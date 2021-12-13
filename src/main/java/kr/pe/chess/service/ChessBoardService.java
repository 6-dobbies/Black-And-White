package kr.pe.chess.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import kr.pe.chess.objects.Bishop;
import kr.pe.chess.objects.King;
import kr.pe.chess.objects.Knight;
import kr.pe.chess.objects.Pawn;
import kr.pe.chess.objects.Queen;
import kr.pe.chess.objects.Rook;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChessBoardService {

	private final Pawn pawn;
	private final King king;
	private final Bishop bishop;
	private final Knight knight;
	private final Rook rook;
	private final Queen queen;
	
	public List<Boolean> B(String data) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(data);
		JSONObject json2 = (JSONObject) json.get("data");
		
		// 받는 체스판 모양 {{p,T.....}*8}
		// 체스판에서 T는 빈칸
		String[][] chessBoard = (String[][]) json2.get("chessBoard");
		
		// 받는 명령구문 {{1,1,p},{1,2,T}}
		String[][] order =  (String[][]) json2.get("order");
		
		// 진영 확인용 데이터
		String color = (String) json2.get("color");
		
		// {합법, 체크, 스테일, 체크메이트}
		List<Boolean> answer = new ArrayList<Boolean>();
		answer.add(false);
		answer.add(false);
		answer.add(false);
		answer.add(false);
		
		if (check(chessBoard, color) == true | staleMate(chessBoard, color) == true | checkMate(chessBoard) == true) {
			// 체크된 체스판이 왔으므로 에러
			return answer;
		}
		
		// 움직임 검증 후, 해당 명령의 합법성 통보
		String [][] changedChessBoard = move(chessBoard, order, color);
		if (changedChessBoard == chessBoard) {
			// 체스판이 바뀌지 않았기에 합법적이지 않은 움직임 에러
			answer.set(0, false);
		}
		else if (changedChessBoard == null) {
			// 체스판이 돌려받지 못해서 알고리즘 에러
			answer.set(0, false);
		}
		else {
			// 체스판이 바뀌었으므로 검증 통과
			answer.set(0, false);
		}
		answer.set(1, check(changedChessBoard, color));
		answer.set(2, staleMate(changedChessBoard, color));
		answer.set(3, checkMate(changedChessBoard));
		return answer;
	}
	
	public String[][] move(String[][] chessBoard, String[][] order, String color) {
		
		int A = Integer.parseInt(order[0][0]);
		int B = Integer.parseInt(order[0][1]);
		String C = order[0][2];
		
		int D = Integer.parseInt(order[1][0]);
		int E = Integer.parseInt(order[1][1]);
		String F = order[1][2];
		
		if (chessBoard[D][E]!=F) {
			return chessBoard;
		}
		// 킹은 잡을수있는 대상이 아님 == 비합법적
		else if (F == "K"| F == "k") {
			return chessBoard;
		}
		// 말 움직임 검증
		else if (moving(chessBoard, order)==true) {
			// 합법적인 움직임이기에 기물을 움직임 
//			// String to Integer
//			Integer.parseInt("1");
			if (chessBoard[D][E] == "T") {
				chessBoard[D][E] = C;
				chessBoard[A][B] = "T";
			}
			else if (chessBoard[D][E] == null) {
				// 체스판 이상 에러송출 / 나오면 데이터 전송 안됨
				return chessBoard;
			}
			else if (chessBoard[D][E] != "T") {
				// 빈칸이 아니니깐 움직이면서 해당 말을 잡은 말로.
				String cap = F; // 잡힌말 처리를 위한 값 이후에 DB에 전송 or 프론트에 넘기기
				chessBoard[D][E] = C;
				chessBoard[A][B] = "T";
			}
			else {
				// 에러 (몰?루)
				System.out.println("이거 뜨면 다 뜯어 고쳐야 됨");
				return null;
			}
		}
		else {
			// 합법적인 움직임이 아닙니다. 에러 송출
			return chessBoard;
		}
		// 움직인 체스판 검증
		String[][] changedChessBoard = chessBoard;
		if (check(changedChessBoard, color)==true) {
			// 체크 확인, true일시 자신이 체크되는것
			// 따라서 잘못된 움직임이기에 에러 송출후, 무엇에 의해 체크되는지 확인해주거나 할 필요성
			return null;
		}
		else if (check(changedChessBoard, color)==false) {
			return changedChessBoard;
		}
		// 몰라 알아서 하셈
		System.out.println("이거 뜨면 다 뜯어 고쳐야 됨");
		return null;
	}
		
	// 합법적인 움직임인지 검증
	public Boolean moving(String[][] chessBoard, String[][] order) {
		
		if (order[0][2] == "p" | order[0][2] == "P") {
			return pawn.move(chessBoard, order);
		}
		else if(order[0][2] == "b" | order[0][2] == "B") {
			return bishop.move(chessBoard, order);
		}
		else if(order[0][2] == "k" | order[0][2] == "K") {
			return king.move(chessBoard, order);
		}
		else if(order[0][2] == "q" | order[0][2] == "Q") {
			return queen.move(chessBoard, order);
		}
		else if(order[0][2] == "n" | order[0][2] == "N") {
			return knight.move(chessBoard, order);
		}
		else if(order[0][2] == "r" | order[0][2] == "R") {
			return rook.move(chessBoard, order);
		}
		
		return false;
		
	}
	
	public Boolean check(String[][] chessBoard, String color) {
		if (0==0) {
			
		}
		return false;
	}
	
	public Boolean staleMate(String[][] chessBoard, String color) {
		if (color == "black") {
			
		}
		return false;
	}
	
	public Boolean checkMate(String[][] chessBoard) {
		
		return false;
	}
}

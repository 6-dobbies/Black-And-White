package kr.pe.chess.service;

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
import kr.pe.playdata.model.response.SingleResult;
import kr.pe.playdata.repository.MemberRepository;
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
	
	public SingleResult<Long> B(String data) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(data);
		JSONObject json2 = (JSONObject) json.get("data");
		
		// 받는 체스판 모양 {{{p},{T}.....}*8}
		String[][][] chessBoard = (String[][][]) json2.get("chessBoard");
		
		// 받는 명령구문 {{{1},{1},{p}},{{1},{2},{T}}}
		String[][] order =  (String[][]) json2.get("order");
		
		int A = Integer.parseInt(order[0][0]);
		int B = Integer.parseInt(order[0][1]);
		String C = order[0][2];
		
		int D = Integer.parseInt(order[1][0]);
		int E = Integer.parseInt(order[1][1]);
		String F = order[1][2];
		
		// 말 움직임 검증
		if (C(chessBoard, order)==true) {
			// 합법적인 움직임이기에 기물을 움직임 
//			// String to Integer
//			Integer.parseInt("1");
			
			if (chessBoard[D][E][0] == "T") {
				
			}
			else if (chessBoard[D][E][0] == null) {
				// 체스판 이상 에러송출
			}
			else if (chessBoard[D][E][0] != "T") {
				// 빈칸이 아니니깐 
			}
			else {
				// 에러 
			}
		}
		else {
			// 합법적인 움직임이 아닙니다. 에러 송출
			
		}
		// 움직인 체스판 검증
		
		
		return null;
	}
	
	// 합법적인 움직임인지 검증
	public Boolean C(String[][][] chessBoard, String[][] order) {
		
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
	
	
	
}

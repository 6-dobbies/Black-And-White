package kr.pe.chess.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONString;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.json.JsonParser;
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
// 이틀만에 만든거니깐 뭐라하지 마세여
public class ChessBoardService {

	private final Pawn pawn;
	private final King king;
	private final Bishop bishop;
	private final Knight knight;
	private final Rook rook;
	private final Queen queen;
	private final Locator locator;
	
	// 필요 메소드 = 1. 기보를 받아서 체스판으로 변경해주는 메소드
	
	// 			  2. 프론트에 체스판 반환
	
	// 기보를 이용하여서 이전 상황을 파악해서, 특수규칙 적용
	// 처음에 실수해서 체스판의 백/흑이 뒤집힌 상황
	private String [][] ChessBoardBase = 
		{		
				{"r","n","b","q","k","b","n","r"},
				{"p","p","p","p","p","p","p","p"},
				{"T","T","T","T","T","T","T","T"},
				{"T","T","T","T","T","T","T","T"},
				{"T","T","T","T","T","T","T","T"},
				{"T","T","T","T","T","T","T","T"},
				{"P","P","P","P","P","P","P","P"},
				{"R","N","B","Q","K","B","N","R"}
		};

	public List<Boolean> B(String data) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(data);
		JSONObject json2 = (JSONObject) json.get("data");
		
		// 받는 체스판 모양 {{p,T.....}*8}
		// 체스판에서 T는 빈칸으로 지정
		// notation은 String 리스트
		String[] notation = (String[]) json2.get("notation");
		
		// 받는 명령구문 {{1,1,p},{1,2,T}}
		String[][] order =  (String[][]) json2.get("order");
		
		String color = (String) json2.get("color");
		
		// 진영 확인용 데이터
		if (notation.length%2 == 0) {
			color = "white";
		}
		else if(notation.length%2 == 1) {
			color = "black";
		}
		
		// 기본 체스판으로 체스판을 셋팅 
		String[][] chessBoard = locator.makeChessBoard(ChessBoardBase, notation);
		if (chessBoard == null) {
			return null;
		}
		
		// {합법, 체크, 스테일, 체크메이트}
		List<Boolean> answer = new ArrayList<Boolean>();
		answer.add(false);
		answer.add(false);
		answer.add(false);
		answer.add(false);
		
		if (check(chessBoard, color) == true | staleMate(chessBoard, color, notation) == true | checkMate(chessBoard, color, notation) == true) {
			// 체크된 체스판이 왔으므로 에러
			return answer;
		}
		
		// 움직임 검증 후, 해당 명령의 합법성 통보
		String [][] changedChessBoard = move(chessBoard, order, color, notation);
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
			answer.set(0, true);
		}
		// 체크, 스테일메이트, 체크메이트 검증후, 합법적인 움직임 기록 
		answer.set(1, check(changedChessBoard, color));
		answer.set(3, checkMate(changedChessBoard, color, notation));
		if (color == "black") {
			color = "white";
		}
		else if (color == "white") {
			color = "black";
		}
		answer.set(2, staleMate(changedChessBoard, color, notation)); // 체크메이트일때도 같이 작동할것임
		return answer;
	}
	
	public String[][] move(String[][] chessBoard, String[][] order, String color, String[] notation) {
		
		int A = Integer.parseInt(order[0][0])+1;
		int B = Integer.parseInt(order[0][1])+1;
		String C = order[0][2];
		
		int D = Integer.parseInt(order[1][0])+1;
		int E = Integer.parseInt(order[1][1])+1;
		String F = order[1][2];
		
		if (chessBoard[D][E]!=F) {
			return chessBoard;
		}
		// 킹은 잡을수있는 대상이 아님 == 비합법적
		else if (F == "K"| F == "k") {
			return chessBoard;
		}
		// 말 움직임 검증
		else if (moving(chessBoard, order, notation) == true) {
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
		// 캐슬링 특수규칙
		// moving 밖에있어서 따로 검증
		else if (order[0][0]=="4" & (order[0][1]=="0" | order[0][1]=="7") & order[0][2]=="k" & (order[1][1]=="1" | order[1][1]=="6")) {
			// 퀸사이드 뒤집혀있음
			Boolean castlingK = true;
			Boolean castlingR = true;
			if (order[1][0] =="1") {
				if (color == "white") {
					for (int i=0; i<notation.length; i++) {
						if (notation[i].substring(0,1)=="K") {
							castlingK = false;
						}
						else if (notation[i].substring(0,2)=="Rh") {
							castlingR = false;
						}
					}
					if (castlingK & castlingR) {
						chessBoard[7][4] ="T";
						chessBoard[7][6] ="K";
						chessBoard[7][5] ="R";
						chessBoard[7][7] ="T";
					}
				}
				else if (color == "black") {
					for (int i=0; i<notation.length; i++) {
						if (notation[i].substring(0,1)=="k") {
							castlingK = false;
						}
						else if (notation[i].substring(0,2)=="rh") {
							castlingR = false;
						}
					}
					if (castlingK & castlingR) {
						chessBoard[0][4] ="T";
						chessBoard[0][6] ="k";
						chessBoard[0][5] ="r";
						chessBoard[0][7] ="T";
					}
				}
			}
			// 킹사이드 뒤집혀있음
			else if (order[1][0] =="6") {
				if (color == "white") {
					for (int i=0; i<notation.length; i++) {
						if (notation[i].substring(0,1)=="K") {
							castlingK = false;
						}
						else if (notation[i].substring(0,2)=="Ra") {
							castlingR = false;
						}
					}
					if (castlingK & castlingR) {
						chessBoard[7][4] ="T";
						chessBoard[7][1] ="K";
						chessBoard[7][3] ="R";
						chessBoard[7][0] ="T";
					}
				}
				else if (color == "black") {
					for (int i=0; i<notation.length; i++) {
						if (notation[i].substring(0,1)=="k") {
							castlingK = false;
						}
						else if (notation[i].substring(0,2)=="Ra") {
							castlingR = false;
						}
					}
					if (castlingK & castlingR) {
						chessBoard[0][4] ="T";
						chessBoard[0][1] ="k";
						chessBoard[0][3] ="r";
						chessBoard[0][0] ="T";
					}
				}
			}
		}
		// 프로모션용 코드
		// 
		else if (order[0][2].substring(0,1).toLowerCase()=="p" & (order[0][1]=="0"|order[0][1]=="7")) {
			chessBoard[A][B] = "T";
			chessBoard[D][E] = order[0][2].substring(0,1);
		}
		else {
			// 합법적인 움직임이 아닙니다. 에러 송출
			return chessBoard;
		}
		// 움직인 체스판 검증
		String[][] changedChessBoard = chessBoard;
		if (check(changedChessBoard, color) == true) {
			// 체크 확인, true일시 자신이 체크되는것
			// 따라서 잘못된 움직임이기에 에러 송출후, 무엇에 의해 체크되는지 확인해주거나 할 필요성
			return null;
		}
		else if (check(changedChessBoard, color) == false) {
			return changedChessBoard;
		}
		// 몰라 알아서 하셈
		System.out.println("이거 뜨면 다 뜯어 고쳐야 됨");
		return null;
	}
		
	// 합법적인 움직임인지 검증
	public Boolean moving(String[][] chessBoard, String[][] order, String[] notation) {
		
		if (order[0][2] == "p" | order[0][2] == "P") {
			return pawn.move(chessBoard, order, notation);
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
		String row = null;
		String column = null;
		String diagonPlus = null;
		String diagonMinus = null;
		
		int x1 = Integer.parseInt(locator.locate(chessBoard,"k")[0][0]);
		int y1 = Integer.parseInt(locator.locate(chessBoard,"k")[0][1]);
		int x2 = Integer.parseInt(locator.locate(chessBoard,"K")[0][0]);
		int y2 = Integer.parseInt(locator.locate(chessBoard,"K")[0][1]);
		
//		직선상에 R이나 Q가 위치
		if (color == "black") {
			for (int i = 0; i<8; i++) {
					row = row + chessBoard[i][y1];
					column = column + chessBoard[x1][i];
				}
			for (int i = 0; i < y1; i++) {
				diagonPlus = chessBoard[x1-i][y1-i] + diagonPlus; 
				diagonMinus = diagonPlus + chessBoard[x1-i][y1-i]; 
			}
			for (int i = 0; i < 8-y1; i++) {
				diagonPlus = diagonPlus + chessBoard[x1-i][y1-i] ; 
				diagonMinus = chessBoard[x1-i][y1-i] + diagonPlus; 
			}
		}
		else if (color == "white") {
			for (int i = 0; i<8; i++) {
				row = row + chessBoard[i][y2];
				column = column + chessBoard[x2][i];
			}
			for (int i = 0; i < y2; i++) {
				diagonPlus = chessBoard[x2-i][y2-i] + diagonPlus; 
				diagonMinus = diagonPlus + chessBoard[x2-i][y2-i]; 
			}
			for (int i = 0; i < 8-y2; i++) {
				diagonPlus = diagonPlus + chessBoard[x2-i][y2-i] ; 
				diagonMinus = chessBoard[x2-i][y2-i] + diagonPlus; 
			}
		}
		// 필요없는 공백표시 제거
		row.replace("T", "");
		column.replace("T", "");
		// 폰 체크 확인 (대각선 공백 필요)
		if (color == "black") {
			if (diagonPlus.contains("kP")) {
				return true;
			}
			else if (diagonMinus.contains("Pk")) {
				return true;
			}
		}
		else if (color == "white") {
			if (diagonPlus.contains("pK")) {
				return true;
			}
			else if (diagonMinus.contains("Kp")) {
				return true;
			}
		}
		// 필요없어진 공백 제거
		diagonPlus.replace("T", "");
		diagonMinus.replace("T", "");
		// 직선상 체크 확인
		if (color == "black") {
			if (row.contains("kQ")|row.contains("Qk")|row.contains("kR")|row.contains("Rk")) {
				return true;
			}
			else if (column.contains("kQ")|column.contains("Qk")|column.contains("kR")|column.contains("Rk")) {
				return true;
			}
			else if (diagonPlus.contains("kQ")|diagonPlus.contains("Qk")|diagonPlus.contains("kB")|diagonPlus.contains("Bk")) {
				return true;
			}
			else if (diagonMinus.contains("kQ")|diagonMinus.contains("Qk")|diagonMinus.contains("kB")|diagonMinus.contains("Bk")) {
				return true;
			}
		}
		else if (color == "white") {
			if (row.contains("Kq")|row.contains("qK")|row.contains("Kr")|row.contains("rK")) {
				return true;
			}
			else if (column.contains("qK")|column.contains("qK")|column.contains("Kr")|column.contains("rK")) {
				return true;
			}
			else if (diagonPlus.contains("qK")|diagonPlus.contains("qK")|diagonPlus.contains("Kb")|diagonPlus.contains("bK")) {
				return true;
			}
			else if (diagonMinus.contains("qK")|diagonMinus.contains("qK")|diagonMinus.contains("Kb")|diagonMinus.contains("bK")) {
				return true;
			}
		}
		
		int[] location = {-2,-1,1,2};
		String knightloc = null;
		// 킹 위치에서 갈수있는 모든 나이트 위치의 말 모으기
		for (int i =0; i<4; i++) {
			for (int j = 0; j<4; j++) {
				try {
					if (color == "black") {
						knightloc = knightloc + chessBoard[x1+location[i]][y1+location[j]];
					}
					else if (color == "white") {
						knightloc = knightloc + chessBoard[x2+location[i]][y2+location[j]];
					}
				}catch(NullPointerException e){
					System.out.print("e");
				}
			}
		}
		// 나이트 범위에 상대 나이트가 위치
		if (color == "black") {
			if (knightloc.contains("N")) {
				return true;
			}
		}
		else if (color == "white") {
			if (knightloc.contains("n")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean staleMate(String[][] chessBoard, String color, String[] notation) {
		// 킹의 위치
		int x1 = Integer.parseInt(locator.locate(chessBoard,"k")[0][0]);
		int y1 = Integer.parseInt(locator.locate(chessBoard,"k")[0][1]);
		int x2 = Integer.parseInt(locator.locate(chessBoard,"K")[0][0]);
		int y2 = Integer.parseInt(locator.locate(chessBoard,"K")[0][1]);
		// 킹이 움직일수있는 경우의 수
		int [] A = {-1,0,1};
		// 체크가 아니면서, 움직일 말이 없고 킹도 움직일수 없을때
		
		// 킹 움직임 확인
		for (int i = 0; i<3; i++) {
			for (int j = 0; j<3; j++) {
				try {
					if (color == "black") {
						if (chessBoard[x1+A[i]][y1+A[j]] == "T") {
							chessBoard[x1+A[i]][y1+A[j]] = "k";
							chessBoard[x1][y1] = "T";
						}
						else if (chessBoard[x1+A[i]][y1+A[j]] == null) {
							// 체스판 이상 에러송출 / 나오면 데이터 전송 안됨
							System.out.println("error chessBoard staleMate");
						}
						else if (chessBoard[x1+A[i]][y1+A[j]] != "T") {
							// 빈칸이 아니니깐 움직이면서 해당 말을 잡은 말로.
							chessBoard[x1+A[i]][y1+A[j]] = "k";
							chessBoard[x1][y1] = "T";
						}
						else {
							// 에러 (몰?루)
							System.out.println("이거 뜨면 다 뜯어 고쳐야 됨");
							return null;
						}
					}
					else if (color == "white") {
						if (chessBoard[x2+A[i]][y2+A[j]] == "T") {
							chessBoard[x2+A[i]][y2+A[j]] = "k";
							chessBoard[x2][y2] = "T";
						}
						else if (chessBoard[x2+A[i]][y2+A[j]] == null) {
							// 체스판 이상 에러송출 / 나오면 데이터 전송 안됨
							System.out.println("error chessBoard staleMate");
						}
						else if (chessBoard[x2+A[i]][y2+A[j]] != "T") {
							// 빈칸이 아니니깐 움직이면서 해당 말을 잡은 말로.
							chessBoard[x2+A[i]][y2+A[j]] = "k";
							chessBoard[x2][y2] = "T";
						}
						else {
							// 에러 (몰?루)
							System.out.println("이거 뜨면 다 뜯어 고쳐야 됨");
							return null;
						}
					}
					String[][] chagedChessBoard = chessBoard;
					
					if (check(chagedChessBoard, color)==false) {
						return false;
					}
				}catch(NullPointerException e) {
					System.out.print("e");
				}
			}
		}
			
			// 가진 모든말의 이동경로 확인
			// 모든말 검색
			String[] black = {"k","p","b","r","q","n"};
			String[] white = {"K","P","B","R","Q","N"};
			String[][][] locList = {};
			String check = null;
			if (color == "black") {
				for (int i = 0; i<6; i++) {
					// 모든 말들의 위치
					locList[i] = locator.locate(chessBoard, black[i]);
					// 모든 말의 움직임 검증
				}
				for (int i =0; i<6;i++) {
					for (int j =0; j<2; j++) {
						if (indicator(chessBoard,locList[i][j],notation)==null) {
							// 전부 null일때만 return true
							check = check + "T";
						}
						else {
							// 사용가능한 움직임이 존재
							check = check + "F";
						}
					}
				}
				if (check == "TTTTTTTTTTTT") {
					return true;
				}
				else {
					return false;
				}
			}
			else if (color == "white") {
				for (int i = 0; i<6; i++) {
					// 모든 말들의 위치
					locList[i] = locator.locate(chessBoard, white[i]);
					// 모든 말의 움직임 검증
				}
				for (int i =0; i<6;i++) {
					for (int j =0; j<2; j++) {
						if (indicator(chessBoard,locList[i][j],notation)==null) {
							// 전부 null일때만 return true
							check = check+"T";
						}
						else {
							// 사용가능한 움직임이 존재
							check = check+"F";
						}
					}
				}
				if (check == "TTTTTTTTTTTT") {
					return true;
				}
				else {
					return false;
				}
			}
			return false;
		}		
	
	public Boolean checkMate(String[][] chessBoard, String color, String[] notation) {
		// 체크면서, 킹이 움직일곳이 없고, 막아줄 말도 없을때
		// 체크 확인
		if (check(chessBoard, color) == true) {
			// 킹 움직임 확인
			// 가진 모든 말의 이동경로중 막아줄 수 있는 경로 찾기
			// 바꾼 위치에서 체크 확인 후 아니라면 다시 경로 찾게 while문 or for문으로 구성
			// 스테일 메이트가 모든말의 움직임 가능성이 있는지 체크하는것이 되었으므로 가능
			if (staleMate(chessBoard, color, notation) == true) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	// 말의 위치와 정보를 받아서 말이 움직일수있는 범위 표시
	public String[][] indicator(String[][] chessBoard, String[] loc, String[] notation) {
		
		String[] black2 = {"k","p","b","r","q","n"};
		ArrayList<String> black = new ArrayList<>();
		for (int i= 0; i<= black2.length; ++i) {
			black.add(black2[i]);
		}
		
		ArrayList<String> white = new ArrayList<>();
		String[] white2 = {"K","P","B","R","Q","N"};
		for (int i= 0; i<= white2.length; ++i) {
			white.add(white2[i]);
		}
		// loc으로 지정된 말의 움직임 범위를 모두 지정해주는 함수
		int[][] location = {{}};
		// location 지정해야 됨
		for (int i = 0; i<64; i++) {
			for (int j = 0; j<8; j++) {
				for (int h = 0; h<8; h++) {
					location[i][0]= j;
					location[i][1]= h;
				}
			}
		}
		String[][] destination = null;
		for ( int i = 0; i<64; i++) {
			int x1 = location[i][0];
			int y1 = location[i][0];
			String[][] order = {{loc[0],loc[1],loc[2]},{Integer.toString(x1),Integer.toString(y1),chessBoard[x1][y1]}};
			if (moving(chessBoard, order, notation)==true) {
				destination[i][0]=Integer.toString(x1);
				destination[i][1]=Integer.toString(y1);
				destination[i][2]=chessBoard[x1][y1];
			}
		}
		return destination;
	}
	
	
	public String PyChessOnly(String data) throws ParseException, IOException {
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(data);
		JSONObject json2 = (JSONObject) json.get("data");
		
		// 받는 명령구문 {{1,1,p},{1,2,T}}
		String order =  (String) json2.get("order");
		String[] notation = (String[]) json2.get("notation");
		String option =  (String) json2.get("option");
		//난이도 조절용 (miniMax의 depth결정 0~4, 5이상은 응답에 시간이 오래 걸림)
		String dif = (String) json2.get("dif");
		
		// 파이썬 말고 자바알고리즘이랑 통신할때 필요
//		String parseOrder = "";
//		String[] parseNotation = {};
		
//		if (notation == null) {
//			parseOrder = locator.orderParser(order);
//		}
//		else if (notation != null) {
//			parseOrder = locator.orderParser(order);
//			parseNotation = locator.notationParser(notation);
//		}
		
		// uri 조절용 값 같은 VM에서 돌리면 상관없음
		String uri = "http://localhost:5000/";
		// 서버에 전송할 방식 결정(uri로)
		if (option == "setGame") {
			uri += "setgame";
		}
		else if (option == "move") {
			uri += "move";
		}
		// 다른걸 사용하면 쓸데는 없어보이지만 일단 만듬
		else if (option == "setGameMove") {
			uri += "setgamemove";
		}
		// 새게임 시작
		else if (option == "new") {
			uri += "game";
		}
		
		URL url = new URL(uri);
		// 파이썬 서버에 전송준비
		String inputLine=null;
        StringBuffer stringBuffer=new StringBuffer();
        
		HttpURLConnection conn=(HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept-Charset", "UTF-8");
        // 서버의 응답 대기 10초
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        
        
        
        // 보낼 json형식 만들기
        String C = "";
        for (int i=0; i<notation.length;i++) {
        	if (i==notation.length-1) {
        		C += "\""+notation[i]+"\"";
        	}
        	else {
        		C += "\""+notation[i]+"\""+",";
        	}
        }
        String A = "\"order\" : \"" + order + "\"";
        String B = "\"notation\" : \""+ C +"\"";
        String dif2 = "\"dif\" : \""+ dif +"\"";
        String jsons = "";
        if (option == "setgamemove") {
        	jsons = "{"+A+","+B+","+dif2+"}";
        }
        else if (option == "setgame") {
        	jsons = "{"+B+"}";
        }
        else if (option == "move") {
        	jsons = "{"+A+","+dif2+"}";
        }
        else if (option == "new") {
        	jsons = "{\"data\" : \"a\"}";
        }

        // json 객체 생성
        JSONObject jObject = (JSONObject) jsonParser.parse(jsons);
        // 보내기
        BufferedWriter bWriter=new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
        bWriter.write(jObject.toString());
        // 데이터 전송후 데이터 삭제
        bWriter.flush();
        // 받기
        BufferedReader bReader=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        while((inputLine=bReader.readLine())!=null){
            stringBuffer.append(inputLine);
        }
        
        bWriter.close();
        bReader.close();
        conn.disconnect();
		
		// 파이썬 서버에서 돌려받은 값 리턴
        // 돌려받은값 = stringBuffer
        // StringBuffer를 필요한 형식으로 전환 == 성공/실패값 == string
        String buffer = stringBuffer.toString();
        
        // 스트링으로 전환되는지 확인 필수
		return buffer;
	}
	
	
}
package kr.pe.chess.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class Locator {

	private final ChessBoardService cbs;
	
	public String[][] locate(String[][] chessBoard, String piece) {
		String[][] answer = {};
		int count = 0;
		for (int i=1; i<=8; i++) {
			for (int j=1; j<=8; j++) {
				if (chessBoard[i][j]==piece) {
					answer[count][0]=Integer.toString(i);
					answer[count][1]=Integer.toString(j);
					answer[count][2]=piece;
					count += 1;
				}
			}
		}
		
		return answer;
	}
	
	public String[][] makeChessBoard(String[][] chessBase, String[] notation) {
		String[][] chessBoard = chessBase;
		// ArrayList로 변환
		ArrayList<String> x = new ArrayList<>();
		String[] x2 = {"a","b","c","d","e","f","g","h"};    //Ke8, e3, Ke8+#
		for (int i =0; i<x2.length; i++) {
			x.add(x2[i]);
		}
		String[][][] convert = {};
		// notation의 요소를 하나씩 분리해서 변환후 convert에 기록
		for (int i =0; i < notation.length; i++) {
			String A = notation[i];// {A,B,....}에서 A 받음
			String[] B = {};
			String[][] order = {};
			for (int j =0; j<A.length()-1;i++) {
				B[j] = A.substring(j,j+1);
				}
			if (x.contains(B[0])) {
				order[0][2] = "p";
			}
			else {
				order[0][2] = B[0];	
			}
			// 말만 이동한 기록
			if (x.contains(B[1])) {
				order[1][0]=Integer.toString(x.indexOf(B[1])+1);
				order[1][1]=Integer.toString(Integer.parseInt(B[2]+1));
			}
			// 말을 잡았다고 기록한것
			else if (B[1]=="x" | B[1]=="X"){
				order[1][0]=Integer.toString(x.indexOf(B[2])+1);
				order[1][1]=Integer.toString(Integer.parseInt(B[3]+1));
			}
			else {
				return null;
			}

			if (i%2 == 1) { // 홀수 횟수는 백색의 움직임
				order[0][2].toUpperCase();
			}
			else if (i%2 == 0) { // 짝수 횟수는 흑색의 움직임
				order[0][2].toLowerCase();
			}
			String[] loc = {};
			loc[0]=order[1][0];
			loc[1]=order[1][1];
			String[][] find = locate(chessBase,order[0][2]);
			try {
				for (int h=0; h<2; h++) {
					String[][] des = cbs.indicator(chessBase, find[h], notation);
					for(int j =0; j<des.length; j++) {
						if (des[j]==loc) {
							order[0][0] =find[h][0];
							order[0][1] =find[h][1];
						}
					}
				}
			} catch(NullPointerException e) {
				System.out.println("찾은 말이 한개입니다. ");
			}
			convert[i]=order;
		}
		
		// 변환된 요소를 이용해서 체스판 변환 for문으로 모든 notation을 사용
		for (int i = 0; i<convert.length; i++) {
			if (i%2==0) {
				chessBoard = cbs.move(chessBoard, convert[i], "white", notation);
			}
			else if(i%2==1) {
				chessBoard = cbs.move(chessBoard, convert[i], "black", notation);
			}
		}
			
		// 변환된 체스판 반환
		return chessBoard;
	}
	public String[][] makeChessBoard(String data) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(data);
		JSONObject json2 = (JSONObject) json.get("data");
		String[] notation = (String[]) json2.get("notation");
		
		ArrayList<String> x = new ArrayList<>();
		String[] x2 = {"a","b","c","d","e","f","g","h"};    //Ke8, e3, Ke8+#
		for (int i =0; i<x2.length; i++) {
			x.add(x2[i]);
		}
		String[][][] convert = {};
		String[][] chessBase =
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
		String[][] chessBoard = chessBase;
		String [] notation2 = {};
		// notation의 요소를 하나씩 분리해서 변환후 convert에 기록
		for (int i =0; i < notation.length; i++) {
			String A = notation[i];// {A,B,....}에서 A 받음
			// notation 진행 정도에 맞춰 바뀌는 String[]
			for (int j = 0; j <= i; j++) {
				notation2[j] = notation[j];
			}
			String[] B = {};
			String[][] order = {};
			for (int j =0; j<A.length()-1;i++) {
				B[j] = A.substring(j,j+1);
				}
			if (x.contains(B[0])) {
				order[0][2] = "p";
				order[1][0] = Integer.toString(x.indexOf(B[0]));
				order[1][1] = Integer.toString(8-Integer.parseInt(B[1]));
				if (B.length == 4) {
					if (B[2]=="=") {
						order[0][2]="p"+B[3];
					}
				}
			}
			else {
				order[0][2] = B[0];	
			}
			// 말만 이동한 기록
			if (x.contains(B[1])) {
				order[1][0]=Integer.toString(x.indexOf(B[1]));
				order[1][1]=Integer.toString(8-Integer.parseInt(B[2]));
			}
			// 말을 잡았다고 기록한것
			else if (B[1]=="x" | B[1]=="X"){
				order[1][0]=Integer.toString(x.indexOf(B[2]));
				order[1][1]=Integer.toString(8-Integer.parseInt(B[3]));
			}
			else {
				return null;
			}
			// 캐슬링 변환
			if (B[0]=="0") {
				// 퀸쪽 캐슬링
				if (B.length==5|B.length==6) {
					if (i%2 == 1) { // 홀수 횟수는 백색의 움직임
						order[0][0]="5";
						order[0][1]="8";
						order[0][2]="K";
						order[1][0]="2";
						order[1][1]="8";
						order[1][2]="T";
					}
					else if (i%2 == 0) { // 짝수 횟수는 흑색의 움직임
						order[0][0]="5";
						order[0][1]="1";
						order[0][2]="K";
						order[1][0]="2";
						order[1][1]="1";
						order[1][2]="T";
					}
				}
				// 킹쪽 캐슬링
				if (B.length==3|B.length==4) {
					if (i%2 == 1) { // 홀수 횟수는 백색의 움직임
						order[0][0]="4";
						order[0][1]="1";
						order[0][2]="K";
						order[1][0]="7";
						order[1][1]="1";
						order[1][2]="T";
					}
					else if (i%2 == 0) { // 짝수 횟수는 흑색의 움직임
						order[0][0]="4";
						order[0][1]="1";
						order[0][2]="K";
						order[1][0]="7";
						order[1][1]="1";
						order[1][2]="T";
					}
				}
			}
			

			if (i%2 == 1) { // 홀수 횟수는 백색의 움직임
				order[0][2].toUpperCase();
			}
			else if (i%2 == 0) { // 짝수 횟수는 흑색의 움직임
				order[0][2].toLowerCase();
			}
			String[] loc = {};
			loc[0]=order[1][0];
			loc[1]=order[1][1];

			// 체스보드 내에서 찾은 해당 말의 위치
			String[][] find = locate(chessBoard,order[0][2]);
			try {
				for (int h=0; h<2; h++) {
					String[][] des = cbs.indicator(chessBoard, find[h], notation2);
					for(int j =0; j < des.length; j++) {
						if (des[j]==loc) {
							order[0][0] =find[h][0];
							order[0][1] =find[h][1];
						}
					}
				}
			} catch(NullPointerException e) {
				System.out.println("찾은 말이 한개입니다. ");
			}
			convert[i]=order;
		}
		
		// 변환된 요소를 이용해서 체스판 변환 for문으로 모든 notation을 사용
		for (int i = 0; i<convert.length; i++) {
			for (int j = 0; j <= i; j++) {
				notation2[j] = notation[j];
			}
			if (i%2==0) {
				chessBoard = cbs.move(chessBoard, convert[i], "white", notation);
			}
			else if(i%2==1) {
				chessBoard = cbs.move(chessBoard, convert[i], "black", notation);
			}
		}
			
		// 변환된 체스판 반환
		return chessBoard;
	}
	
	
	// 일반적인 좌표를 이용한 notation을 위한것 - python과 연결하기위함
	public String[] notationParser(String[] notation) throws ParseException {		
		// ArrayList로 변환
		ArrayList<String> x = new ArrayList<>();
		String[] x2 = {"a","b","c","d","e","f","g","h"};    //Ke8, e3, Ke8+#
		for (int i =0; i<x2.length; i++) {
			x.add(x2[i]);
		}
		ArrayList<String> x3 = new ArrayList<>();
		String[] x4 = {"1","2","3","4","5","6","7","8"};    //Ke8, e3, Ke8+#
		for (int i =0; i<x4.length; i++) {
			x3.add(x4[i]);
		}
		
		String A = "";
		String[] B = {};
		String[][] convertedNotation = {};
		
		// 받은 notation 분리
		for (int i =0; i<notation.length;i++) {
			A = notation[i];
			// 분리된 notation 글자별로 나누기
			for (int j =0; j<A.length()-1;i++) {
				B[j] = A.substring(j,j+1);
				// 좌표값일때
				if(x.contains(B[j])) {
					convertedNotation[i][j] = B[j];
				}
				// 숫자일때 체스판이 위아래로 뒤집혀있기 때문에 명령을 거꾸로 뒤집어주기
				else if(x3.contains(B[j])) {
					convertedNotation[i][j] = Integer.toString(8-Integer.parseInt(B[j]));
				}
				// 말 정보일때
				else if(!x.contains(B[j])) {
					continue;
				}
			}
		}
		
		String[] convertedNotation2 = {};
		// 한줄로 보내기위해 합치기
		for (int i =0; i<convertedNotation.length;i++) {
			for (int j =0; j<convertedNotation[i].length;j++) {
				convertedNotation2[i]+=convertedNotation[i][j];
			}
		}
		return convertedNotation2;
	}
	
	
	public String orderParser(String order) throws ParseException {		
		// ArrayList로 변환
		ArrayList<String> x = new ArrayList<>();
		String[] x2 = {"a","b","c","d","e","f","g","h"};    //Ke8, e3, Ke8+#
		for (int i =0; i<x2.length; i++) {
			x.add(x2[i]);
		}
		ArrayList<String> x3 = new ArrayList<>();
		String[] x4 = {"1","2","3","4","5","6","7","8"};    //Ke8, e3, Ke8+#
		for (int i =0; i<x4.length; i++) {
			x3.add(x4[i]);
		}
		
		String A = "";
		String[] B = {};
		String convertedOrder = "";
		
		// 받은 notation 분리
		for (int i =0; i<order.length();i++) {
			A = order.substring(i,i+1);
			
			// 분리된 notation 글자별로 나누기
			B[i] = A;
			if(x.contains(B[i])) {
				convertedOrder += B[i];
			}
			// 숫자일때 체스판이 위아래로 뒤집혀있기 때문에 명령을 거꾸로 뒤집어주기
			else if(x3.contains(B[i])) {
				convertedOrder += Integer.toString(8-Integer.parseInt(B[i]));
			}
			// 말 정보일때
			else if(!x.contains(B[i])) {
				continue;
			}
		}
			
		return convertedOrder;
	}
}

package kr.pe.chess.service;

public class Locator {

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
		String[][] chessBoard = {};
		
		// notation의 요소를 하나씩 분리
		for (int i =0; i<notation.length; i++) {
			String A = notation[i];// {A,B,....}에서 A 받음
			
		}
		
		// notation 요소를 변환
		String[] convert = {};
		
		// 변환된 요소를 이용해서 체스판 변환 for문으로 모든 notation을 사용
		for (int i = 0; i<convert.length; i++) {
			
		}
			
		// 변환된 체스판 반환
		chessBoard = chessBase;
		return chessBoard;
	}
}

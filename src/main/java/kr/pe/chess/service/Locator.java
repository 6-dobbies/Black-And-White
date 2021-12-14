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
}

package kr.pe.chess.service;

public class Locator {

	public int[] locate(String[][] chessBoard, String piece) {
		int[][] answer = {};
		int count = 0;
		for (int i=1; i<=8; i++) {
			for (int j=1; j<=8; j++) {
				if (chessBoard[i][j]==piece) {
					answer[count][0]=i;
					answer[count][1]=j;
					count += 1;
				}
			}
		}
		return null;
		
	}
}

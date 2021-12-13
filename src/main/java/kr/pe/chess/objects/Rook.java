package kr.pe.chess.objects;

import java.util.ArrayList;

public class Rook {
	public Boolean move(String[][] chessBoard, String[][] order) {
		int A = Integer.parseInt(order[0][0]);
		int B = Integer.parseInt(order[0][1]);
		String C = order[0][2];

		int D = Integer.parseInt(order[1][0]);
		int E = Integer.parseInt(order[1][1]);
		String F = order[1][2];

		String[] black2 = { "k", "p", "b", "r", "q", "n" };
		ArrayList<String> black = new ArrayList<>();
		for (int i = 0; i <= black2.length; ++i) {
			black.add(black2[i]);
		}

		String[] white2 = { "K", "P", "B", "R", "Q", "N" };
		ArrayList<String> white = new ArrayList<>();
		for (int i = 0; i <= white2.length; ++i) {
			white.add(white2[i]);
		}
		// 하나라도 안맞는것은 합법적이지 않은 움직임
		if (chessBoard[A][B] == C) {
			// 움직이는것인지 체크
			if (A != D | B != E) {
				// 같은 진영의 말을 잡으려 하는것인지 체크
				if (!(white.contains(C) & white.contains(F)) & !(black.contains(C) & black.contains(F))) {
					// 말 이동 관련
					// 킹은 모든 방향으로 한칸씩 움직일 수 있음
					// 룩은 x축 y축 한방양만 가능
					// x축일경우
					if (Math.abs(Math.abs(A) - Math.abs(D)) > 0) {
						// 반복해서 한칸씩 x축을 이동하며 검사,
						for (int i = 1; i < Math.abs(Math.abs(A) - Math.abs(D)) - 1; i++) {
							if ((A - D) > 0) {// 왼쪽이동, A-원래위치 D- 움직인 위치, 0보다 작을시 왼쪽이동
								if (chessBoard[A - i][B] != "T") {
									return false;
								}
							}
							// 오른쪽이동
							if (A - D < 0) {
								if (chessBoard[A + i][B] != "T") {
									return false;
								}
							}
						}
						return true;
					}
					// y축일경우
					if (Math.abs(Math.abs(B) - Math.abs(E)) > 0) {
						// 반복해서 한칸씩 y축을 이동하며 검사
						for (int i = 1; i < Math.abs(Math.abs(B) - Math.abs(E)) - 1; i++) {
							if ((B - E) > 0) {// 아래쪽이동, A-원래위치 D- 움직인 위치, 0보다 작을시 왼쪽이동
								if (chessBoard[A][B - i] != "T") {
									return false;
								}
							}
							// 윗쪽이동
							if (B - E < 0) {
								if (chessBoard[A][B + i] != "T") {
									return false;
								}
							}
						}
						return true;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else

		{
			return false;
		}
		return false;
	}
}
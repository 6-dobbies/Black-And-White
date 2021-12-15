package kr.pe.chess.objects;

import java.util.ArrayList;

public class Knight {

	public Boolean move(String[][] chessBoard, String[][] order) {
		int A = Integer.parseInt(order[0][0])+1;
		int B = Integer.parseInt(order[0][1])+1;
		String C = order[0][2];
		
		int D = Integer.parseInt(order[1][0])+1;
		int E = Integer.parseInt(order[1][1])+1;
		String F = order[1][2];
		
		ArrayList<String> black = new ArrayList<>();
		String[] black2 = {"k","p","b","r","q","n"};
		for (int i= 0; i<= black2.length; ++i) {
			black.add(black2[i]);
		}
		
		ArrayList<String> white = new ArrayList<>();
		String[] white2 = {"K","P","B","R","Q","N"};
		for (int i= 0; i<= white2.length; ++i) {
			white.add(white2[i]);
		}
		
		// 하나라도 안맞는것은 합법적이지 않은 움직임
		if (chessBoard[A][B] == C) {
			// 움직이는것인지 체크
			if ( A != D | B != E ) {
				// 같은 진영의 말을 잡으려 하는것인지 체크
				if (!(white.contains(C) & white.contains(F)) & !(black.contains(C) & black.contains(F))) {
					// 말 이동 관련
					// 나이트는 1,2 또는 2,1 비율로 이동 따라서 이동값이 1,2 또는 2,1가 나오게
					if ((Math.abs(Math.abs(A)-Math.abs(D))==1 & Math.abs(Math.abs(B)-Math.abs(E))==2) | (Math.abs(Math.abs(A)-Math.abs(D))==2 & Math.abs(Math.abs(B)-Math.abs(E))==1) ) {
						return true;
					}
				}
				else {
					return false;
				}
			}
				else {
					return false;
				}
		}
		else {
			return false;
		}
		return false;
	}

}
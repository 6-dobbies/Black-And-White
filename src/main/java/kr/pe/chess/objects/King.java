package kr.pe.chess.objects;

import java.util.ArrayList;

public class King {

	public Boolean move(String[][] chessBoard, String[][] order) throws NullPointerException{
		// 독립시킬 방법이 딱 떠오르지않아서, 다른곳으로 뺄수 있는지 방법이 있는지 
		// {A=x,B=y,기물이름} 시작위치
		int A = Integer.parseInt(order[0][0]);
		int B = Integer.parseInt(order[0][1]);
		
		// 선택한 기물정보
		String C = order[0][2];
		
		// {D=x,E=y,기물이름} 도착위치
		int D = Integer.parseInt(order[1][0]);
		int E = Integer.parseInt(order[1][1]);
		
		// 도착할 기물정보
		String F = order[1][2];
		
		// 서로 진영을 확인용 , 선택,도착한 기물의 정보가 백&흑 인지 정확한 확인용 , 체크가 필요할 경우를 위하여 미리 만들어둔것
		// 현재 사용중인곳 : 같은 진영의 말을 잡으려하는것인지 체크 하는 항목
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
		
		// 하나라도 안맞는것은 합법적이지 않은 움직임, AB - order 입력을 했을때 말이 같은지 확인용, 교차검증? 서로 맞는지 검사 
		if (chessBoard[A][B] == C) {
			// 움직이는것인지 체크
			if ( A != D | B != E ) {
				// 같은 진영의 말을 잡으려 하는것인지 체크
				if (!(white.contains(C) & white.contains(F)) & !(black.contains(C) & black.contains(F))) {
					// 말 이동 관련 , Math-절대값 구하는것, 
					// 킹은 모든 방향으로 한칸씩 움직일 수 있음, (A,D = X축 / B,E = Y축)
					if (Math.abs(Math.abs(A)-Math.abs(D))<2 & Math.abs(Math.abs(B)-Math.abs(E))<2) {
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

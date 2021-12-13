package kr.pe.chess.objects;

import java.util.ArrayList;

public class Pawn {

	public Boolean move(String[][] chessBoard, String[][] order) {
		int A = Integer.parseInt(order[0][0]);
		int B = Integer.parseInt(order[0][1]);
		String C = order[0][2];
		
		int D = Integer.parseInt(order[1][0]);
		int E = Integer.parseInt(order[1][1]);
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
					// 폰은 초기위치에 있을경우 2칸 전진 가능(앙파상은 그 전 체스판을 받아야되기에 일단 구현 X)
					// 폰은 앞으로 한칸씩만 움직이고, 대각선에 상대말이 존재할 경우 그 위치로 이동가능 
					// 따라서 첫번째는 전진용
					if (white.contains(C) & (B-E)==1 & F=="T") {
						return true;
					}
					else if (black.contains(C) & (E-B)==1 & F=="T") {
						return true;
					}
					// 2칸전진
					else if ((white.contains(C) & (B)==7) & (B-E)==2 & chessBoard[A][6]=="T") {
						return true;
					}
					else if ((black.contains(C) & (B)==2) &(E-B)==2 & chessBoard[A][3]=="T"){
						return true;
					}
					// 대각선에 무언가 있을 경우
					else if (Math.abs(Math.abs(A)-Math.abs(D))==1 & Math.abs(Math.abs(B) - Math.abs(E)) == 1) {
						if (white.contains(C) & B-E == 1) {
							return true;
						}
						else if(black.contains(C) & B-E == -1) {
							return true;
						}
						return false;
						}
				}
				else {
					return false;
				}
			}
			// 프로모션을 위해 움직이지않으면서 바꿀 말을 지정한 명령어를 받음
			// 프론트에서 프로모션의 경우 바로 진행되지않고 명령을 한차례 더 내리게 만들어야됨
			else if ( A == D & B == E & ((black.contains(C) & B == 8) | (white.contains(C) & B==1) )) {
				// 프로모션 구현 아마 여기서 하는것보다 프론트에서 하는게 나을듯
				if ((white.contains(C) & white.contains(F) | (black.contains(C) & black.contains(F)) & (F == "k" | F == "K"))) {
					if (white.contains(C) & (A-D)==0 & (B-E)==1 & F=="T") {
						return true;
					}
					else if (black.contains(C) & (A-D)==0 & (E-B)==1 & F=="T") {
						return true;
					}
					// 대각선에 무언가 있을 경우
					else if (Math.abs(Math.abs(A) - Math.abs(D)) == 1 & Math.abs(Math.abs(B) - Math.abs(E)) == 1) {
						if (white.contains(C) & B-E == 1) {
							return true;
						}
						else if(black.contains(C) & B-E == -1) {
							return true;
						}
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
			
		}
		return false;
	}

}
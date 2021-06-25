package baekjoon;

/***
 * 
 * <문제 요약>
 * 문제 정의 : N*N 크기의 체스판에 서로 공격할 수 없게 퀸을 놓을 수 있는 방법의 수를 구하라
 * 문제 유형 : 백트레킹
 * 제약 사항 : 퀸은 8방으로 다 이동이 가능하다.
 * <풀이 요약>
 * 1. 행에 각 열마다 퀸을 놓아본다.
 * 2. 퀸을 놓았을 때 문제가 없으면 다음 행으로 넘어간다.
 * 3. 마지막 N번째 까지 퀸을 다 놓았으면 ans를 +1 해준다.
 * 
 * 풀어본 문제여서 쉽게 생각했다가 놓치는 것들이 있어 시간이 생각외로 걸렸다... 집중합시다...
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_9663_NQueen {

	static int[][] map;
	static int N, ans;
	static int[] col;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		
		// 인덱스  : 행
		// 값 	: 열
		col = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			// 1행에 i열에 퀸 놓음
			col[1] = i;
			DFS(1);
			col[1] = 0;
		}
		
		System.out.println(ans);
		

	}

	private static void DFS(int row) {
		if(row == N) {
			ans++;
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			col[row+1] = i;
			if(isPossible(row+1)) {
				DFS(row+1);
			}
			col[row+1] = 0;
		}
		
	}

	private static boolean isPossible(int row) {
		// 이미 놓은 행에서
		for(int i = 1; i < row; i++) {
			// 같은 열에 놓은게 있는지 확인
			if(col[i] == col[row]) return false;
			// 대각선에 퀸이 있는지 확인
			if(Math.abs(col[i] - col[row]) == Math.abs(i - row)) return false;
			
		}
		// 없으면 놓을 수 있음.
		return true;
	}

}

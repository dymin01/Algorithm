package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : i => i로 이동할 수 있게 만들기 위해 추가해야 하는 최소한의 사다리 수, 안될경우 -1 
 * 문제 핵심 요약 : 
 * <풀이법 요약> 
 *  사다리의 빈곳에 사다리를 놓고 DFS로 풀어보기
 *  사다리 놓고 시뮬레이션 돌리기
 *  1. 새로 놓아야 하는 가로선의 갯수를 0 ~ 3개를 선택한다.
 *  2. DFS를 통해 가로 사다리를 놓는다.
 *  3. P개의 사다리를 다 놓았으면 사다리가 답인지 확인한다.
 *  5. 정답이면 정답을 출력하고 종료한다.
 */

public class BOJ_G4_15684_사다리조작 {
	
	static int N, H;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 세로선의 개수
		N = Integer.parseInt(st.nextToken());
		// 가로선의 개수
		int M = Integer.parseInt(st.nextToken());
		// 가로선을 놓을 수 있는 개수
		H = Integer.parseInt(st.nextToken());
		
		int[][] lader = new int[H+1][N+1];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			lader[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}
		
		// 0 ~ 3 개의 새로운 사다리 놓기
		for(int i = 0; i <= 3; i++) {
			DFS(i, 0, lader);
		}
		
		System.out.println(-1);
		
	}
	
	public static void DFS(int p, int cnt, int[][] lader) {
		
		if(cnt == p) {
			// p개의 사다리를 놓았으면 답인지 확인
			if(isAnswer(lader)) {
				System.out.println(cnt);
				System.exit(0);
			}
			return;
		}
		
		for(int i = 1; i <= H; i++) {
			for(int j = 1; j < N; j++) {
				if(lader[i][j-1] == 1 || lader[i][j] == 1 || lader[i][j+1] == 1) continue;
				lader[i][j] = 1;
				DFS(p, cnt+1, lader);
				lader[i][j] = 0;
			}
		}
		
		
	}

	private static boolean isAnswer(int[][] lader) {
		
		for(int j = 1; j <= N; j++) {
			int col = j;
			for(int i = 1; i <= H; i++) {
				int left = lader[i][col-1];
				int right = lader[i][col];
				
				if(left == 1) col--;
				if(right == 1) col++;
			}
			
			// 시작 col이 끝 col과 다르면 답이 아님 
			if(j != col) return false;
			
		}
		
		return true;
	}
}

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 항상 내리막길로만 이동하는 경로의 개수를 구하라
 * 
 * 문제 유형
 * - DP
 * - DFS/BFS
 * 
 * 제약 사항
 * M, N <= 500
 * 각 지점의 높이 <= 10000
 * 
 * <풀이 요약>
 * 욕심쟁이팬더문제랑 비슷하다고 생각해서 DP + DFS로 풀었다.
 * 
 * 1. 만약 이미 방문한 곳이라면 DP값을 반환한다.
 * 2. 4방향을 탐색한다.
 * 3. 범위 안에있고, 다음 높이가 더 낮으면 현재 장소의 DP값은 (현재 장소의 DP + 다음 장소의 DP) 이다.
 * 4. 마지막 지점이면 1을 반환한다. 
 * 5. 결과를 출력한다. 결과는 DP[0][0]의 값이다.
 * 
 */

public class BOJ_G4_1520_내리막길_DFS {
	
	static int[][] DP;
	static int[][] map;
	static boolean[][] V;
	static int M, N;

	// 상 우 하 좌
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		DP = new int[M][N];
		map = new int[M][N];
		V = new boolean[M][N];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = findWay(0, 0);
		
		System.out.println(answer);

	}

	private static int findWay(int r, int c) {
		
		// 마지막 지점이라면
		if(r == M-1 && c == N-1) return 1;
		// 이미 방문한 지점이면 DP값을 반환
		if(V[r][c]) return DP[r][c];
		
		V[r][c] = true;
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
			// 다음 지점의 높이가 낮다면
			if(map[nr][nc] < map[r][c]) {
				// 다음 지점의 값을 가져와 더한다.
				DP[r][c] += findWay(nr, nc);
			}
		}
		
		return DP[r][c];
		
	}

}

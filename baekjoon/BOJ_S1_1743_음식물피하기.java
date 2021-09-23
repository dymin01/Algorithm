package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 제일 큰 음식물의 크기를 구하라
 * 
 * 문제 유형
 * - BFS/DFS 그래프 탐색
 * 
 * 제약 사항
 * 1 <= N <= 100
 * 1 <= M <= 100
 * 1 <= K <= N*M
 * 
 * <풀이 요약>
 * 
 * 
 */

public class BOJ_S1_1743_음식물피하기 {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int[][] map;
	static boolean[][] visited;

	static int N, M, K;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		ans = 0;
		
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[r][c] = 1;
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				// 쓰레기가 있고 방문하지 않은 곳
				if(map[i][j] == 1 && !visited[i][j]) {
					BFS(i, j);
				}
			}
		}
		
		System.out.println(ans);
	}

	private static void BFS(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		visited[i][j] = true;
		int cnt = 1;
		queue.add(new int[] {i, j});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			// 4방 탐색
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위 확인
				if(nr < 1 || nr > N || nc < 1 || nc > M) continue;
				// 방문 또는 쓰레기가 있는지 확인.
				if(visited[nr][nc] || map[nr][nc] != 1) continue;
				
				cnt++;
				visited[nr][nc] = true;
				queue.add(new int[] {nr, nc});
				
			}
		}
		
		ans = Math.max(ans, cnt);
		
	}
	
	

}

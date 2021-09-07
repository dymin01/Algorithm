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
 * - 1,1 에서 N,M의 위치까지 최단경로를 구하라
 * 
 * 문제 유형
 * - DFS/BFS
 * 
 * 제약 사항
 * 1 <= N <= 1,000
 * 1 <= M <= 1,000
 * 
 * <풀이 요약>
 * 
 * 
 */

public class BOJ_G4_2206_벽부수고이동하기 {
	
	static class place{
		int r, c, dist, broke;

		public place(int r, int c, int dist, int broke) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.broke = broke;
		}
		
	}

	static int[][] map;
	static int[][] visited;
	static int answer;
	
	static int N, M;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M];
		answer = -1;
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		
		BFS(0, 0);
		
		if(answer == -1) System.out.println(answer);
		else System.out.println(answer);
		

	}

	private static void BFS(int sr, int sc) {
		Queue<place> queue = new LinkedList<>();
		queue.add(new place(sr, sc, 1, 0));
		visited[sr][sc] = 0;
		
		while(!queue.isEmpty()) {
			
			place cur = queue.poll();
			int r = cur.r;
			int c = cur.c;
			int dist = cur.dist;
			int broke = cur.broke;
			
			// 종료지점
			if(r == N-1 && c == M-1) {
				answer = dist;
				return;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				// 방문한 곳이면 넘어감
				if(visited[nr][nc] <= broke) continue;
				
				// 벽이 아닐때
				if(map[nr][nc] == 0) {
					
					visited[nr][nc] = broke;
					queue.add(new place(nr, nc, dist+1, broke));
				}
				// 벽일때
				else {
					// 벽을 부수고 지나갈 수 있으면
					if(broke == 0) {
						visited[nr][nc] = broke+1;
						queue.add(new place(nr, nc, dist+1, broke+1));
					}
					
				}
				
			}
			
			
		}
		
		
	}

}

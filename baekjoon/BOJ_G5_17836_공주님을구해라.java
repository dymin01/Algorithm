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
 * 공주에게 도달할 수 있는 최단 시간을 구하라.
 * 도달할 수 없다면 Fail을 출력하라.
 * 
 * 문제 유형
 * - 구현
 * - BFS/DFS
 * 
 * 제약 사항
 * 3 ≤ N
 * M ≤ 100
 * 1 ≤ T ≤ 10000
 * 
 * <풀이 요약>
 * 조건 확인 잘하자!
 * 
 * 1. BFS 탐색을 통해 다음 이동할 위치를 구한다.
 * 2. 만약 검을 가지고 있지 않다면
 * 2-1. 벽을 만나면 가지 못한다.
 * 2-2. 벽이 아니고 그냥 길이면 간다.
 * 2-3. 벽이 아니고 검을 만나면 검을 가진것으로 표시한다.
 * 3. 만약 검을 가지고 있다면
 * 3-1. 벽이든 그냥 길이든 그냥 갈 수 있다.
 * 
 * 주의!
 * cnt가 T보다 크면 답이 아니다...
 * 방문배열을 검을 가지고 있을때, 없을때로 나눠서 봐야 한다.
 * 
 */

public class BOJ_G5_17836_공주님을구해라 {

	static int N, M, T;
	
	static int[][] map;
	static boolean[][][] visited;
	
	static int answer = 0;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static class Knight{
		int r;
		int c;
		int sword;
		int cnt;
		
		public Knight(int r, int c, int sword, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.sword = sword;
			this.cnt = cnt;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M][2];
		
		answer = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		BFS(0,0);
		
		
		if(answer == Integer.MAX_VALUE)
			System.out.println("Fail");
		else
			System.out.println(answer);
		
	}

	private static void BFS(int sr, int sc) {
		
		Queue<Knight> queue = new LinkedList<>();
		
		// r c sword개수
		queue.add(new Knight(sr, sc, 0, 0));
		visited[0][0][0] = visited[0][0][1] = true;
		
		while(!queue.isEmpty()) {
			Knight cur = queue.poll();
			int r = cur.r;
			int c = cur.c;
			int sword = cur.sword;
			int cnt = cur.cnt;
			
			if(cnt > T) continue;
			
			if(r == N-1 && c == M-1) {
				answer = cnt;
				return;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위, 방문 체크 
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc][sword]) continue;
				

				// 검이 없으면
				if(sword == 0) {
					if(map[nr][nc] == 1) continue;
					// 방문처리
					visited[nr][nc][sword] = true;
					
					// 검을 발견했으면
					if(map[nr][nc] == 2) {
						queue.add(new Knight(nr, nc, sword+1, cnt+1));
					}
					// 그냥 길이면
					else {
						queue.add(new Knight(nr, nc, sword, cnt+1));
					}
					
				}
				// 검이 있으면
				else {
					visited[nr][nc][sword] = true;
					queue.add(new Knight(nr, nc, sword, cnt+1));
				}
				
			}
			
		}
		
	}

}

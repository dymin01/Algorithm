package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
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
 * 일반 Queue로 하면 답이 안나온다...
 * 높이로 우선순위큐로 만들어서 높이가 높은 순서로 먼저 확인한다. 그래야 돌아가는 경우도 판단할 수 있다.
 * 
 * 1. 시작지점에서 시작해서 BFS를 활용하여 높이가 높은 장소를 먼저 방문한다.
 * 2. 방문을 하면 count[nextR][nextC] += count[curR][curC] 해서 길의 경우의 수를 더한다.
 * 3. 만약 이미 방분한 장소이면 count만 더하고 큐에는 넣지 않는다.
 * 4. count[M-1][N-1] 지점의 결과를 출력한다.
 * 
 */

public class BOJ_G4_1520_내리막길_BFS {
	
	static int[][] count;
	static int[][] map;
	static boolean[][] V;
	static int M, N;

	// 상 우 하 좌
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static class pos implements Comparable<pos>{
		int r, c;
		int height;
		public pos(int r, int c, int height) {
			super();
			this.r = r;
			this.c = c;
			this.height = height;
		}
		@Override
		public int compareTo(pos o) {
			return o.height - this.height;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		count = new int[M][N];
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

	private static int findWay(int sr, int sc) {
		
		PriorityQueue<pos> queue = new PriorityQueue<>();
		// 시작지점 저장
		queue.offer(new pos(sr, sc, map[sr][sc]));
		V[sr][sc] = true;
		// 경우의 수 1
		count[sr][sc] = 1;
		
		while(!queue.isEmpty()) {
			
			pos cur = queue.poll();
			int r = cur.r;
			int c = cur.c;
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
				
				// 현재 높이보다 다음 높이가 낮을경우
				if(map[r][c] > map[nr][nc]) {
					// 방문을 했던 안했던 가는 길의 경우의 수 +1;
					count[nr][nc] += count[r][c];
					// 방문안했으면 큐에 추가
					if(!V[nr][nc]) {
						V[nr][nc] = true;
						queue.offer(new pos(nr, nc, map[nr][nc]));
					}
						
				}
				
			}
			
		}
		
		return count[M-1][N-1];
		
	}

}

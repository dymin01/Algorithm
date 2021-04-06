package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17086_아기상어2 {
	
	static class Shark{
		int r, c;

		public Shark(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static int N, M;
	
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		ArrayList<Shark> sharks = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					sharks.add(new Shark(i, j));
				}else {
					map[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		for(Shark s : sharks) {
			BFS(s.r, s.c);
		}
		
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				max = Math.max(max, map[i][j]);
			}
		}
		System.out.println(max-1);
		
		
	}
	
	static void BFS(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {sr, sc});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			for(int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if(map[nr][nc] > map[r][c] +1) {
					map[nr][nc] = map[r][c]+1;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}
}

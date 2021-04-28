package sw_expert_academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_D4_1868_파핑파핑지뢰찾기 {

	static int[][] map;
	static boolean[][] v;
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			Queue<int[]> list = new LinkedList<int[]>();
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				for(int j = 0; j < N; j++) {
					char st = str.charAt(j);
					if(st == '*') map[i][j] = -1;
					else if(st == '.') map[i][j] = -2;
				}
			}
			
			v = new boolean[N][N];
			
			Queue<int[]> queue = new LinkedList<>();
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == -1) {
						v[i][j] = true;
						continue;
					}
					int cnt = 0;
					for(int d = 0; d < 8; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						if (map[nr][nc] == -1) cnt++;
					}
					map[i][j] = cnt;
				}
			}
			
			int ans = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 0 && !v[i][j]) {
						ans++;
						queue.add(new int[] {i, j});
						v[i][j] = true;
						
						while(!queue.isEmpty()) {
							int[] cur = queue.poll();
							int r = cur[0];
							int c = cur[1];
							
							for(int d = 0; d < 8; d++) {
								int nr = r + dr[d];
								int nc = c + dc[d];
								if(nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc]) continue;
								v[nr][nc] = true;
								if(map[nr][nc] == 0) queue.add(new int[] {nr, nc});
							}
						}
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] > 0 && !v[i][j]) ans++;
				}
			}
			
			System.out.println("#" + t + " " + ans);
		}
		
	}

}

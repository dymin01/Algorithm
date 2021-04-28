package sw_expert_academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취 {

	static int N, M, C;
	
	static int[][] map;
	static boolean[][] find;
	static int[][] worker;
	static int total;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			worker = new int[2][M];
			find = new boolean[N][N];
			
			ans = Integer.MIN_VALUE;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			selectHoney(0, 0, 0);
			
			System.out.println("#" + t + " " + ans);
		}
		
		

	}

	private static void selectHoney(int cnt, int R, int C) {
		
		if(cnt == 2) {
			int res = 0;
			for(int i = 0; i < 2; i++) {
				total = 0;
				sumHoney(i, 0, 0, 0);
				res += total;
			}
			
			ans = Math.max(ans, res);
			
			return;
		}
		
		for(int r = R; r < N; r++) {
			int c = 0;
			if(r == R) {
				c = C;
			}
			for(; c < N; c++) {
				
				if(c + M > N || find[r][c]) continue;
				
				for(int i = 0; i < M; i++) {
					find[r][c+i] = true;
					worker[cnt][i] = map[r][c + i];
				}
				
				selectHoney(cnt + 1, r, c+M-1);
				
				for(int i = 0; i < M; i++) {
					find[r][c+i] = false;;
				}
				
			}
			
		}
		
	}

	private static void sumHoney(int idx, int cnt, int sum, int cost) {
		
		if(sum > C) return;
		
		if(cnt == M) {
			total = Math.max(total, cost);
			return;
		}
		
		sumHoney(idx, cnt+1, sum + worker[idx][cnt], cost + (worker[idx][cnt] * worker[idx][cnt]));
		sumHoney(idx, cnt+1, sum, cost);
		
	}

}

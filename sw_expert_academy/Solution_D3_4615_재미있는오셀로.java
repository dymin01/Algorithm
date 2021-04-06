package sw_expert_academy;
import java.util.Scanner;

public class Solution_D3_4615_재미있는오셀로 {
	
	static int[][] map;
	static int N;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			
			N = sc.nextInt();
			int M = sc.nextInt();
			
			map = new int[N][N];
			map[N/2-1][N/2-1] = 2;
			map[N/2-1][N/2] = 1;
			map[N/2][N/2-1] = 1;
			map[N/2][N/2] = 2;
			
			for(int m = 0; m < M; m++) {
				int R = sc.nextInt()-1;
				int C = sc.nextInt()-1;
				int color = sc.nextInt();
				put(R, C, color);
			}
			
			int b = 0;
			int w = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 1) {
						b++;
					}else if(map[i][j] == 2) {
						w++;
					}
				}
			}
			
			System.out.println("#" + t + " " + b + " " + w);
			
		}
	}

	private static void put(int r, int c, int color) {
		map[r][c] = color;
		for(int d = 0; d < 8; d++) {
			if(canChange(r, c, color, d)) {
				change(r, c, color, d);
			}
		}
		
	}

	private static void change(int r, int c, int color, int d) {
		int nr = 0;
		int nc = 0;
		for(int i = 0; i < N-1; i++) {
			nr = r + dr[d];
			nc = c + dc[d];
			if(map[nr][nc] == color) {
				return;
			}else {
				map[nr][nc] = color;
				r = nr;
				c = nc;
			}
		}
	}

	private static boolean canChange(int r, int c, int color, int d) {
		int nr = 0;
		int nc = 0;
		for(int i = 0; i < N-1; i++) {
			nr = r + dr[d];
			nc = c + dc[d];
			if(check(nr, nc)) {
				if(map[nr][nc] == color) {
					return true;
				}else if(map[nr][nc] == 0) {
					return false;
				}else {
					r = nr;
					c = nc;
				}
			}else {
				return false;
			}
		}
		return false;
	}
	
	public static boolean check(int r, int c) {
		return(r >= 0 && r < N && c >= 0 && c < N);
	}

}

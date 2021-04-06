package sw_expert_academy;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution_2117_홈방범서비스 {

	static int N, M, max;
	static int cntHom;
	
	static int[][] map;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {

			N = sc.nextInt();
			M = sc.nextInt();

			map = new int[N][N];
			ArrayList<int[]> homes = new ArrayList<>();
			cntHom = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1) {
						homes.add(new int[] { i, j });
						cntHom++;
					}
				}
			}
			
			max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(cntHom == max) break;
					go(i, j);
				}
			}
			
			System.out.println("#" + t + " " + max);
		}
	}
	
	public static void go(int r, int c) {
		// k = 1부터 맵 전체를 다 
		for(int k = 1; k <= (2*N)-1; k++) {
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(Math.abs(i-r) + Math.abs(j - c) <= k-1) {
						if(map[i][j] == 1) {
							cnt++;
						}
					}
				}
			}
			int cost = k * k + (k - 1) * (k - 1);
			if(cost <= cnt * M) {
				max = max < cnt ? cnt : max;
			}
		}
	}

}

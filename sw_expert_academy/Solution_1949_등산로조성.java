package sw_expert_academy;
import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 주어진 K값을 이용하여 가장 긴 등산로를 만들어라
 * 요구 개념/문제 유형 : DFS
 * 
 * <풀이법 요약>
 * 1. map을 저장할때 가장 높은곳의 크기를 찾아놓고 높은곳에서 DFS를 출발한다.
 * 2. 4방탐색을 하면서 만약 낮을경우 그냥 지나가고 높을경우 K를 사용해서 지나갈 수 있는지 확인한다.
 * -> 만약 앞에서 이미 K를 사용했을경우 못지나가므로 그냥 끝난다.
 * 3. 길이를 비교해 가장 긴 등산로를 출력한다.
 */

public class Solution_1949_등산로조성 {

	static int N, K;
	static int[][] map;
	static boolean[][] v;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			K = sc.nextInt();

			map = new int[N][N];
			ans = Integer.MIN_VALUE;

			int max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					max = max < map[i][j] ? map[i][j] : max;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) {
						v = new boolean[N][N];
						v[i][j] = true;
						DFS(i, j, 1, K);
					}
				}
			}
			System.out.println("#" + t + " " + ans);
		}
		sc.close();
	}

	public static void DFS(int r, int c, int len, int k) {
		ans = Math.max(ans, len);
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			if (v[nr][nc])
				continue;
			// k가 필요없는경우
			if (map[nr][nc] < map[r][c]) {
				v[nr][nc] = true;
				DFS(nr, nc, len + 1, k);
				v[nr][nc] = false;
			}
			// k가 필요한경우
			else if (map[nr][nc] >= map[r][c] && k != 0) {
				if (map[nr][nc] - k < map[r][c]) {
					int temp = map[nr][nc];
					int dif = map[nr][nc] - map[r][c];
					map[nr][nc] = map[nr][nc] - dif - 1;
					v[nr][nc] = true;
					DFS(nr, nc, len + 1, 0);
					v[nr][nc] = false;
					map[nr][nc] = temp;
				}
			}
		}
	}
}

package baekjoon;
import java.util.Scanner;
/***
 * <문제 요약> 
 * 구해야 하는 것 : 붙인 색종이의 둘레를 구하기
 * 문제 핵심 요약 : 1일경우 주변의 0의 갯수를 구한다.
 * <풀이법 요약> 
 * 1. 색종이를 붙일때 배열을 1로 바꾼다.
 * 2. 배열을 돌면서 1일 경우 사방을 탐색해서 0의 갯수를 구한다.
 */
public class BOJ_S5_2567 {

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map = new int[102][102];
		int ans = 0;
		for (int t = 0; t < N; t++) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					map[y + i][x + j] = 1;
				}
			}
		}
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				if (map[i][j] == 1) {
					for (int k = 0; k < 4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if (nr >= 0 && nr <= 100 && nc >= 0 && nc <= 100 && map[nr][nc] == 0) ans++;
					}
				}
			}
		}

		System.out.println(ans);

	}

}

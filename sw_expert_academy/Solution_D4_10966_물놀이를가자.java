package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_10966_물놀이를가자 {

	static char[][] map;
	static int[][] cntmap;
	static boolean[][] v;

	static int N, M;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringTokenizer st = null;

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			cntmap = new int[N][M];
			v = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				map[i] = str.toCharArray();
			}
			Queue<int[]> queue = new LinkedList<int[]>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 'W') {
						queue.offer(new int[] {i, j, 0});
					}
				}
			}
			BFS(queue);
			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 'L')
						ans += cntmap[i][j];
				}
			}
			System.out.println("#" + t + " " + ans);
		}
	}
	
	public static void BFS(Queue<int[]> queue) {

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];

				if (nr < N && nr >= 0 && nc < M && nc >= 0) {
					if (map[nr][nc] == 'L') {
						if (cntmap[nr][nc] == 0) {
							cntmap[nr][nc] = cur[2] + 1;
							queue.offer(new int[] { nr, nc, cur[2] + 1 });
						}
					}
				}
			}
		}
	}
}

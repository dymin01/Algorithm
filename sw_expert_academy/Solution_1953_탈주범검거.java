package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거 {
	
	static int N, M, R, C, L;

	static int[][] map;
	static boolean[][] v;
	
	//상 우 하 좌
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int[][] Tunnel = {
			//상 우 하 좌
			{0, 0, 0, 0},
			{1, 1, 1, 1},
			{1, 0, 1, 0},
			{0, 1, 0, 1},
			{1, 1, 0, 0},
			{0, 1, 1, 0},
			{0, 0, 1, 1},
			{1, 0, 0, 1},
	};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			v = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			BFS(R, C, L);
			int ans = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(v[i][j]) ans++;
				}
			}
			
			System.out.println("#" + t + " " + ans);
			
		}
	}
	
	public static void BFS(int R, int C, int L) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {R, C, 1});
		v[R][C] = true;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int cnt = cur[2];
			if(cnt == L) break;
			for(int d = 0; d < 4; d++) {
				if(Tunnel[map[r][c]][d] == 0) continue;
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if(map[nr][nc] == 0) continue;
					if(v[nr][nc]) continue;
					
					if(Tunnel[map[nr][nc]][(d + 2) % 4] == 1) {
						queue.offer(new int[] {nr, nc, cnt+1});
						v[nr][nc] = true;
					}
				}
			}
		}
	}
}

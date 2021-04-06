package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_1600_말이되고픈원숭이 {
	
	static class mar{
		int r;
		int c;
		int k;
		int cnt;
		public mar(int r, int c, int k, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
			this.cnt = cnt;
		}
		
	}

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int[] mdr = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] mdc = {-2, -1, 1, 2, 2, 1, -1, -2};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[H][W];
		boolean[][][] v = new boolean[H][W][K+1];
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = Integer.MAX_VALUE;
		Queue<mar> queue = new LinkedList<>();
		queue.add(new mar(0, 0, K, 0));
		v[0][0][K] = true;
		boolean isfind = false;
		while(!queue.isEmpty()) {
			mar cur = queue.poll();
			int r = cur.r;
			int c = cur.c;
			int k = cur.k;
			int cnt = cur.cnt;
			
			if(r == H-1 && c == W-1) {
				ans = Math.min(ans, cnt);
				isfind = true;
				continue;
			}
			
			// 4방탐색
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
				if(v[nr][nc][k] || map[nr][nc] == 1) continue;
				v[nr][nc][k] = true;
				queue.add(new mar(nr, nc, k, cnt+1));
			}
			// 나이트 움직임
			if(k != 0) {
				for(int d = 0; d < 8; d++) {
					int nr = r + mdr[d];
					int nc = c + mdc[d];
					if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
					if(v[nr][nc][k-1] || map[nr][nc] == 1) continue;
					v[nr][nc][k-1] = true;
					queue.add(new mar(nr, nc, k-1, cnt+1));
				}
			}
		}
		if(isfind) System.out.println(ans);
		else System.out.println(-1);
	}
}

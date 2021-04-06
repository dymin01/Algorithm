package jungol;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUNGOL_1661_미로탈출로봇_DFS {
	
	static int Y, X;
	static int min;
	static int[][] map;
	static boolean[][] v;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		map = new int[Y][X];
		v = new boolean[Y][X];
		min = Integer.MAX_VALUE;

		st = new StringTokenizer(br.readLine());
		int sc = Integer.parseInt(st.nextToken()) - 1;
		int sr = Integer.parseInt(st.nextToken()) - 1;
		int ec = Integer.parseInt(st.nextToken()) - 1;
		int er = Integer.parseInt(st.nextToken()) - 1;
		
		for(int i = 0; i < Y; i++) {
			String str = br.readLine();
			for(int j = 0; j < X; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		v[sr][sc] = true;
		DFS(sr, sc, er, ec, 0);
		
		System.out.println(min);
		
	}
	
	static void DFS(int sr, int sc, int tr, int tc, int cnt) {
		if(cnt > min) return;
		if(sr == tr && sc == tc) {
			min = Math.min(min, cnt);
			return;
		}
		
		for(int d = 0; d < 4; d++) {
			int nr = sr + dr[d];
			int nc = sc + dc[d];
			if(nr < 0 || nr >= Y || nc < 0 || nc >= X) continue;
			if(map[nr][nc] == 1 || v[nr][nc]) continue;
			v[nr][nc] = true;
			DFS(nr, nc, tr, tc, cnt+1);
			v[nr][nc] = false;
		}
	}
}

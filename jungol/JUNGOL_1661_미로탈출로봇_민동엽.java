package jungol;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUNGOL_1661_미로탈출로봇_민동엽 {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[Y][X];
		int min = Integer.MAX_VALUE;

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
		
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {sr, sc});
		map[sr][sc] = 1;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(r == er && c == ec) {
				min = Math.min(min, map[r][c]);
				break;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nr >= Y || nc < 0 || nc >= X) continue;
				if(map[nr][nc] != 0) continue;
				map[nr][nc] = map[r][c] +1;
				queue.add(new int[] {nr, nc});
			}
		}
		
		System.out.println(min-1);
		
	}
}

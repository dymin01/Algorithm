package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1767_프로세스연결하기{

	static int N;
	
	static int[][] map;
	static ArrayList<int[]> list;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int cores, cntCores, ans;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N+2][N+2];
			list = new ArrayList<>();
			cores = 0;
			cntCores = 0;
			ans = Integer.MAX_VALUE;
			
			for(int i = 0; i < map.length; i++) {
				Arrays.fill(map[i], 3);
			}
			for(int i = 1; i <= N; i++ ) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());					
					if(i == 1 || i == N || j == 1 || j == N) continue;
					if(map[i][j] == 1) {
						list.add(new int[] {i, j});
						cores++;
					}
				}
			}
			
			DFS(0, 0, 0);
			System.out.println("#" + t + " " + ans);
		}
		
	}
	
	public static void DFS(int idx, int len, int cnt) {
		
		if(len > ans) return;
		if(idx == cores) {
			if(cnt >= cntCores) {
				cntCores = cnt;
				ans = Math.min(ans, len);
			}
			return;
		}
		
		int[] cur = list.get(idx);
		int d_check = 0;
		for(int d = 0; d < 4; d++) {
			if(check(cur[0], cur[1], d)) {
				int temp = put(cur[0], cur[1], d, 2);
				DFS(idx+1, len + temp, cnt+1);
				put(cur[0], cur[1], d, 0);
			}else {
				d_check++;
			}
		}
		if(d_check > 3) {
			DFS(idx+1, len, cnt);
		}
	}
	
	public static int put(int r, int c, int d, int ch) {
		int len = 0;
		for(int i = 0; i < N+1; i++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(map[nr][nc] == 3) break;
			map[nr][nc] = ch;
			if(ch == 2) len++;
			
			r = nr;
			c = nc;
		}
		return len;
	}
	
	
	public static boolean check(int r, int c, int d) {
		
		for(int i = 0; i < N+1; i++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(map[nr][nc] == 1 || map[nr][nc] == 2) return false;
			
			if(map[nr][nc] == 3) {
				return true;
			}
			r = nr;
			c = nc;
		}
		
		return false;
	}

}

package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution_5656_벽돌깨기_live교수님2 {

	static class Point{
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int N, W, H, min;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[H][W];
			
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			
			go(0, map);
			
			System.out.println("#" + t + " " + min);
		}
	}

	// 중복순열로 구슬 떨어뜨리기
	// cnt : 구슬을 떨어뜨린 횟수
	// boolean : ture 면 모두 깬 상황
	private static boolean go(int cnt, int[][] map) {
		int result = getRemain(map);
		if(result == 0) {	// 모두 빈칸
			min = 0;
			return true;
		}
		
		if(cnt == N) {
			min = Math.min(min, result);
			return false;
		}

		int[][] newMap = new int[H][W];
		
		// 매열마다 구슬 떨어뜨리는 시도
		for(int c = 0; c < W; c++) {
			//해당열에 구슬을 떨어뜨려 맞는 벽돌 찾기.
			int r = 0;
			while(r < H && map[r][c] == 0) ++r;
			
			// 맞는 벽돌이 없음(모두 빈칸)
			if(r == H) {
				//go(cnt+1, map);
				continue;	// 다음열에 떨어뜨리기
			}else {
				// 기존 cnt-1 구슬까지의 상태로 초기화
				copy(map, newMap);
				// 벽돌 터지기
				boom(newMap, r, c);
				// 벽돌 내리기
				down(newMap);
				// 다음 구슬 던지기
				if(go(cnt+1, newMap)) return true;
			}
		}
		
		return false;
		
	}

	private static int getRemain(int[][] map) {
		
		int count = 0;
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(map[i][j] > 0) count++;
			}
		}
		
		
		return count;
	}

	private static void down(int[][] map) {
		// 자료구조로 써보기....
		for(int c = 0; c < W; c++) {
			int r = H - 1;
			while(r >= 0 && map[r][c] == 0) --r;
			if(r < 0) continue;	// 모든칸이 빈칸
			
			r = H - 1;
			while(r > 0) {	// 자신의 위치 내리기
				if(map[r][c] == 0) {	// 빈칸이면
					// 직전부터 위쪽 탐색하며 가장 가까운 벽돌 찾기
					int nr = r - 1;
					while(nr > 0 && map[nr][c] == 0) --nr;
					map[r][c] = map[nr][c];
					map[nr][c] = 0;
				}
				--r;
			}
			
		}
		
		
	}

	private static void boom(int[][] map, int r, int c) {
		
		Queue<Point> queue = new LinkedList<Point>();
		if(map[r][c] > 1) {
			queue.offer(new Point(r, c, map[r][c]));
		}
		map[r][c] = 0;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				for(int k = 1; k < p.cnt; k++) {
					nr += dr[d];
					nc += dc[d];
					if(nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] != 0) {
						if(map[nr][nc] > 1) {
							queue.offer(new Point(nr, nc, map[nr][nc]));
						}
						map[nr][nc] = 0;
					}
					
				}
				
			}
		}
		
		
		
	}

	private static void copy(int[][] map, int[][] newMap) {
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}
		
}

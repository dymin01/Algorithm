package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_16236_아기상어 {

	static class info{
		int r, c;
		public info(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		int[][] v = new int[N][N];

		int sharkR = 0;
		int sharkC = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					sharkR = i;
					sharkC = j;
				}
			}
		}
		
		int ans = 0;
		
		int sharkSize = 2;
		int sharkCnt = 0;
		int eat_cnt = 0;
		while(true) {
			int min_r = 987654321;
			int min_c = 987654321;
			int min_dis = 987654321;
			v = new int[N][N];
			for(int i = 0; i < N; i++) {
				Arrays.fill(v[i], -1);
			}
			Queue<info> queue = new LinkedList<>();
			queue.offer(new info(sharkR, sharkC));
			v[sharkR][sharkC] = 0;
			while(!queue.isEmpty()) {
				info cur = queue.poll();
				int r = cur.r;
				int c = cur.c;
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					if(v[nr][nc] != -1 || map[nr][nc] > sharkSize) continue;
					
					v[nr][nc] = v[r][c] + 1;
					
					if(map[nr][nc] != 0 && map[nr][nc] < sharkSize) {
						if(min_dis > v[nr][nc]) {
							min_dis = v[nr][nc];
							min_r = nr;
							min_c = nc;
						}
						
						else if( min_dis == v[nr][nc]) {
							if(min_r == nr) {
								if(min_c > nc) {
									min_r = nr;
									min_c = nc;
								}
							}
							else if(min_r > nr) {
								min_r = nr;
								min_c = nc;
							}
						}
					}
					queue.offer(new info(nr, nc));
				}
			}
			
			if(min_r != 987654321) {
				ans += v[min_r][min_c];
				
				eat_cnt++;
				
				if(eat_cnt == sharkSize) {
					sharkSize++;
					eat_cnt = 0;
				}
				
				map[min_r][min_c] = 0;
				sharkR = min_r;
				sharkC = min_c;
			}
			else {
				break;
			}
		}
		
		System.out.println(ans);
	}
}

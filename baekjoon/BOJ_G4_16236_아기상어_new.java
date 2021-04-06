package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_16236_아기상어_new {

	static class fish{
		int r, c, size;

		public fish(int r, int c, int size) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
		}
	}
	
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		int[][] map = new int[N][N];
		int[][] v = new int[N][N];
		
		fish shark = null;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new fish(i, j, 2);
				}
			}
		}
		
		int eat = 0;
		int ans = 0;
		map[shark.r][shark.c] = 0; 
		while(true) {
			
			int min_r = 987654321;
			int min_c = 987654321;
			int min_dis = 987654321;
			
			for(int i = 0; i < N; i++) {
				Arrays.fill(v[i], -1);
			}
			
			Queue<fish> queue = new LinkedList<>();
			queue.offer(shark);
			v[shark.r][shark.c] = 0;
			
			while(!queue.isEmpty()) {
				fish cur = queue.poll();
				int r = cur.r;
				int c = cur.c;
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					if(v[nr][nc] != -1 || map[nr][nc] > shark.size) continue;
					
					v[nr][nc] = v[r][c] + 1;
					
					if(map[nr][nc] > 0 && map[nr][nc] < shark.size) {

						if(min_dis > v[nr][nc]) {
							min_dis = v[nr][nc];
							min_r = nr;
							min_c = nc;
						}
						else if(min_dis == v[nr][nc]) {
							if(min_r == nr) {
								if(min_c > nc) {
									min_r = nr;
									min_c = nc;
								}
							}else if(min_r > nr) {
								min_r = nr;
								min_c = nc;
							}
						}
						
					}
					queue.offer(new fish(nr, nc, 0));
				}
			}
			
			if(min_r != 987654321) {
				ans += v[min_r][min_c];
				if(++eat == shark.size) {
					eat = 0;
					shark.size++;
				}
				
				map[min_r][min_c] = 0;
				shark.r = min_r;
				shark.c = min_c;
			}
			else {
				break;
			}
			
		}
		
		System.out.println(ans);
	}

}

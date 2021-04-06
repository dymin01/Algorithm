package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_2636_치즈 {

	static int[][] map;
	static int[][] sub;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		sub = new int[N][M];
		for(int i = 0; i < N; i++ ) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sub[i][j] = map[i][j];
			}
		}
		int time = -1;
		int cnt = 0;
		int precnt = 0;
		while(true) {
			time++;
			cnt = 0;
			boolean isbreak = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 0) {
						fill(i, j);
						isbreak = true;
						break;
					}
				}
				if(isbreak) break;
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 1) {
						cnt++;
						for(int d = 0; d < 4; d++) {
							int nr = i + dr[d];
							int nc = j + dc[d];
							if(map[nr][nc] == 2) {
								map[i][j] = 3;
								break;
							}
						}
					}
				}
			}
			if(cnt == 0) {
				break;
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 3) {
						map[i][j] = 2;
					}
				}
			}
			precnt = cnt;
		}
		System.out.println(time);
		System.out.println(precnt);
	}
	
	static void fill(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr, sc});
		map[sr][sc] = 2;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(map[nr][nc] == 0) {
					map[nr][nc] = 2;
					queue.add(new int[] {nr, nc});
				}
			}
			
		}
	}

}

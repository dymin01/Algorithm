package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MAin_BOJ_2636_치즈 {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int map[][] = new int[H][W];
		int numOfCheese = 0;
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) numOfCheese++;
			}
		}
		
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {0,0});
		map[0][0] = 2;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for(int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if(nr >= 0 && nr < H && nc >= 0 && nc < W) {
					if(map[nr][nc] == 0) {
						queue.offer(new int[] {nr,nc});
						map[nr][nc] = 2;
					}
				}
			}
		}
		int time = 0;
		while(numOfCheese > 0) {
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					int cnt = 0;
					for(int d = 0; d < 4; d++) {
						int nr = i+dr[d];
						int nc = j+dc[d];
						if(nr >= 0 && nr < H && nc >= 0 && nc < W) {
							if(map[i+dr[d]][j+dc[d]] == 2) {
								cnt++;
							}
						}
					}
					if(cnt >= 2) {
						map[i][j] = 2;
						numOfCheese--;
					}
				}
			}
			time++;
		}
		System.out.println(time);
	}
}

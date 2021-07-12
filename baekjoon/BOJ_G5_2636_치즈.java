package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 시간이 지나 치즈가 모두 없어지는 시간과, 없어지기 1시간 전에 치즈의 개수를 구하라
 * 
 * 문제 유형
 * - DFS/BFS
 * - 시뮬레이션
 * 
 * 제약 사항
 * - 가로 세로 최대 100
 *  
 * <풀이 요약>
 * 1. 밖에있는 공기인지 체크한다.
 * 2. 치즈중에 밖에있는 공기와 만나고 있으면 녹인다.
 * 3. 녹은 자리의 공기도 밖에 공기라고 체크한다.
 * 4. 1~3번을 반복하면서 치즈의 개수를 저장한다.
 * 5. 치즈가 다 없어지는 시간을 구한다.
 * 
 */


public class BOJ_G5_2636_치즈 {

	static boolean[][] isAir;
	static int[][] map;
	
	static int height, width;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		isAir = new boolean[height][width];
		map = new int[height][width];
		
		int precheese = 0;
		int cheese = 0;
		int ans = 0;
		
		for(int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < width; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) cheese++;
			}
		}
		isAir[0][0] = true;
		checkAir();
		
		precheese = cheese;
		while(true) {
			precheese = cheese;
			cheese = checkMeltCheese();	
			if(cheese == 0) {
				break;
			}
			ans++;
			checkAir();
		}
		
		System.out.println(ans);
		System.out.println(precheese);
		
	}
	
	public static void checkAir() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {0,0});
		
		boolean[][] checked = new boolean[height][width];
		checked[0][0] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= height || nc < 0 || nc >= width || map[nr][nc] == 1 || checked[nr][nc]) continue;
				
				if(!isAir[nr][nc]) {
					isAir[nr][nc] = true;
				}
				checked[nr][nc] = true;
				queue.add(new int[] {nr, nc});
			}
		}
	}
	
	public static int checkMeltCheese() {
		int res = 0;
		
		int[][] temp = new int[height][width];
		boolean[][] tempAir = new boolean[height][width];
		
		for(int r = 0; r < height; r++) {
			for(int c = 0; c < width; c++) {
				if (map[r][c] == 1) {
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if(nr < 0 || nr >= height || nc < 0 || nc >= width || !isAir[nr][nc]) continue;
						map[r][c] = -1;
						tempAir[r][c] = true;
						res++;
						break;
					}
					
				}
				tempAir[r][c] = isAir[r][c];
				temp[r][c] = (map[r][c] == 1 ? 1 : 0);
			}
		}
		isAir = tempAir;
		map = temp;
		return res;
	}
	
}

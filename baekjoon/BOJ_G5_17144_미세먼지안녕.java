package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: t시간이 지난 후 미세먼지의 상황
 * 제약 사항: 미세먼지의 확산이 일어나고, 공지청정기가 작동한다.
 * 문제 유형: 시뮬레이션
 * 요구 개념: 구현
 * 
 * <풀이법 요약>
 * 1. 미세먼지 확산 -> 공기청정기 작동 --> 1초
 * 2. 공기청정기는 위에는 반시계방향, 아래는 시계방향으로 바람이 움직인다.
 * 
 */

public class BOJ_G5_17144_미세먼지안녕 {

	static int R, C, T;
	
	static int[][] map;
	static int[][] temp;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	// 반시계방향,
	static int[][] drr = {{0, -1, 0, 1}, {0, 1, 0, -1}};
	static int[][] dcc = {{1, 0, -1, 0}, {1, 0, -1, 0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		int sr = 0;
		int sc = 0;
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					sr = i;
					sc = j;
				}
			}
		}
		sr--;
		
		for(int t = 0; t < T; t++) {
			print();
			temp = new int[R][C];
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(map[i][j] > 0) {
						diffusion(i, j);
					}
				}
			}
			// 확산 적용
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					map[i][j] += temp[i][j];
				}
			}
			
			// 공기청정기 작동
			// 0일때는 반시계, 1일때는 시계방향
			for(int i = 0; i < 2; i++) {
				int r = sr + i;
				int c = sc+1;
				int d = 0;
				int pre = map[r][c];
				map[r][c] = 0;
				while(true) {
					int nr = r + drr[i][d];
					int nc = c + dcc[i][d];
					if(nr == sr+i && nc == sc) break;
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
						d++;
						continue;
					}
					int temp = map[nr][nc];
					map[nr][nc] = pre;
					pre = temp;
					
					r = nr;
					c = nc;
				}
			}
		}
		int ans = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				ans += map[i][j];
			}
		}
		System.out.println(ans+2);

	}

	private static void diffusion(int i, int j) {
		
		int Arc = map[i][j]/5;
		int cnt = 0;
		
		for(int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			if(map[nr][nc] == -1) continue;
			cnt++;
			temp[nr][nc] += Arc;
		}
		
		temp[i][j] -= Arc * cnt;
		
	}
	
	public static void print() {
		System.out.println();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}

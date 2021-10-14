package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_G5_21610_마법사상어와비바라기 {

	static int N, M;
	
	static int[][] map;
	static LinkedList<int[]> cloud;
	// 1부터 시작
	static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	
	// 좌상 우상 우하 좌하
	static int[] ddr = {-1, -1, 1, 1};
	static int[] ddc = {-1, 1, 1, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		
		// map 초기화
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cloud = new LinkedList<>();
		cloud.add(new int[] {N, 1});
		cloud.add(new int[] {N, 2});
		cloud.add(new int[] {N-1, 1});
		cloud.add(new int[] {N-1, 2});
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			// 1. move
			moveCloud(d, s);
			
			// 2. 구름에서 비를 내린다.
			rain();
			// 3. 구름이 사라진다.
			
			// 4. 물이 증가한 칸에서 물복사 버그가 일어난다.
			copyBug();
			
			// 5. 구름만들기
			makeCloud();
			
		}
		
		// 6. 물의 합 구하기.
		int answer = countAll();
		
		System.out.println(answer);

	}

	private static int countAll() {
		
		int tot = 0;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				tot += map[i][j];
			}
		}
		
		return tot;
	}

	private static void makeCloud() {
		
		boolean[][] noCloud = new boolean[N+1][N+1];
		for(int[] c : cloud) {
			noCloud[c[0]][c[1]] = true;
		}
		
		cloud = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				
				if(map[i][j] >= 2 && !noCloud[i][j]) {
					cloud.add(new int[] {i, j});
					map[i][j] -= 2;
				}
			}
		}
	}

	private static void copyBug() {
		
		int[][] temp = new int[N+1][N+1];
		
		for(int[] cur : cloud) {
			int r = cur[0];
			int c = cur[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + ddr[d];
				int nc = c + ddc[d];
				// 범위체크
				if(nr <= 0 || nr > N || nc <= 0 || nc > N) continue;
				
				// 물이 있다면
				if(map[nr][nc] != 0) {
					temp[r][c]++;
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j <= N; j++) {
				map[i][j] += temp[i][j];
			}
		}
		
	}

	private static void rain() {
		
		for(int[] cur : cloud) {
			map[cur[0]][cur[1]] += 1;
		}
		
	}

	private static void moveCloud(int d, int s) {
		
		int size = cloud.size();
		for(int i = 0; i < size; i++) {
			int[] cur = cloud.poll();
			int r = cur[0];
			int c = cur[1];
			
			int nr = r + (dr[d] * s);
			int nc = c + (dc[d] * s);
			
			r = (nr % N) == 0 ? N : (nr % N);
			c = (nc % N) == 0 ? N : (nc % N);
			
			if(r < 0) r = (nr % N) + N;
			if(c < 0) c = (nc % N) + N;
			
			cloud.add(new int[] {r, c});
		}
		
		
	}

}

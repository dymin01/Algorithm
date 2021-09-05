package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 마법사 상어가 파이어스톰을 Q번 사용하고 남아있는 얼음의 합, 가장 큰 덩어리가 차지하는 칸의 개수.
 * 
 * 문제 유형
 * - 귀찮은 시뮬
 * - DFS/BFS
 * 
 * 제약 사항
 * 
 * <풀이 요약>
 * 
 * 귀찮...
 * 순서 1. 2^L * 2^L 크기의 부분 격자를 시계방향으로 90도 회전한다.
 * 순서 2. 인접해있는 칸에 얼음이 2개 이하면 얼음을 -1한다.
 * 순서 3. Q번 반복한다.
 * 순서 4. 남아있는 얼음의 총합 구하기.
 * 순서 5. 가장 큰 얼음덩어리 크기 구하기.
 * 
 * ..... 얼음덩어리가 없으면 0을 출력한다.....둘다 0으로 출력하면 된다...
 * 
 */

public class BOJ_G4_20058_마법사상어와파이어스톰 {

	static int[][] map;
	static int size;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		size = (int)Math.pow(2, N);
		map = new int[size][size];
		
		// map 초기화
		for(int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// Q번 반복한다.
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			
			// 1. 2^L사이즈로 격자를 나누고 시계 방향으로 90도 회전.
			rotation(L);
			// 2. 인접해 있는 칸에 얼음이 2개 이하면 얼음--;
			checkIce();
		}
		int sum = sumIce();
		visited = new boolean[size][size];
		// sum이 0이면 얼음 덩어리가 존재하지 않는다.
		// 가장 큰 얼음 덩어리의 크기를 구한다.
		int maxSizeIce = findMaxIce();
		
		System.out.println(sum);
		System.out.println(maxSizeIce);

	}

	private static int findMaxIce() {
		int ans = 0;
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				// 이미 방문했으면 continue
				if(visited[r][c]) continue;
				// 얼음이 있으면.
				if(map[r][c] > 0) {
					visited[r][c] = true;
					ans = Math.max(BFS(r,c), ans);
				}
			}
		}
		
		return ans;
		
	}

	private static int BFS(int sr, int sc) {
		
		int cnt = 1;
		
		Queue<int[]> queue = new LinkedList<>();
		
		queue.add(new int[] {sr, sc});
		
		while(!queue.isEmpty()) {
			
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위 체크
				if(nr < 0 || nr >= size || nc < 0 || nc >= size) continue;
				// 방문 체크
				if(visited[nr][nc]) continue;
				
				// 얼음이 있으면
				if(map[nr][nc] > 0) {
					visited[nr][nc] = true;
					cnt++;
					queue.add(new int[] {nr, nc});
				}
				
			}
			
		}
		
		return cnt;
	}

	private static void checkIce() {
		
		int[][] next = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			next[i] = Arrays.copyOf(map[i], size);
		}
		
		
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				int cnt = 0;
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					// 범위 체크
					if(nr < 0 || nr >= size || nc < 0 || nc >= size) continue;
					// 얼음이 0보다 크면 cnt++;
					if(map[nr][nc] > 0) cnt++;
					
				}
				// 인접한 얼음의 개수가 2개 이하면 얼음--
				if(cnt < 3) next[r][c]--;
			}
		}
		
		map = next;
		
	}

	private static void rotation(int L) {
		
		int smallSquareSize = (int)Math.pow(2, L);
		
		int[][] next = new int[size][size];
		
		for(int r = 0; r < size; r += smallSquareSize) {
			for(int c = 0; c < size; c += smallSquareSize) {
				// 나눈곳을 90도 회전.
				for(int i = 0; i < smallSquareSize; i++) {
					for(int j = 0; j < smallSquareSize; j++) {
						next[r+j][c-i + smallSquareSize - 1] = map[r+i][c+j];
					}
				}
				
			}
		}
		map = next;
	}
	
	public static int sumIce() {
		int sum = 0;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(map[i][j] > 0)
					sum += map[i][j];
			}
		}
		
		return sum;
	}


}

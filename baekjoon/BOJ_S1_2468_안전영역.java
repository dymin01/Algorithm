package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 안전지대가 최대인 경우
 * 제약 사항: N은 2이상 100이하
 * 문제 유형: DFS, BFS, 브루트포스
 * 요구 개념: DFS, BFS
 * 
 * <풀이법 요약>
 * 1. 높이의 최소값과 최대값을 구해 높이의 구간을 정한다.
 * 2. 높이를 바꿔가며 BFS를 통해 안전지대를 찾는다.
 * 3. 안전지대의 갯수를 구해 최대값인지 비교해본다.
 * 4. 최대값을 출력한다.
 * 
 */


public class BOJ_S1_2468_안전영역 {

	static int maxcnt;
	static int N, cnt;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];

		maxcnt = 1;
		
		// min 높이가 가장 낮은곳, max 높이가 가장 높은곳
		int min = 100;
		int max = 0;
		StringTokenizer st = null;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
			}
		}
		
		// 가장 작은높이부터 가장 높은 높이까지
		for(int n = min; n <= max; n++) {
			// 방문 배열
			boolean[][] v = new boolean[N][N];
			//높이가 n일때 안전지대의 개수
			cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] <= n || v[i][j]) continue;
					// 안전지대를 체크
					cnt++;
					BFS(i, j, n, map, v);
				}
			}
			// 안전지대의 최대값을 구한다.
			maxcnt = Math.max(cnt, maxcnt);
		}
		
		System.out.println(maxcnt);
		
	}
	
	public static void BFS(int i, int j, int n,int[][] map, boolean[][] v) {
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {i, j});
		v[i][j] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc]) continue;
				if(map[nr][nc] <= n) continue;
				v[nr][nc] = true;
				
				queue.add(new int[] {nr, nc});
				
			}
			
		}
	}

}

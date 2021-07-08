package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 판다가 가장 오래 살 수 있는 일수를 구해라
 * 
 * 문제 유형
 * - DP
 * - DFS/BFS
 * 
 * 제약 사항
 * - 1 <= n <= 500
 * 대나무의 양은 1,000,000 보다 작거나 같은 자연수
 * 
 * <풀이 요약>
 * 오 한번에 맞춤 대단 칭찬해줘여
 * 
 * 1. DP에 1로 초기화 아무곳도 못움직이면 1일이 최대기 때문에
 * 2. 2중 for문을 돌면서 DP가 1이면 DFS를 돌아 DP 값을 바꿔준다.
 * 3. DFS로 탐색한다.
 * 	3-1. 4방을 탐색하면서 범위를 넘어가거나, 현재 map의 값보다 작으면 continue
 *	3-2. 다음 이동할 곳의 DP값이 1보다 크면 이미 탐색을 한것이기 때문에 임시변수 max에 최대값을 저장한다.
 *	3-3. DP값이 1이면 DFS를 돌아 DP값을 바꾸고 max에 최대값을 저장한다.
 *	3-4. 4방 탐색이 끝나면 현재 위치의 DP값은 max + 1이 된다.
 *	3-5. 현재 DP값을 return한다.
 * 4. for문을 돌면서 DP값 중 가장 큰 값을 출력한다.
 * 
 */

public class BOJ_G3_1937_욕심쟁이판다 {

	static int n;
	static int[][] map;
	static int[][] DP;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		
		DP = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				DP[i][j] = 1;
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(DP[i][j] == 1) {
					DFS(i, j);
				}
			}
		}
		
		int ans = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				ans = Math.max(ans, DP[i][j]);
			}
		}
		
		System.out.println(ans);
	
	}
	
	public static int DFS(int r, int c) {
		
		int cur = map[r][c];
		
		int max = 0;
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 범위 체크 + 다음이 지금보다 작을경우 못감
			if(nr < 0 || nr >= n || nc < 0 || nc >= n || cur >= map[nr][nc]) continue;
			
			// DP에 저장된 값이 이미 있으면
			if(DP[nr][nc] > 1) {
				max = Math.max(max, DP[nr][nc]);
			}
			else {
				int temp = DFS(nr, nc);
				max = Math.max(max, temp);
			}
		}
		
		DP[r][c] = max + 1;
		
		return DP[r][c];
	}

}

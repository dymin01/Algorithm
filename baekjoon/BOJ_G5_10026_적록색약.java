package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <문제 요약>
 * 구해야 하는 것: 일반사람이 보는 구역의 수와 적록색약이 보는 구역의 수를 구하여라
 * 제약 사항: N은 1이상 100이하
 * 문제 유형: DFS, BFS, 브루트포스
 * 요구 개념: DFS, BFS
 * 
 * <풀이법 요약>
 * 1. 일반과 적록색맹을 구별하면 방문배열을 만든다.
 * 2. 방문하지 않았을 경우 구역을 체크한다.
 * 3. 구역의 갯수를 출력한다.
 * 
 */

public class BOJ_G5_10026_적록색약 {
	
	static int N;
	static int max, maxRG;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		// 일반구역의 개수
		max = 0;
		// 적록색약의 개수
		maxRG = 0;
		
		// 일반 RGB 구역 방문 배열
		boolean[][] v = new boolean[N][N];
		// 적록색맹의 RGB 구역의 방문 배열
		boolean[][] vRG = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!v[i][j]) {
					max++;
					BFS(i, j, map, v);
				}
				if(!vRG[i][j]) {
					maxRG++;
					BFSRG(i, j, map, vRG);
				}
			}
		}
		
		System.out.println(max + " " + maxRG);

	}

	// 일반 구역
	private static void BFS(int i, int j, char[][] map, boolean[][] v) {
		Queue<int[]> queue = new LinkedList<int[]>();
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
				
				if(map[nr][nc] == map[r][c]) {
					v[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
				
			}
		}
	}
	// 적록생맹일 경우
	private static void BFSRG(int i, int j, char[][] map, boolean[][] v) {
		Queue<int[]> queue = new LinkedList<int[]>();
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
				// 색이 같을경우
				if(map[nr][nc] == map[r][c]) {
					v[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
				// 다른데 R과 G일 경우
				else if((map[nr][nc] == 'R' && map[r][c] == 'G') || map[nr][nc] == 'G' && map[r][c] == 'R') {
					v[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}

}

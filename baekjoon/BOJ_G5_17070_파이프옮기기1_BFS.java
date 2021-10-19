package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS로 풀면 시간초과 난다. DFS또는 DP로 풀어야 한다.
 */

public class BOJ_G5_17070_파이프옮기기1_BFS {

//	static int N;
//	static int[][] map;
	
	// 0 = 오른쪽
	// 1 = 오른쪽 아래
	// 2 = 아래
//	static int[] dr = {0, 1, 1};
//	static int[] dc = {1, 1, 0};
//	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(BFS(0, 1, map, N));
	}


	private static int BFS(int i, int j, int[][] map, int N) {
		
		int[] dr = {0, 1, 1};
		int[] dc = {1, 1, 0};
		
		int answer = 0;
		
		Queue<int[]> queue = new LinkedList<>();
		
		// r, c, 파이프 모양(0 : 가로, 1 : 대각, 2 : 세로)
		queue.add(new int[] {i, j, 0});
		
		while(!queue.isEmpty()) {
			
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int pose = cur[2];
			
			for(int d = 0; d < 3; d++) {
				
				// 가로모양이고 세로방향은 넘어감
				if(pose == 0 && d == 2) continue;
				// 세로모양이고 가로방향은 넘어감
				if(pose == 2 && d == 0) continue;
				
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위 확인
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				// 파이프를 놓을 수 있는지 확인
				if(isOk(nr, nc, d, map)) {
					if(nr == N-1 && nc == N-1) {
						answer++;
						continue;
					}
					queue.add(new int[] {nr, nc, d});
				}
				
			}
			
		}
		
		return answer;
		
	}


	private static boolean isOk(int nr, int nc, int d, int[][] map) {
		
		if(d == 1){
			if(map[nr][nc] == 0 && map[nr-1][nc] == 0 && map[nr][nc-1] == 0) return true;
		}else {
			if(map[nr][nc] == 0) return true;
		}
		
		return false;
	}

}

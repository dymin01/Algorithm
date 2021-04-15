package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 토마토가 모두 익는데 걸리는 시간
 * 제약 사항: 토마토가 들어있지 않는 칸이 있다.
 * 문제 유형: 그래프 탐색, BFS
 * 요구 개념: BFS
 * 
 * <풀이법 요약>
 * 1. 토마토가 들어있는 박스를 입력받을때 익은 토마토를 Queue에 저장한다.
 *	  - 익어야 하는 토마토의 개수를 구해놓는다.
 * 2. BFS를 통해서 익은 토마토의 인접 토마토를 익힌다.
 *	  - 인접 토마토를 익힐때 개수를 구한다.
 * 3. 종료가 되었을때 익혀야 하는  토마토의 개수와, 시간이 지남에 따라 익은 토마토의 개수를 비교하여
 * 	    같으면 지난 시간을, 다르면 -1을 출력한다.
 */

public class BOJ_S1_7576_토마토 {

	static int N, M;
	static int[][] box;
	static boolean[][] v;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 가로
		int M = Integer.parseInt(st.nextToken());
		// 세로
		int N = Integer.parseInt(st.nextToken());
		// 상자
		box = new int[N][M];
		v = new boolean[N][M];
		
		Queue<int[]> queue = new LinkedList<int[]>();
		
		int toma = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 1) {
					queue.add(new int[] {i, j});
					v[i][j] = true;
				}
				if(box[i][j] == 0) {
					toma++;
				}
			}
		}
		
		int count = 0;
		int day = 0;
		while(!queue.isEmpty()) {
			int qsize = queue.size();
			while(qsize-- > 0) {
				int[] cur = queue.poll();
				int r = cur[0];
				int c = cur[1];
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if(v[nr][nc] || box[nr][nc] == -1) continue;
					v[nr][nc] = true;
					box[nr][nc] = 1;
					count++;
					queue.add(new int[] {nr, nc});
				}
			}
			day++;
		}
		
		if(count == toma) {
			System.out.println(day-1);
		}else {
			System.out.println(-1);
		}

	}


}

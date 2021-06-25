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
 * 문제 정의 : (1, 1)에사 (N, M)에 도착하기 위해 미로를 지나야 하는 칸의 최소 개수
 * 문제 유형 : BFS/DFS
 * 제약 사항 : 2 <= N, M <= 100
 * <풀이 요약>
 * 1. 그래프 입력받기
 * 2. 시작점부터 DFS나 BFS를 이용하여 도착지점에 도착하는 가장 작은 경우를 구한다.
 * 
 */


public class BOJ_S1_2178_미로탐색 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 미로를 저장할 배열
		int[][] map = new int[N][M];
		// 이동한 칸수를 저장할 배열
		int[][] ans = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		Queue<int[]> queue = new LinkedList<>();
		
		queue.add(new int[] {0, 0});
		ans[0][0] = 1;
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		// BFS를 이용하여 구한다.
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위를 넘어가거나 미로에서 갈 수 없는 길인경우
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 0) continue;
				// 이미 지나온 길인경우 가지 않는다.
				if(ans[nr][nc] != 0) continue;
				
				queue.add(new int[] {nr, nc});
				ans[nr][nc] = ans[r][c]+1;
				
			}
		}
		
		System.out.println(ans[N-1][M-1]);
		
	}

}

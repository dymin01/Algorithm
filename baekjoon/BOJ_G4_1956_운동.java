package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * 도로의 길이의 합이 가장 작은 사이클을 찾아라
 * 
 * 문제 유형
 * - 플로이드와샬?
 * 
 * 제약 사항
 * 2 ≤ V ≤ 400
 * 0 ≤ E ≤ V(V-1)
 * 거리는 10,000 이하의 자연수
 * 
 * <풀이 요약>
 * 오타 실수가 너무 많았다....반성
 * 
 * 1. map 행렬을 큰값으로 초기화 한다.
 * 2. 인접행렬로 입력받는다.
 * 3. 플로이드 와샬을 사용하여 최소거리를 구한다.
 * 4. 다른 마을을 방문하고 돌아오는 거리의 최소값을 구한다.
 * 
 */

public class BOJ_G4_1956_운동 {

	static final int INF = 4000001;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[V+1][V+1];
		
		// 여기서 오타...
		for(int i = 0; i <= V; i++) {
			Arrays.fill(map[i], INF);
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[a][b] = c;
		}
		
		for(int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for(int j = 1; j <= V; j++) {
					if(map[i][j] > map[i][k] + map[k][j]) {
						// 여기서 오타....
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		int answer = INF;
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				answer = Math.min(answer, map[i][j] + map[j][i]);
			}
		}
		
		if(answer == INF)
			System.out.println(-1);
		else
			System.out.println(answer);

	}

}

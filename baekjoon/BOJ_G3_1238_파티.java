package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * <문제 요약>
 * 문제 정의
 * - 학생들이 오고 가는데 가장 많은 시간을 소비한 학생을 구하라
 * 
 * 문제 유형
 * - 다익스트라?
 * - 플로이드와샬?
 * 
 * 제약 사항
 * 1 <= N <= 1,000
 * 1 <= M <= 10,000
 * 
 *
 * <풀이 요약>
 * 모든 학생의 이동시간을 구해야 하기 때문에 플로이드와샬을 사용함
 * 
 * 1. 인접행렬을 만들고 자기자신 = 0, 다른값은 매우 큰 값으로 저장한다.
 * 2. 인접행렬로 입력을 받는다.
 * 3. 플로이드 와샬 알고리즘을 사용한다.
 * 3-1. i -> j로 가는 경로중에 K를 경유하여 가는 길 중 가장 작은 값으로 값을 바꿔준다.
 * 
 * 4. 모든 학생들 중 X를 거쳤다가 돌아오는 값이 가장 큰 학생의 시간을 구한다.
 * 
 */

public class BOJ_G3_1238_파티 {

	static int N, M, X;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][N+1];
		
		for(int i = 0; i <= N; i++) {
			Arrays.fill(map[i], 10000001);
			map[i][i] = 0;
		}
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			map[start][end] = dist;
			
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		int max = 0;
		for(int i = 1; i <= N; i++) {
			if(map[i][X] + map[X][i] >= 10000001) continue;
			else if(map[i][X] + map[X][i] > max) {
				max = map[i][X] + map[X][i];
			}
		}
		
		System.out.println(max);

	}

}

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * 준규가 (N, M)으로 이동할때 가져올 수 있는 사탕의 최대 개수를 구해라
 * 
 * 문제 유형
 * DP
 * 
 * 제약 사항
 * 인접한 방으로만 이동가능
 * 1 <= N, M <= 1,000
 * 
 *  
 * <풀이 요약>
 * 자신이 위치한 방에 올 수 있는 경우는 왼쪽, 위, 왼쪽위(대각선위) 에서 오는 방법이다.
 * 자신이 위치한 방에까지 가져올 수 있는 사탕의 수는 전 방에서 사탕을 들고 자신의 방의 사탕과 합치는 방법이다.
 * 왼쪽, 위, 왼쪽위(대각선) 중 가장 사탕이 많은 사탕을 선택하여 DP에 저장한다.
 * 
 */

public class BOJ_S1_11048_이동하기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N+1][M+1];
		int[][] DP = new int[N+1][M+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				// 방으로 올 수 있는 방 중 큰 값을 가져온다.
				DP[i][j] = Math.max(Math.max(DP[i-1][j], DP[i][j-1]), DP[i-1][j-1]) + map[i][j];
			}
		}
		
		System.out.println(DP[N][M]);
		
	}

}

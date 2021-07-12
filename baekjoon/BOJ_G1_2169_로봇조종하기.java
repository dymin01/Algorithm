package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 조건을 만족하면서 탐사한 지역들의 가티의 최대 합을 구하라
 * 
 * 문제 유형
 * - DP
 * 
 * 제약 사항
 * - 1 <= N, M <= 1,000
 * 100을 넘지 않는 정수 
 * 
 * <풀이 요약>
 * 왼쪽과 오른쪽을 따로 계산해야 한다...
 * 
 * 
 * 
 * 
 */

public class BOJ_G1_2169_로봇조종하기 {

	// 아래는 안본다.
	static int[] dr = {-1, 0, 0};
	static int[] dc = {0, 1, -1};
	
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
		DP[1][1] = map[1][1];
		for(int i = 2; i <= M; i++) {
			DP[1][i] = DP[1][i-1] + map[1][i];
		}
		
		for(int i = 2; i <= N; i++) {
			// 왼쪽과 오른쪽 값들을 저장할 임시 배열
			// 0은 왼쪽 1은 오른쪽
			int[][] temp = new int[2][M+1];
			
			// 왼쪽에서 오른쪽으로 가는경우
			// 가장 왼쪽은 위에서 내려온 경우밖에 없다.
			temp[0][1] = map[i][1] + DP[i-1][1];
			for(int j = 2; j <= M; j++) {
				// 위에서 온것과 왼쪽에서 온것 중 큰 값을 선택
				temp[0][j] = Math.max(temp[0][j-1], DP[i-1][j]) + map[i][j];
			}
			
			// 오른쪽에서 왼쪽으로 가는경우
			// 가장 오른쪽은 위에서 내려오는 경우밖에 없다.
			temp[1][M] = map[i][M] + DP[i-1][M];
			for(int j = M-1; j > 0 ; j--) {
				temp[1][j] = Math.max(temp[1][j+1], DP[i-1][j]) + map[i][j];
			}
			// 왼쪽에서 오른쪽, 오른쪽에서 왼쪽의 두 경우 중 큰 값으로 DP를 갱신한다.
			for(int j = 1; j <= M; j++) {
				DP[i][j] = Math.max(temp[0][j], temp[1][j]);
			}
			
		}
		
		System.out.println(DP[N][M]);
	}

}

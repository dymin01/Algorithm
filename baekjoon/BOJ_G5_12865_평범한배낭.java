package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 구하라
 * 
 * 문제 유형
 * DP
 * 
 * 제약 사항
 * 1 <= N <= 100
 * 1 <= K <= 100,000
 * 1 <= W <= 100,000
 * 1 <= V <= 1,000
 * 
 *  
 * <풀이 요약>
 * DP 수업에서 배운 내용이다.
 * 물건을 고려했을때
 * - 담지 않는경우
 * - 담을경우
 * 두 가지를 봐야 한다.
 * 
 * 물건을 1번부터 N번째까지 물건을 늘려 가며 값을 찾는다.
 * 배낭의 무게도 1~K까지 무게일때 값이 모두 필요하기 때문에 완탐을 해야 한다.
 * 
 * 1. 1번 물건부더 1의 무게부터 K의 무게까지 넣을 수 있어 넣거나 넣지 않을경우 가치를 구한다.
 * 2. 물건을 넣을 수 있는경우(배낭의 무게가 물건의 무게보다 클 경우) 물건을 넣지 않았을때의 가치과, 물건을 넣었을떄의 가치를 비교
 * 3. 물건을 넣을 수 없는경우 바로 전 물건의 최대 가치를 그대로 사용한다.
 * 4. 마지막 DP의 값을 출력한다.
 * 
 * 
 */

public class BOJ_G5_12865_평범한배낭 {

		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] Weights = new int[N+1];
		int[] profits = new int[N+1];
		
		int[][] DP = new int[N+1][K+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			Weights[i] = Integer.parseInt(st.nextToken());
			profits[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= K; j++) {
				// 현재 물건의 무게가 배낭의 무게보다 클 경우 넣지 못함.
				if(Weights[i] > j) {
					DP[i][j] = DP[i-1][j];
				}
				// 물건을 담을 수 있을때
				// 물건을 담았을경우나 담지 않을경우 중 큰 가치를 선택한다.
				else {
					DP[i][j] = Math.max(DP[i-1][j-Weights[i]] + profits[i], DP[i-1][j]);
				}
			}
		}
		
		System.out.println(DP[N][K]);
			
		

	}

}

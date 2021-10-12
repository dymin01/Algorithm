package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 수익을 구하라
 * 
 * 문제 유형
 * - DP
 * 
 * 제약 사항
 * 1 <= N <= 15
 * 1 ≤ Ti ≤ 5, 1 ≤ Pi ≤ 1,000
 * 
 * <풀이 요약>
 * 뒤에서 부터 생각하는게 더 쉬운듯 하다... 앞에서 가는거는...잘 이해가 안된다... 나중에 다시 봐야겠다.
 * 
 * 
 */


public class BOJ_S3_14501_퇴사 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] T = new int[N+1];
		int[] P = new int[N+1];
		int[] dp = new int[N+1];
		
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i = N; i > 0; i--) {
			
			// 일하는 날짜가 넘어간다면
			if(i + T[i] -1 > N) {
				dp[i] = dp[i+1];
			}
			// 일할 수 있으면
			else {
				//		    일을 하지 않는경우       일을 하는 경우
				dp[i] = Math.max(dp[i+1], P[i] + dp[i + T[i]]);
			}
			
		}
		
		System.out.println(dp[1]);

	}

}

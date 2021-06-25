package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의 : 동전을 적당히 사용해서 그 가치의 합이 K가 되도록 만드는 경우의 수를 구하라
 * 문제 유형 : DP
 * 제약 사항 : (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000)
 * <풀이 요약>
 * 0. 동전의 값인 DP에 1을 넣어준다. DP는 최소 동전의 갯수를 저장한다.
 * 1. 가장 작은 동전의 값에서 시작한다. 그보다 작은 동전은 만들 수 없기 떄문이다.
 * 2. 값에 동전들은 더해 만들 수 있는 숫자가 있으면 DP[값 + 동전값]에 +1 한다.
 * 3. 기존값과 비교해 작은값으로 넣는다.
 * 4. 만약 DP값이 0이면 이미 동전을 만들 수 없기 때문에 넘어간다.
 * 5. DP[k]값을 출력한다.
 */


public class BOJ_S1_2294_동전2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] coins = new int[N];
		int[] dp = new int[120000];
		
		for(int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
			dp[coins[i]] = 1;
		}
		
		Arrays.sort(coins);
		
		int start = coins[0];
		
		for(int i = start; i < K; i++) {
			if(dp[i] == 0) continue;
			for(int j = 0; j < N; j++) {
				if(dp[i+coins[j]] == 0) dp[i+coins[j]] = dp[i]+1;
				else dp[i+coins[j]] = Math.min(dp[i]+1, dp[i+coins[j]]);
			}
		}
		if(dp[K] == 0) System.out.println(-1);
		else System.out.println(dp[K]);
		
	}

}

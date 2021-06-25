package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의 : 동전을 적당히 사용해서 그 가치의 합이 K가 되도록 만드는 경우의 수를 구하라
 * 문제 유형 : DP
 * 제약 사항 : (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000)
 * <풀이 요약>
 * 1. 첫번째 동전으로만 K까지 만들 수 있는 경우의 수를 구한다.
 * 2. 두번째 동전을 추가해서 사용할때 K까지 만들 수 있는 경우의 수를 구한다.
 * 3. 그 뒤로도 동전 하나를 추가해서 만들 수 있는 경우의 수를 만든다. 이는 DP[K] = DP[K] + DP[K - i번째 동전값];
 * 		ex) 1 2 5월을 이용할때
 *          
 *        	  1 2 3 4 5 6 7 8 9 10
 * 		1원만     1 1 1 1 1 1 1 1 1 1
 *  1원만 사용해서 만들 수 있는 경우의 수는 1가지씩 밖에 없다.
 * 
 * 
 *        	  1 2 3 4 5 6 7 8 9 10
 * 		1원만     1 1 1 1 1 1 1 1 1 1
 * 		2원도     1 2 2 3 3 4 4 5 5 6
 * 
 * 		예로 3원을 만드는 경우의 수는 (1원으로만 만들 수 있는 경우의수) + (2원을 사용해서 만들 수 있는 경우의 수)
 * 					이는 DP로 나타네면 DP[3] = DP[3] + DP[3 - 2] 로 나타낼수있다.
 * 										(1로만 3만드는 경우) + (1로만 1 만드는 경우에 2원동전 하나 추가 == 1로만 1만드는 경우의 수)
 * 
 * 
 * DP는 역시 어렵다...
 * 
 */


public class BOJ_S1_2293_동전1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[15000];
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 1;
		for(int i = 0; i < N; i++) {
			for(int j = arr[i]; j <= K; j++) {
				dp[j] = dp[j] + dp[j - arr[i]]; 
			}
		}
		
		System.out.println(dp[K]);
		
		
	}

}



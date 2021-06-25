package baekjoon;

import java.util.Scanner;

/***
 * 
 * <문제 요약>
 * 문제 정의 : 길이가 N인 계단 수가 몇개 있는지 구하라
 * 문제 유형 : DP
 * 제약 사항 : 1 <= N <= 100
 * <풀이 요약>
 * 1. 1개의 숫자로 만들 수 있는 숫자들을 DP에 넣는다.
 * 2. 2개의 숫자로 만들 수 있는 숫자들은 10의 자리의 수가 1자리 숫자였을때 갯수이다.
 * 3. N자리 수까지 반복한다.
 * 
 * long 형으로 만들었어야 했고, 미리 1000000000으로 나눠야 안넘어 가더라...
 * 
 */

public class BOJ_S1_10844_쉬운계단수 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		long[][] dp = new long[N+1][11];
		for(int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		
		for(int i = 2; i <= N; i++) {
			dp[i][0] = dp[i-1][1];
			
			for(int j = 1; j <= 9; j++) {
				// 미리 나누기....
				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
			}
		}
		long ans = 0;
		for(int i = 0; i <= 9; i++) {
			ans += (dp[N][i] % 1000000000);
		}
		
		System.out.println(ans % 1000000000);
	}
}

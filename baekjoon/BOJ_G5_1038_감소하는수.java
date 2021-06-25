package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/***
 * 
 * <문제 요약>
 * 문제 정의 : N번째 감소하는 수를 구하라
 * 문제 유형 : 백트레킹
 * 제약 사항 : N은 1,000,000 (백만)보다 작거나 같은 자연수 또는 0
 * <풀이 요약>
 * 0. N이 1022번째 숫자가 9876543210이다. 이보다 큰 감소하는 숫자는 없다. 그러므로 N이 1022보다 크면 -1을 출력한다.
 * 1. 0~9는 배열에 그대로 넣는다.
 * 2. 10번째에는 가장 작은 1뒤에 1보다 작은 숫자를 붙여 10을 만들어 배열에 넣는다.
 * 3. 11번째는 2 뒤에 0을 붙이고 12번째는 2 뒤에 1을 붙여 감소하는 숫자를 만든다.
 * 4. 위와 같은 방식으로 숫자를 만들어 N번째 숫자를 찾는다.
 * 
 */

public class BOJ_G5_1038_감소하는수 {

	static long[] dp;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		if(N > 1022) {
			System.out.println(-1);
			return;
		}
		
		dp = new long[1000001];
		
		for(int i = 0; i <= 9; i++) {
			dp[i] = i;
		}
		int idx = 10;
		
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < (dp[i]%10); j++) {
				dp[idx++] = dp[i] * 10 + j;
			}
		}
		
		System.out.println(dp[N]);
	}

}

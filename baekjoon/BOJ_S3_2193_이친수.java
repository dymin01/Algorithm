package baekjoon;

import java.util.Scanner;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - N자리 이친수의 개수를 구하라
 * 
 * 문제 유형
 * - DP
 * 
 * 제약 사항
 * 1 <= N <= 90
 * 이친수는 0으로 시작하지 않는다.
 * 1이 두 번 연속으로 나타나지 않는다.
 *  
 * <풀이 요약>
 * DP...DP라.....
 * 각 자리수의 이친수의 개수는 마지막이 0인것과 1인것의 합이다.
 * 다음 자리수의 마지막이 0의 개수는 전 자리수의 0과 1의 개수의 합이다.
 * 다음 자리수의 마지막이 1의 개수는 전 자리수의 0의 개수이다.
 * 결국 피보나치 수열이다.
 * 
 * 자료형을 long으로 해야 한다...
 * 
 * 
 */

public class BOJ_S3_2193_이친수 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long[] DP = new long[N+10];
		
		DP[1] = 1;
		DP[2] = 1;
		
		for(int i = 3; i <= N; i++) {
			DP[i] = DP[i-1] + DP[i-2];
		}
		
		/*
		DP[1][0] = 0;
		DP[1][1] = 1;
		DP[2][0] = 1;
		DP[2][1] = 0;
		
		for(int i = 3; i <= N; i++) {
			DP[i][0] = DP[i-1][0] + DP[i-1][1];
			DP[i][1] = DP[i-1][0];
		}
		 */
		
		System.out.println(DP[N]);
		
		
		
	}

}

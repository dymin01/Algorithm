package baekjoon;

import java.util.Scanner;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * 수의 길이가 N이 주어질때 오르막 수의 개수를 구하라.
 * 
 * 문제 유형
 * DP
 * 
 * 제약 사항
 * 인접한 수가 같아도 오름차순이다.
 *  
 * <풀이 요약>
 * 1. 1자리수는 만들 수 있는 수는 1이다.
 * 2. 2의 자리수는 한자리 수에서 마지막 수가 자신보다 작은 것들의 합이다.
 * 예) 2의 자리수의 마지막이 3이면 1자리수 0~3 까지 더한 값이다.
 * 
 */

public class BOJ_S1_11057_오르막수 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[][] DP = new int[N+1][10];
		
		for(int i = 0; i < 10; i++) {
			DP[1][i] = 1;
		}
		
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j < 10; j++) {
				for(int k = 0; k <= j; k++) {
					DP[i][j] = (DP[i][j] + DP[i-1][k]) % 10007;
				}
			}
		}
		
		int ans = 0;
		
		for(int i = 0; i < 10; i++) {
			ans = (ans + DP[N][i]) % 10007;
		}
		
		System.out.println(ans);
		
	}

}

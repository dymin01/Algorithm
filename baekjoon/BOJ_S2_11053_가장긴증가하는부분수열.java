package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 가장 긴 증가하는 부분 수열의 길이를 구하라
 * 
 * 문제 유형
 * - DP
 * 
 * 제약 사항
 * - 1 <= N <= 1,000
 * 1 <= Ai <= 1,000
 * 
 * <풀이 요약>
 * 1. 증가하는 부분수열의 길이를 저장할 DP를 정의한다. DP의 초기값은 1로 초기화 한다.
 * 2. 첫번째 숫자부터 자신보다 왼쪽에 있는 수들 중에 자신보다 작은것들의 DP값 중 가장 긴 수열을 구한다.
 * 3. 가장 긴 수열의 길이 + 1이 자신의 수열길이
 * 
 */

public class BOJ_S2_11053_가장긴증가하는부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		// 기존수열
		int[] A = new int[N];
		//DP
		int[] DP = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			// DP는 1로 초기화 가장 짧은 길이가 1이기 때문에
			DP[i] = 1;
		}
		
		int ans = 1;
		// 0부터 자신보다 작은 것들 중 가장 긴 수열을 구한다.
		for(int i = 0; i < N; i++) {
			int max = 0;
			// i 까지 i보다 작고은 것들 중 DP(수열의 길이가 가장 긴것)을 구한다.
			for(int j = 0; j < i; j++) {
				if(A[i] > A[j]) {
					max = Math.max(max, DP[j]);
				}
			}
			// i의 DP(수열의 길이)는 i보다 작은 것의 수열 + 1이다.
			DP[i] = max + 1;
			// 가장 긴 수열의 길이를 구한다.
			ans = Math.max(DP[i], ans);
		}
		
		System.out.println(ans);
		
		

	}

}

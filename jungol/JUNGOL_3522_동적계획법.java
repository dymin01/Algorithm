package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 
 * <문제 요약>
 * 문제 정의 : 피보나치 N번째 수를 구한 후 10억 7로 나눈 나머지를 구하시오
 * 문제 유형 : 디피
 * 제약 사항 : 10 <= N <= 100,000
 * <풀이 요약>
 *
 * DP를 이용한다.
 *
 *
 */


public class JUNGOL_3522_동적계획법 {

	static int[] DP;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		DP = new int[100001];
		DP[1] = DP[2] = 1;
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= N; i++) {
			DP[i] = fibo(i);
		}
		
		int ans = DP[N];
		
		System.out.println(ans);

	}

	private static int fibo(int N) {
		
		if(N == 1 || N == 2) {
			return 1;
		}
		
		if(DP[N] != 0) {
			return DP[N];
		}
		else {
			DP[N] = (fibo(N-1) + fibo(N-2)) % 1000000007;
			return DP[N];
		}
	}

}

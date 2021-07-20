package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * <문제 요약>
 * 문제 정의
 * - S 문자열에 P문자열이 부분문자열인지 알아보자.
 * 
 * 문제 유형
 * - 문자열
 * - KMP
 * 
 * 제약 사항
 * 빈 문자열이 아니다.
 * 문자열의 길이는 100만을 넘지않는다.
 * 
 * <풀이 요약>
 * KMP 알고리즘 정리 하고 문제 다시 풀기
 * 
 * 
 */

public class BOJ_G4_16916_부분문자열 {
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		String P = br.readLine();
		
		int answer = KMP(S, P);
		
		System.out.println(answer);

	}

	private static int KMP(String S, String P) {
		int ans = 0;
		// pi 배열을 만든다.
		int[] pi = getPi(P);
		
		int j = 0;
		for(int i = 0; i < S.length(); i++) {
			while(j > 0 && S.charAt(i) != P.charAt(j)) {
				j = pi[j-1];
			}
			if(S.charAt(i) == P.charAt(j)) {
				if(j == P.length()-1) {
					ans=1;
					break;
				}else {
					j++;
				}
			}
		}
		
		
		return ans;
	}

	private static int[] getPi(String P) {
		// pi배열의 길이는 패턴의 길이
		int[] pi = new int[P.length()];
		// j는 접두
		int j = 0;
		// i는 접미 1부터 시작한다. 0은 한개여서 계산할 필요가 없다.
		// i+1 자리수
		for(int i = 1; i < P.length(); i++) {
			// j가 0보다 큰것은 앞에 같은 문자열이 있었다. 
			// 
			while(j > 0 && P.charAt(i) != P.charAt(j)) {
				j = pi[j-1];
			}
			if(P.charAt(i) == P.charAt(j)) {
				pi[i] = ++j;
			}
		}
		
		return pi;
	}

}

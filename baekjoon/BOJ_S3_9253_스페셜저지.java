package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * <문제 요약>
 * 문제 정의
 * - 부분문자열이 맞으면 YES 틀리면 NO 출력
 * 
 * 문제 유형
 * - KMP
 * - 문자열 문제
 * 
 * 제약 사항
 * 
 * <풀이 요약>
 * KMP 알고리즘 연습
 * 
 * B와 사용자의 문자열이 모두 포함되어 있으면 YES를 출력
 * 
 * 다른코드 안보고 짜라고 하면 짤 수 있기는 한데... 중간에 좀 이해가 안되네....
 * 
 * 
 */

public class BOJ_S3_9253_스페셜저지 {
	
	static String A;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String A = br.readLine();
		String B = br.readLine();
		String user = br.readLine();
		
		int[] pi = getPI(user);
		
		if(KMP(A, user, pi) && KMP(B, user, pi)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		
	}

	private static boolean KMP(String S, String P, int[] pi) {
		
		int j = 0;
		
		for(int i = 0; i < S.length(); i++) {
			
			while(j > 0 && S.charAt(i) != P.charAt(j)) {
				j = pi[j-1];
			}
			if(S.charAt(i) == P.charAt(j)) {
				if(j == P.length()-1) {
					return true;
				}else {
					j++;
				}
			}
		}
		
		return false;
	}

	private static int[] getPI(String P) {
		int[] pi = new int[P.length()];
		
		int j = 0;
		
		for(int i = 1; i < P.length(); i++) {
			while(j > 0 && P.charAt(i) != P.charAt(j)) {
				// 이부분이 왜 하는지는 알것같은데..이해가...왜 잘 안될까...
				// 다음 중복부분까지 뛰어넘으려고 하는거긴한데...잘 이해가 안되네....
				j = pi[j-1];
			}
			if(P.charAt(i) == P.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}

}

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * <문제 요약>
 * 문제 정의
 * - P문자열이 T문자열에 몇번 등장하고, 등장할때 I번째 인덱스들을 구하라
 * 
 * 문제 유형
 * - KMP
 * 
 * 제약 사항
 * 1 <= n, m <= 1,000,000
 *
 * <풀이 요약>
 * 
 * KMP 알고리즘을 쓰라고 하면 쓰는데...아직도 내꺼가 된것같진 않음... 이해가 더 필요!
 * 
 * 1. 전형적인 KMP 알고리즘으로 문제를 구한다.
 * 
 * 
 */

public class BOJ_G1_1786_찾기 {
	
	static int count;
//	static StringBuilder answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] T = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();
		
		count = 0;
		
		String ans = KMP(T, P);
			
		System.out.println(count);
		System.out.println(ans);
		
		

	}

	private static String KMP(char[] T, char[] P) {
		StringBuilder res = new StringBuilder();
		int[] fail = getFail(P);
		
		int j = 0;
		
		for(int i = 0; i < T.length; i++) {
			
			while(j > 0 && T[i] != P[j]) {
				j = fail[j - 1];
			}
			
			if(T[i] == P[j]) {
				if(j == P.length-1) {
					count++;
					res.append((i - P.length + 2) + " ");
					j = fail[j];
				} else {
					j++;
				}
			}
			
		}
		
		return res.toString();
		
	}

	private static int[] getFail(char[] P) {
		
		int[] fail = new int[P.length];
		
		int j = 0;
		
		for(int i = 1; i < P.length; i++) {
			
			while(j > 0 && P[i] != P[j]) {
				j = fail[j - 1];
			}
			
			if(P[i] == P[j]) {
				fail[i] = ++j;
			}
			
		}
		
		return fail;
	}

}

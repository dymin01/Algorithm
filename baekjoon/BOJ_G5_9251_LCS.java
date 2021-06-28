package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - LCS(최장 공통 부분 수열)의 가장 긴 길이를 구해라
 * 
 * 문제 유형
 * -DP
 * 
 * 제약 사항
 * - 대문자로만 이루어져 있고, 1000자 이하이다.
 * 
 * <풀이 요약>
 * 처음에 점화식을 찾기 어려워서 강의 찾아서 들었음...
 * 점화식을 찾는게 어려웠다.
 * 1번 스트링과 2번 스트링을 비교해 DP를 채워 나간다.
 * 1번 문자열과  2번 문자열의 문자를 하나씩 늘려가며 비교한다.
 * 1번 문자열의 i번째 문자와 2번 문자열의 j번째 문자와 같다면
 * 1번 문자열의 i-1번째 문자와 2번 문자열 j-1번째 문자까지 구한 공통수열의 길이보다 1 길어진다.
 * 
 * 말로 설명이 어렵다... 그림을 참고하는게 좋을것 같다.
 * 
 */

public class BOJ_G5_9251_LCS {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		
		int str1_len = str1.length;
		int str2_len = str2.length;
		
		int[][] DP = new int[str1_len +1][str2_len + 1];
		
		for(int i = 1; i <= str1_len; i++) {
			for(int j = 1; j <= str2_len; j++) {
				// 알파벳이 같으면
				if(str1[i-1] == str2[j-1]) {
					DP[i][j] = DP[i-1][j-1] + 1;
				}
				// 알파벳이 다르면
				else {
					DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1]);
				}
			}
		}
		
		System.out.println(DP[str1_len][str2_len]);
		
	}

}

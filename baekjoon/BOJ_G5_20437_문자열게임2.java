package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/***
 * <문제 요약>
 * 문제 정의
 * 1.알파벳 소문자로 이루어진 문자열 W가 주어진다.
 * 2.양의 정수 K가 주어진다.
 * 3.어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
 * 4.어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.
 * 
 * 를 만족하는 3번 4번 길이를 출력하라 없으면 -1을 출력하라
 * 
 * 문제 유형
 * - 투포인터?
 * 
 * 제약 사항
 * 1 <= T <= 100
 * 1 <= K <= |W| <= 10,000
 * 
 * <풀이 요약>
 * 거의 시뮬레이션 문제라고 생각함
 * 문자의 출현 개수를 미리 저장해 놓는게 포인트 인듯
 * 
 * 1. 문자열을 입력받는다.
 * 2. 입력받은 문자열에서 알파벳의 출현 빈도를 배열로 만들어 놓는다.
 * 3. 2개의 포인터로 i 와 j를 비교한다.
 * 4. 만약 문자 i의 개수가 K개 보다 작으면 답이 나올 수 없기 때문에 넘어간다.
 * 5. i 와 j가 같으면 출현 카운트를 증가한다.
 * 6. 출현 카운트가 K와 같으면 min, max값을 구한다.
 * 7. min, max를 출력한다.
 * 
 */

public class BOJ_G5_20437_문자열게임2 {

	
	static int max;
	static int min;
	
	static int[] alphabetCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();
		
		for(int t = 0; t < T; t++) {
			max = min = -1;
			char[] W = br.readLine().toCharArray();
			int K = Integer.parseInt(br.readLine());
			
			alphabetCnt = new int[26];
			
			for(int i = 0; i < W.length; i++) {
				alphabetCnt[W[i]-'a']++;
			}
			
			go(W, K);
			
			answer.append(min);
			if(min != -1) {
				answer.append(" ").append(max);
			}
			answer.append("\n");
		}
		
		System.out.println(answer.toString());

	}

	private static void go(char[] W, int K) {
		
		for(int i = 0; i < W.length; i++) {
			char left = W[i];
			int sum = 0;
			if(alphabetCnt[left-'a'] < K) continue;
			for(int j = i; j < W.length; j++) {
				char right = W[j];
				// 문자가 같으면
				if(left == right) {
					// 같은 문자의 갯수 증가
					sum++;
				}
				// 같은 문자의 갯수가 K개 이면
				if(sum == K) {
					max = Math.max(max, j - i + 1);
					if(min != -1) {
						min = Math.min(min, j - i + 1);
					}else {
						min = j - i + 1;
					}
					// 다음 문자로 이동
					break;
				}
			}
			
		}
		
	}

}

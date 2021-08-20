package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * K개의 알파벳을 가르칠 때 학생들이 읽을 수 있는 단어의 개수의 최댓값
 * 
 * 문제 유형
 * - 조합
 * - 문자열
 * 
 * 제약 사항
 * N은 50보다 작거나 같은 자연수
 * K는 26보다 작거나 같은 자연수 또는 0
 * 8 <= 단어의 크기 <= 15
 * 
 * <풀이 요약>
 * 비트마스트 쓰면 좀 더 빠를것 같지만....모르겠음...
 * 
 * 0. K가 5보다 작으면 단어를 못읽기 때문에 0을 출력하고, 26이면 모든 알파벳을 읽을 수 있기 때문에 N개를 출력한다.
 * 1. 조합을 이용해 a n t i c를 제외한 K-5개의 알파벳을 고른다.
 * 2. 주어진 단어들의 중간 단어들 중 읽을 수 있는 단어가 있으면 카운트 한다.
 * 3. 조합들 중 가장 많은 단어를 읽을 수 있는 값을 출력한다.
 * 
 * 
 */

public class BOJ_G4_1062_가르침 {

	static String[] subString;
	static boolean[] visited;
	
	static int N, K;
	
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		subString = new String[N];
		visited = new boolean[26];
		
		answer = 0;
		
		if(K < 5) {
			System.out.println(0);
		} else if(K == 26) {
			System.out.println(N);
		} else {
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				subString[i] = str.substring(4, str.length()-4);
			}
			// 필수로 배워야 하는 알파벳
			visited['a' - 'a'] = true;
			visited['n' - 'a'] = true;
			visited['t' - 'a'] = true;
			visited['i' - 'a'] = true;
			visited['c' - 'a'] = true;
			
			// 배워야 하는 알파벳을 조합으로 찾는다.
			nCr(0,0);
			
			System.out.println(answer);
		}

	}

	private static void nCr(int cnt, int start) {
		
		// 배울 수 있는 알파벳 조합을 만들었으면
		if(cnt == K-5) {
			int count = 0;
			
			// 단어 중간 단어들 중
			for(String str : subString) {
				boolean isOk = true;
				// 알파벳들을 모두 배웠는지 확인
				for(int i = 0; i < str.length(); i++) {
					// 배운 알파벳이라면
					if(visited[str.charAt(i)-'a']) continue;
					
					// 배우지 않은 알파벳이라면
					isOk = false;
					break;
				}
				// 알파벳들을 모두 배웠다면 읽을 수 있는 단어이다.
				if(isOk)
					count++;
			}
			
			answer = Math.max(answer, count);
			
		}
		
		for(int i = start; i < 26; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			nCr(cnt+1, i+1);
			visited[i] = false;
			
		}
		
		
	}

}

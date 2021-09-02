package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 팀 빌딩에서 나올 수 있는 팀 중 능력치의 최대값을 구하라
 * 
 * 문제 유형
 * 투포인터
 * 
 * 제약 사항
 * 2 <= N <= 100,000
 * 1 <= xi <= 10,000
 * 
 * <풀이 요약>
 * 머리가 안돈다.
 * 
 * 1. left와 right로 양쪽 끝 사람을 팀으로 생각하고 팀을 만들어 능력치를 구한다.
 * 2. answer과 비교해서 최대값을 저장한다.
 * 3. left와 right중 작은 숫자를 옮겨 더 큰 값이 나오는지 확인한다.
 * 4. 1 ~ 3 과정을 N-2번 반복한다.
 * 
 */

public class BOJ_G5_22945_팀빌딩 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] list = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		
		int left = 0;
		int right = N-1;
		
		for(int i = 0; i < N-2; i++) {
		
			int teamAbility = (right - left - 1) * Math.min(list[left], list[right]);
			
			answer = Math.max(answer, teamAbility);
			
			if(list[left] < list[right]) {
				left++;
			}
			else {
				right--;
			}
		}
		
		System.out.println(answer);
		
	}

}

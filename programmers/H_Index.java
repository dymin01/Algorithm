package programmers;

/***
 * <문제 요약>
 * 구해야 하는 것 : H-Index
 * 문제 유형 : 구현, 문제이해
 * 요구 개념 : sort
 * <풀이법 요약>
 * 0. 내림차순이나 오름차순으로 정렬
 * 1. 정렬 후 큰수 부터 조건 H <= answer 을 처음 만족하는 구간을 찾는다.
 */

import java.util.Arrays;

public class H_Index {

	public static void main(String[] args) {
		int[] citations = {3,0,6,1,5};
		System.out.println(solution(citations));
	}
	
	public static int solution(int[] citations) {
		int answer = 0;
		
		// 논문의 인용된 횟수 정렬
		// 내림차순으로 정렬해도 괜찮음
		Arrays.sort(citations);
		
		// 최댓값을 구하기 때문에 뒤에서부터 탐색
		for(int i = citations.length-1; i >= 0; i--) {
			// citations[i] 는 논문의 인용 횟수 H
			// answer 은 H번 이상 인용된 논문의 갯수
			// 논문의 인용횟수 H가 H번 이상 인용된 논문의 갯수보다 크면 조건에 맞지 않는다. 그럼 하나 밑의 논문을 탐색
			// 만약 H가 answer보다 작거나 같으면 H-Index 조건에 만족한다. 즉 최대값의 H-Index가 된다.
			if(citations[i] <= answer) break;
			answer++;
		}
		return answer;
		
	}
	

}

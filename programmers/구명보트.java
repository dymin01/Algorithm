package programmers;

/***
 * <문제 요약>
 * 구해야 하는 것 : N명의 사람들을 구하기 위한 최소한의 구명보트
 * 제약 사항 : 구명보트는 2명만 탈 수 있다.
 * 문제 유형 : 그리디, 구현
 * 요구 개념 : 그리디
 * <풀이법 요약>
 * 0. 사람들의 몸무게를 정렬한다.
 * 1. 큰사람과 작은사람이 같이 구명보트에 타도 괜찮으면 2명을 태운다.
 * 1-1 그렇지 않을경우 몸무게가 많이 나가는 사람 1명만 태운다. 
 * 2. 1 ~ 1-1 사항을 반복한다. 사람을 모두 구할때 까지.
 */

import java.util.Arrays;

public class 구명보트 {

	public static void main(String[] args) {
		int[] people = {70, 50, 40, 40};
		int limit = 100;
		
		Solution(people, limit);

	}
	
	public static int Solution(int[] people, int limit) {
        int answer = 0;
        
        //몸무게로 정렬한다.
        Arrays.sort(people);

        // B는 몸무게가 많이 나가는쪽, S는 적게 나가는쪽
        int b = people.length-1;
        int s = 0;
        
        // 다 탐색할때 까지 반복한다.
        while(b >= s) {
        	// 만약 몸무게가 많이 나가는사람과 적게 나가는 사람이 같이 탈 수 있는경우
        	if(people[b] + people[s] <= limit) {
        		s++;
        		b--;
        	}
        	// 몸무게가 많이 나가는 사람 1명만 구한다.
        	else {
        		b--;
        	}
        	answer++;
        }
        
        return answer;
    }
	
}

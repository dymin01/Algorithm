package programmers;


/***
 * <문제 요약>
 * 구해야 하는 것 : AAAAA로 되어있는 초기 이름에서 입력받은 이름으로 바꾸는 최소의 움직임
 * 제약 사항 : A는 바꾸지 않는다.
 * 문제 유형 : 그리디, 구현
 * 요구 개념 : 그리디
 * <풀이법 요약>
 * 0. 조이스틱의 위치의 글자가 N보다 작으면 위로, 크면 아래로 움직인다.
 * 1. 오른쪽에 A가 아닌 문자를 찾아 이동한 거리와 왼쪽에 A가 아닌 문자를 찾아 이동한 거리 중 작은 위치로 조이스틱 이동
 * 2. 0, 1 을 반복하다가 AAAAAA로 만들어지면 종료
 */

import java.util.Arrays;

public class 조이스틱_희원 {

	public static void main(String[] args) {
		조이스틱_희원 sol = new 조이스틱_희원();
		//String name = "JEROEN";
		String name = "JAAABAC";
		System.out.println(sol.solution(name));
	}
	
	public int solution(String name) {
	     int answer = 0;
	     //최대로 가질 수 있는 min값은 끝까지 가는것
			int len = name.length();
			int min = len-1;
			for (int i = 0; i < len; i++) {
				answer+=Math.min(name.charAt(i)-'A', 'Z'-name.charAt(i)+1);
	        	int next = i+1;
	          //내 다음이 A면 계속 진행(next++)
				while(next<len&&name.charAt(next)=='A') next++;
	      //현재까지 왔다가 다시 돌아가서 남은거까지 가는 이동 횟수
				min = Math.min(min, i+len-next+i);
			}
			answer += min;
			return answer;
	    }

}

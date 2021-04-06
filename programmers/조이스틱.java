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

public class 조이스틱 {

	public static void main(String[] args) {
		조이스틱 sol = new 조이스틱();
		String name = "JEROEN";
		System.out.println(sol.solution(name));
	}
	
	public int solution(String name) {
        int answer = 0;
        // 확인할 이름
        char[] checkName = name.toCharArray();
        // "AAAAAAA" 다 바꾸고 마지막에 비교할 문자열
        char[] basicName = name.toCharArray();
        Arrays.fill(basicName, 'A');
        
        int pos = 0;
        while(true) {
        	
        	// 1.
        	//pos, 조이스틱 위치의 글자가 N보다 작으면 위로 움직이고, 아니면 아래로 움직임.
        	if(checkName[pos] <= 'N') answer += checkName[pos] - 'A';
        	else answer += 'Z' - checkName[pos] + 1;
        	// 글자를 바꿨으므로 A로 변경.
        	checkName[pos] = 'A';
        	
        	// 확인할 이름 chackName이 "AAAAA"로 다 바뀌면 끝
        	if(String.valueOf(basicName).equals(String.valueOf(checkName))) break;
        	
        	// 2.
        	//오른쪽으로 이동
        	int rightPos = (pos + 1) % checkName.length;
        	int cntMoveRight = 1;        	
        	// A가 나오고 "AAAAA" 랑 같지 않을경우 오른쪽으로 얼마나 이동해야 하는지 구함.
        	while(checkName[rightPos] == 'A' && !(String.valueOf(basicName).equals(String.valueOf(checkName)))){
        		rightPos = (rightPos+1)%checkName.length;
        		cntMoveRight++;
        	}
        	//왼쪽으로 이동
        	//(pos + checkName.length -1)은 pos가 0이면 계산이 안되기 떄문에 ((pos + length) -1) % length로 구함.
        	int leftPos = (pos + checkName.length - 1) % checkName.length;
        	int cntMoveLeft = 1;
        	// A가 나오고 "AAAAA" 랑 같지 않을경우 왼쪽으로 얼마나 이동해야 하는지 구함.
        	while(checkName[leftPos] == 'A' && !(String.valueOf(basicName).equals(String.valueOf(checkName)))){
        		leftPos = (leftPos + checkName.length - 1)%checkName.length;
        		cntMoveLeft++;
        	}
        	
        	// 오른쪽으로 움직인 것이 더 적으면 다음 위치를 오른쪽, 움직인 만큼 answer에 +
        	if(cntMoveRight <= cntMoveLeft) {
        		pos = rightPos;
        		answer += cntMoveRight;
        	}else {
        		pos = leftPos;
        		answer += cntMoveLeft;
        	}
        }
        return answer;
    }

}

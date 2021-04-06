package programmers;

/***
 * <문제 요약>
 * 구해야 하는 것 : 체육복을 빌려 체육수업을 들을 수 있는 최대의 학생수
 * 제약 사항 : 체육복이 2개 있는 학생이 체육복을 도둑맞을 수 있다. 체육복은 앞 뒤로 1명 안에서만 빌려줄 수 있다.
 * 문제 유형 : 구현, 문제이해
 * 요구 개념 : 그리디
 * <풀이법 요약>
 * 0. 학생 배열을 초기화 한다. 이때 잃어버리거나 추가로 가지고 있을경우 무조건 0 이나 2가 아니다. 2에서 잃어버리기도 한다.
 * 1. 여분을 가지고 있는 학생의 왼쪽, 오른쪽을 확인하여 체육복을 나눠준다. 왼쪽부터 탐색하기 떄문에 왼쪽부터 확인한다.
 * 2. 체육복을 갖고 있는 학생의 수를 계산한다.
 */


import java.util.Arrays;

public class 체육복 {

	public static void main(String[] args) {
		
		System.out.println(solution(3, new int[] {3}, new int[] {1}));
	}
	
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        // 학생을 저장하는 배열
        int[] stds = new int[n+1];
        // 모든 학생이 1개의 체육복을 갖고있다.
        Arrays.fill(stds, 1);
        // 일어버린 학생의 체육복을 1개 뺴준다.
        for(int i = 0; i < lost.length; i++) {
        	stds[lost[i]]--;
        }
        // 추가로 여분을 가지고 있는 학생의 체육복을 1개 더해준다.
        for(int i = 0; i < reserve.length; i++) {
        	stds[reserve[i]]++;
        }
        
        // 여분을 가지고 있는 학생의 앞 뒤를 판단하여 빌려줄 수 있는지 확인한다.
        for(int i = 0; i < reserve.length ; i++) {
        	// 여분을 가지고 있던 학생이 잃어버린경우 패스
        	if(stds[reserve[i]] == 1) continue;
        	//왼쪽에 있는 학생이 체육복이 없는경우
        	if(reserve[i] > 1 && stds[reserve[i]-1] == 0) {
        		stds[reserve[i]] = 1;
        		stds[reserve[i]-1] = 1;
        	}
        	//오른쪽에 있는 학생이 체육복이 없는경우
        	else if(reserve[i] < n && stds[reserve[i]+1] == 0) {
        		stds[reserve[i]] = 1;
        		stds[reserve[i]+1] = 1;
        	}
        }
        // 체육복이 1개 이상 가지고 있는 학생의 수
        for(int i = 1; i <= n; i++) {
        	if(stds[i] != 0) answer++;
        }
        
        return answer;
    }

}

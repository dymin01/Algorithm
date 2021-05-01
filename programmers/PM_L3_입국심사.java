package programmers;

import java.util.Arrays;

/**
 * <문제 요약> 구해야 하는 것: 모든 사람이 심사를 받는데 걸리는 최소의 시간
 * 유형 : 이분탐색
 * 요구 개념 : 이분탐색
 * 
 * <풀이법 요약>
 * 
 * 문제에 이분탐색이라고 나와있어서 그나마 풀 수 있었다...
 * 
 * 1. 중간 시간에 몇명의 사람을 처리할 수 있는지 구한다.
 * 2-1. 시간이 충분하다면 시간을 줄이는 왼쪽 범위로 범위를 옮겨 이분탐색을 다시 진행한다.
 * 2-2. 시간이 부족하다면 시간을 늘리는 오른쪽 범위롤 범위를 옮겨 이분탐색을 다시 진행한다.
 * 
 * 주의할점...
 * 형변환...int형이랑 long형 주의
 * 
 */

public class PM_L3_입국심사 {

	public static void main(String[] args) {
		
		PM_L3_입국심사 sol = new PM_L3_입국심사();
		
		int n = 6;
		int[] times = {7, 10};
		
		System.out.println(sol.solution(n, times));

	}
	
	static public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long right = (long) n * times[times.length-1];
        long left = 1L;
        
        answer = right;
        
        while(left <= right) {
        	
        	long mid = (right + left) / 2;
        	long cnt = 0L;
        	
        	// 처리할 수 있는 사람의 수
        	for(Integer time : times) {
        		cnt += (mid / time);
        	}
        	
        	// 처리할 수 있는 사람이 기다리는 사람보다 적을경우
        	if(cnt < n) {
        		// 더 많은 시간으로 범위를 옮긴다.
        		left = mid + 1L;
        	}
        	// 처리할 수 있는 사람이 기다리는 사람보다 많을경우
        	else {
        		// 더 작은 시간으로 범위를 옮긴다.
        		right = mid - 1L;
        		//answer이 mid보다 클 경우 answer을 바꿔준다.
        		answer = answer > mid ? mid : answer;
        	}
        }
        
        return answer;
    }

}

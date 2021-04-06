package programmers;
/**
 * <문제 요약>
 * 구해야 하는 것: 최소 몇대의 카메라를 설치해야 하는지
 * 제약 사항: 차량은 10,000대 이하, 진입/진출 지점에 설치해도 된다.
 * 문제 유형: 그리디
 * 요구 개념: 그리디
 * 
 * 과제로 나왔던 냉장고 문제와 같다.
 * 
 * <풀이법 요약>
 * 1. 진출 지점을 기준으로 오름차순 정렬한다.
 * 2. 진출 지점보다 큰 진입지점이 나오면 카메라가 더 필요하다.
 */


import java.util.Arrays;

public class PM_L3_단속카메라 {

	public static void main(String[] args) {
		
		PM_L3_단속카메라 sol = new PM_L3_단속카메라();
				
		int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};

		System.out.println(sol.solution(routes));
	}
	
	public int solution(int[][] routes) {
        int answer = 0;
        
        //진출 지점 routes[n][1]을 기준으로 오름차순 정렬한다.
        Arrays.sort(routes, (o1, o2) -> Integer.compare(o1[1], o2[1]));
        
        answer = 1;
        int max = routes[0][1];
		for(int i = 1; i < routes.length; i++) {
			// 앞의 진출지점보다 큰 진입지점이 들어오면 카메라가 더 필요하다.
			if(max < routes[i][0]) {
				max = routes[i][1];
				answer++;
			}
		}
        return answer;
    }

}

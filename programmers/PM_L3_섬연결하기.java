package programmers;

/**
 * <문제 요약>
 * 구해야 하는 것: 모든섬을 연결하는 최소비용
 * 제약 사항: n은 100이하, 연결할 수 없는 섬은 주어지지 않는다, 모든 섬 사이에 다리 건설 비용이 주어지지 않는다.
 * 문제 유형: 그리디
 * 요구 개념: 그리디, 크루스칼 알고리즘
 * <풀이법 요약>
 * 1. 주어진 섬의 비용을 기준으로 정렬한다.
 * 2. 나동빈님 크루스칼 알고리즘을 본다. * 중요 *
 * 3. 그대로 코딩한다.
 * 4. 공부한다.
 */


import java.util.Arrays;
import java.util.Comparator;

public class PM_L3_섬연결하기 {

	public static void main(String[] args) {
		PM_L3_섬연결하기 sol = new PM_L3_섬연결하기();
		int n = 8;
		int[][] costs = {{1,7,12}, {4,7,13}, {1,5,17},{3,5,20},{2,4,24},{1,4,28},{3,6,37},{5,6,45},{2,5,62},{1,2,67},{5,7,73}};
		System.out.println(sol.solution(n, costs));
	}
	
	// 사이클 테이블
	static int[] node;
	public int solution(int n, int[][] costs) {
        int answer = 0;
        // 비용을 기준으로 오름차순으로 정렬한다.
        Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
        
        node = new int[n];
        
        // 사이클 테이블의 기본값은 자기자신이다.
        for(int i = 0; i < n; i++) {
        	node[i] = i;
        }
        // 간선의 길이만큼 반복한다.
        for(int i = 0; i < costs.length; i++) {
        	// 시작 점의 정점
        	int start = findParent(costs[i][0]);
        	// 종료 점의 정점
        	int end = findParent(costs[i][1]);
        	// 비용
        	int cost = costs[i][2];
        	
        	// 정점이 다르면 연결되지 않은것이다.
        	if(start != end) {
        		// 정점을 바꿔준다.
        		node[end] = start;
        		answer += cost;
        	}
        }
        
        return answer;
    }
	// 정점을 찾는 함수
	public int findParent(int position) {
		// 정점이 자기자신과 같을경우 자기자신을 반환한다.
		if(node[position] == position) return position;
		// 그렇지 않을경우 정점의 정점을 찾아간다.
		return node[position] = findParent(node[position]);
	}
	
}

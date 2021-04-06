package programmers;

/**
 * <문제 요약>
 * 구해야 하는 것: 모든섬을 연결하는 최소비용
 * 제약 사항: n은 100이하, 연결할 수 없는 섬은 주어지지 않는다, 모든 섬 사이에 다리 건설 비용이 주어지지 않는다.
 * 문제 유형: 그리디
 * 요구 개념: 그리디, 그래프, 크루스칼 알고리즘
 * <풀이법 요약>
 * 1. 주어진 섬의 비용을 기준으로 정렬한다.
 * 2. 결함 관계를 나타내는 배열을 만든다.
 * 3. 주어진 연결cost에서 두 섬의 상위 섬을 확인한다.
 * 3-1 만약 상위 정점의 섬이 다를경우 연결되니 않은것으로 판단하고, 연결시킨다.
 */


import java.util.Arrays;
import java.util.Comparator;

public class PM_L3_섬연결하기_2 {

	public static void main(String[] args) {
		PM_L3_섬연결하기_2 sol = new PM_L3_섬연결하기_2();
		int n = 4;
		int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		System.out.println(sol.solution(n, costs));
	}
	
	static int[] node;
	public int solution(int n, int[][] costs) {
        int answer = 0;
        Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
        
        // 유니온 관계를 나타내는 배열
        node = new int[n];
        for(int i = 0; i < n; i++) {
        	node[i] = i;
        }
        
        for(int i = 0; i < costs.length; i++) {
        	// 정점이 다르면 정점을 연결해 준다.
        	if(!find(costs[i][0], costs[i][1])) {
        		answer += costs[i][2];
        		// 하나의 정점으로 만든다.
        		unionParent(costs[i][0], costs[i][1]);
        	}
        }
        
        return answer;
    }
	
	// 유니온 시키기
	public static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		if(a < b) node[b] = a;
		else node[b] = a;
	}
	// 정점이 같은지 찾는 함수
	// a의 정점과 b의 정점이 같으면 이미 연결되 있음
	// 다르다면 아직 연결되지 않았다.
	public static boolean find(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		if(a == b) return true;
		else return false;
	}
	
	/**
	 * 유니온의 정점을 찾는 함수
	 * 자기자신이 아니면 정점을 갖고 있는것이기 때문에 재귀로 찾는다.
	 * **/
	public static int getParent(int child) {
		// 정점이 자기자신이면 자기자신을 반환
		if(node[child] == child) return child;
		// 정점이 자기자신과 다르면 정점의 정점을 반환(재귀)
		return node[child] = getParent(node[child]);
	}

}

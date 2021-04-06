package baekjoon;
/**
 * <문제 요약>
 * 구해야 하는 것: 최소스패닝 트리
 * 제약 사항: 
 * 문제 유형: MST
 * 요구 개념: MST
 * 
 * <풀이법 요약>
 * 1. 간선의 root를 자기 자신으로 초기화 한다.
 * 2. costs 배열을 만들어 비용을 기준으로 오름차순 정렬한다.
 * 3. costs를 선회하면서 시작점과 종료점의 root가 같지 않으면 union 시켜준다.(parent를 같게 한다.)
 * 4. 4번이 진행될때 cost를 answer에 더한다.
 * 5. 3번과 4번을 반복한다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_G4_1197_최소스패닝트리 {

	static int V, E;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// 점의 root를 저장할 배열
		parent = new int[V+1];
		int[][] costs = new int[E+1][3];
		
		// costs 배열을 초기화
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			costs[i][0] = Integer.parseInt(st.nextToken());
			costs[i][1] = Integer.parseInt(st.nextToken());
			costs[i][2] = Integer.parseInt(st.nextToken());
		}
		// 비용을 기준으로 정렬
		Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		// 정점의 root는 자기자신으로 초기화
		for(int i = 0; i <= V; i++) {
			parent[i] = i;
		}
		
		int answer = 0;
		
		// 간선의 갯수만큼
		for(int i = 0; i < costs.length; i++) {
			// 시작점과 종료점의 root 정점이 다를경우 써클이 일어나지 않기 때문에 열겨할 수 있다.
			if(!find(costs[i][0], costs[i][1])) {
				// 비용을 더해준다.
				answer += costs[i][2];
				// 두 정점의 root를 같게 만들어 준다.
				unionParent(costs[i][0], costs[i][1]);
			}
		}
		
		System.out.println(answer);
		
	}
	// 두 정점의 root를 같게 만드는 함수
	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		// 작은 정점의 root로 저장시킨다.
		if(a < b) parent[b] = a;
		else parent[a] = b;
		
	}

	// 두 정점의 root를 비교해 서클인지 확인한다.
	private static boolean find(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		//  root정점이 같으면 써클이다.
		if(a == b) return true;
		else return false;
	}
	// 정점의 root정점을 찾는다.
	private static int getParent(int child) {
		// 정점이 자기 자신이면 root이다.
		if(parent[child] == child) return child;
		// 정점이 자기 자신이 아니면 root를 재귀로 찾는다.
		return parent[child] = getParent(parent[child]);
	}
}

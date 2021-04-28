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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G4_1197_최소스패닝트리_프림 {

	static int V, E;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		boolean[] v = new boolean[V+1];
		
		ArrayList<int[]>[] list = new ArrayList[V+1];
		for(int i = 0; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[from].add(new int[] {to, weight});
			list[to].add(new int[] {from, weight});
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		pq.add(new int[] {1, 0});
		int ans = 0;
		while(!pq.isEmpty()) {
			int[] curnode = pq.poll();
			int cur = curnode[0];
			int cost = curnode[1];
			if(v[cur]) continue;
			v[cur] = true;
			ans += cost;
			for(int[] node : list[cur]) {
				if(!v[node[0]]) {
					pq.add(new int[] {node[0], node[1]});
				}
			}
			
		}
		
		System.out.println(ans);
	}
}

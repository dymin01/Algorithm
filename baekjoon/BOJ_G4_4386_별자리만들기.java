package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의 : n개의 별을 이용하여 최소비용으로 별자리를 만들어라
 * 문제 유형 : MST
 * 제약 사항 : 1 <= n <= 100
 * <풀이 요약>
 * 1. 각 별들의 좌표를 저장한다.
 * 2. 각 별들간의 간선의 길이(비용)을 저장한다.
 * 3. 별들간의 간선의 길이(비용)을 기준으로 정렬한다.
 * 4. 정렬된 간선중 가장 작은 간선부터 선택하여 크루스칼 알고리즘에 적용한다.
 * 
 * 
 * 간만프 간적쿠 쿠루스칼 알고리즘 사용
 * ... 까먹어서 크루스칼 다시 보고 했음... 더 공부해야 겠음...
 * 
 * 
 */

public class BOJ_G4_4386_별자리만들기 {

	static class Star{
		double x, y;

		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to;
		double distance;
		
		public Edge(int from, int to, double distance) {
			super();
			this.from = from;
			this.to = to;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.distance, o.distance);
		}
	}
	
	// n 별의 개수, E 간선의 개수
	static int n, E;
	// 간선 리스트
	static ArrayList<Edge> EdgeList;
	// parent 배열
	static int[] parent;
	static int[] rank;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		
		E = (n * (n-1))/2;
		
		parent = new int[n];
		rank = new int[n];
		
		Star[] stars = new Star[n];
		
		// 별의 좌표 입력
		for(int i = 0; i < n; i++) {
			parent[i] = i;
			st = new StringTokenizer(br.readLine(), " ");
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			stars[i] = new Star(x, y);
		}
		
		EdgeList = new ArrayList<>();
		
		// 별들 간의 간선 거리 입력
		for(int i = 0; i < n; i++) {
			Star from = stars[i];
			
			for(int j = i+1; j < n; j++) {
				Star to = stars[j];
				
				double dist = Math.sqrt(Math.pow(from.x - to.x, 2) + Math.pow(from.y - to.y, 2));
				
				EdgeList.add(new Edge(i, j, dist));
			}
		}
		
		// 간선의 길이를 기준으로 정렬
		EdgeList.sort(null);
		
		double ans = 0;
		int cnt = 0;
		
		// 가장 작은 간선부터
		for(Edge edge : EdgeList) {
			// from별과 to 별이 서로 연결되어 있지 않으면
			if(!find(edge.from, edge.to)) {
				// 간선을 만든다
				ans += edge.distance;
				// 간선을 만들때 별들을 union시킨다.
				unionParent(edge.from, edge.to);
			}
		}
		
		System.out.printf("%.2f", ans);
		

	}

	private static void unionParent(int a, int b) {
		
		a = getParent(a);
		b = getParent(b);
		
		if(rank[a] > rank[b]) {
			parent[b] = a;
		}else {
			parent[a] = b;
			if(rank[a] == rank[b]) {
				rank[b]++;
			}
		}
		
	}

	private static boolean find(int a, int b) {
		
		a = getParent(a);
		b = getParent(b);
		
		if(a == b) return true;
		else return false;
	}

	private static int getParent(int child) {
		if(parent[child] == child) return child;
		return parent[child] = getParent(parent[child]);
	}

}

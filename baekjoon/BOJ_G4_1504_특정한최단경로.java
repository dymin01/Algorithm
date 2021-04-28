package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: V1, V2를 지나는 최단거리를 구하라
 * 제약 사항: V1과 V2를 무조건 지나가야 한다. 이용한 정점을 다시 이용할 수 있다.
 * 문제 유형: 최단거리구하기, 다익스트라
 * 요구 개념: 다익스트라
 * 
 * <풀이법 요약>
 * 1. 1 -> V1 -> V2 -> N으로 가는 경우와, 1 -> V2 -> V1 -> N 으로 가는 경우를 구한다.
 * 2. 둘 중 작은 값을 구하고 만약 값이 답이 아닐경우 -1을 출력한다.
 * 
 */



public class BOJ_G4_1504_특정한최단경로 {

	static class Node implements Comparable<Node>{
		int end, weight;

		public Node(int end, int weight) {
			super();
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	static int N, E;
	static ArrayList<Node>[] list;
	static int[] dist;
	static final int MAX = 800001;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 정점의 개수
		N = Integer.parseInt(st.nextToken());
		// 간선의 개수
		E = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		dist = new int[N+1];
		
		for(int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b, weight));
			list[b].add(new Node(a, weight));
		}
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int ans1 = 0;
		int ans2 = 0;
		//int temp = dijkstra(v1, v2);
		ans1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
		ans2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);
		
		int cost = Math.min(ans1, ans2);
		
		if(cost >= MAX) {
			System.out.println(-1);
		}else {
			System.out.println(cost);
		}
		
	}
	
	public static int dijkstra(int start, int end) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(start, 0));
		Arrays.fill(dist, MAX);
		dist[start] = 0;
		boolean[] check = new boolean[N+1];
		check[start] = true;
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			int cur = curNode.end;
			int cost = curNode.weight;
			
			if(dist[cur] < cost) continue;
			
			for(Node node : list[cur]) {
				if(dist[node.end] > dist[cur] + node.weight) {
					dist[node.end] = dist[cur] + node.weight;
					queue.add(new Node(node.end, dist[node.end]));
				}
			}
		}
		
		return dist[end];
		
	}

}

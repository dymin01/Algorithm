package baekjoon;
/**
 * <문제 요약>
 * 구해야 하는 것: 시작 정점에서 모든 정점으로 가는 최단 경로 비용
 * 제약 사항: 
 * 문제 유형: 다익스트라
 * 요구 개념: 다익스트라
 * 
 * 이 문제는 개념이 좀 부족.... 더 공부...ㅜㅜ
 * 
 * <풀이법 요약>
 * 1. 다익스트라 알고리즘을 이용한다....
 * 2. 거리를 나타내는 dist를 max값으로 초기화 한다.
 * 3. 정점에서 갈 수 있는 정점과 거리를 Node class를 이용하여 List배열에 저장한다
 * 4. 우선순위 큐를 이용하여 다익스트라 알고리즘을 구현한다.
 * 5. 정점에서 이동할 수 있는 정점을 비교하여 더 작은값을 취한다.
 * 6. 시작 정점에서 현재 노드까지의 거리 + 이동할 수 있는 노드의 거리 VS 시작정점에서 이동할 수 있는 노드의 거리 두개를 비교한다. 
 * 6. 위 상황을 반복하여 시작점에서 모든 정점에 가는 길이(dist)를 구한다.
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ_G5_1753_최단경로 {

	static int[] dist;
	static List<Node>[] list;
	static int V, E, begin;
	static boolean[] check;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		begin = Integer.parseInt(br.readLine());
		list = new ArrayList[V+1];
		dist = new int[V+1];
		check = new boolean[V+1];

		Arrays.fill(dist, Integer.MAX_VALUE);
					
		for(int i = 0; i < V+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[start].add(new Node(end, weight));
		}
		
		dijkstra(begin);
		
		for(int i = 1; i <= V; i++) {
			if(dist[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(dist[i]);
		}
		
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			int cur = curNode.end;
			
			if(check[cur]) continue;
			
			check[cur] = true;
			
			for(Node node : list[cur]) {
				if(dist[node.end] > dist[cur] + node.weight) {
					dist[node.end] = dist[cur] + node.weight;
					queue.add(new Node(node.end, dist[node.end]));
				}
			}
		}
	}
}

class Node implements Comparable<Node>{
	int end, weight;

	public Node(int end, int weigt) {
		this.end = end;
		this.weight = weigt;
	}
	
	@Override
	public int compareTo(Node o) {
		return weight - o.weight;
	}
}

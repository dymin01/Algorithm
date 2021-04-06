package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_1753_최단경로3 {

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
		
		check = new boolean[V+1];
		
		dist = new int[V+1];
		List<Node2>[] list = new ArrayList[V+1];
		
		for(int i = 0; i < V+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[start].add(new Node2(end, weight));
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
			
			for(int i = 0; i < list[cur].size(); i++) {
				if(dist[cur] > dist[i] + list[cur].get(i).weight) {
					dist[cur] = dist[i] + list[cur].get(i).weight;
					queue.offer(new Node(list[cur].get(i).end, dist[list[cur].get(i).end]));
				}
			}
		}
	}
}

class Node3 implements Comparable<Node>{
	int end, weight;

	public Node3(int end, int weigt) {
		this.end = end;
		this.weight = weigt;
	}
	
	@Override
	public int compareTo(Node o) {
		return weight - o.weight;
	}
}


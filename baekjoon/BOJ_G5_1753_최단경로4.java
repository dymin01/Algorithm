package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_1753_최단경로4 {

	static class Node implements Comparable<Node>{
		int next, dis;

		public Node(int next, int dis) {
			super();
			this.next = next;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dis, o.dis);
		}
		
	}
	
	static ArrayList<Node>[] list;
	static int[] dist;
	
	static final int INF = 987654321;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int begin = Integer.parseInt(br.readLine());
		
		list = new ArrayList[V+1];
		for(int i = 0; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		
		dist = new int[V+1];
		
		Arrays.fill(dist, INF);
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			list[start].add(new Node(end, dis));
		}
		
		sol(begin);
		
		for(int i = 1; i <= V; i++) {
			if(dist[i] > INF) System.out.println(INF);
			else System.out.println(dist[i]);
		}
		
		
	}

	private static void sol(int begin) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(begin, 0));
		dist[begin] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.next;
			
			for(Node next : list[cur]) {
				if(dist[next.next] > dist[cur] + next.dis) {
					dist[next.next] = dist[cur] + next.dis;
					pq.add(new Node(next.next, dist[next.next]));
				}
			}
		}
		
		
	}

}

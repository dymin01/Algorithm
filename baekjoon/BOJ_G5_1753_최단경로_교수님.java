package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_1753_최단경로_교수님 {

	static int[] dist;
	static List<Node>[] list;
	static int V, E, begin;
	static boolean[] check;

	static class Edge{
		int v,w;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		List<Edge>[] adj = new ArrayList[V];
		
		for(int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[u-1].add(new Edge(v-1, w));
		}
		int[] distance = new int[V];
		Arrays.fill(distance, Integer.MAX_VALUE);
		boolean[] check = new boolean[V];
		
		distance[K-1] = 0;
		
		for(int i = 0; i < V-1; i++) {
			int min = Integer.MAX_VALUE;
			int w = -1;
			
			for(int j = 0; j < V; j++) {
				if(!check[j] && distance[j] < min) {
					min = distance[j];
					w=j;
				}
			}
			if(w==-1) break;
			check[w] = true;
			
			for(Edge next : adj[w]) {
				int v = next.v;
				if(!check[v] && distance[v] > distance[w] + next.w) {
					distance[v] = distance[w] + next.w;
				}
			}
		}
		for(int i = 0; i < V; i++) {
			if(distance[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(distance[i]);
			}
		}
		
	}

}

package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D4_3124_최소스패닝트리 {

	static class Edge implements Comparable<Edge>{
		int a, b, c;
		public Edge(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.c, o.c);
		}
	}
	
	static int V, E;
	static int[] p;
	static int[] r;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				pq.offer(new Edge(A-1, B-1, C));
			}
			
			makeSet();
			
			long sum = 0L;
			
			while(!pq.isEmpty()) {
				Edge next = pq.poll();
				if(!isConnect(next.a, next.b)) {
					union(next.a, next.b);
					sum += next.c;
				}
			}
			
			System.out.println("#" + t + " " + sum);
			
		}
		

	}
	
	static void makeSet() {
		p = new int[V];
		for(int i = 0; i < V; i++) {
			p[i] = i;
		}
		r = new int[V];
		Arrays.fill(r, 1);
	}
	
	static int find(int x) {
		if(x == p[x]) return p[x];
		return p[x] = find(p[x]);
	}
	
	static boolean isConnect(int x, int y) {
		if( find(x) == find(y)) return true;
		return false;
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px == py) return;
		
		if(r[px] >= r[py]) {
			p[py] = px;
			r[px] += r[py];
		}else {
			p[px] = py;
			r[py] += r[px];
		}
		
	}


}

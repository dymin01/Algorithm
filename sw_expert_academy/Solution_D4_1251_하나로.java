package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D4_1251_하나로 {
	
	static class Edge implements Comparable<Edge>{
		int from, to;
		double dis;
		public Edge(int from, int to, double dis) {
			this.from = from;
			this.to = to;
			this.dis = dis;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.dis, o.dis);
		}
	}
	
	static int[] p;
	static int[] r;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] R = new int[N];
			int[] C = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				R[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				C[i] = Integer.parseInt(st.nextToken());
			}
			double E = Double.parseDouble(br.readLine());
			
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
			for(int i = 0; i < N-1; i++) {
				for(int j = i+1; j < N; j++) {
					double rr = Math.pow(Math.abs(R[i] - R[j]), 2);
					double cc = Math.pow(Math.abs(C[i] - C[j]), 2);
					double dis = (rr + cc);
					pq.add(new Edge(i, j, dis));
				}
			}
			makeSet(N);
			
			double sum = 0L;
			
			while(!pq.isEmpty()) {
				Edge next = pq.poll();
				if(!isConnect(next.from, next.to)) {
					union(next.from, next.to);
					sum += (E * (next.dis));
				}
			}
			
			System.out.println("#" + t + " " + Math.round(sum));
			
		}
		
	}
	
	static void makeSet(int N) {
		p = new int[N];
		r = new int[N];
		for(int i = 0; i < N; i++) {
			p[i] = i;
		}
		Arrays.fill(r, 1);
		
	}
	
	static int find(int x) {
		if(x == p[x]) return x;
		return p[x] = find(p[x]);
	}
	
	static boolean isConnect(int x, int y) {
		if(find(x) == find(y)) return true;
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

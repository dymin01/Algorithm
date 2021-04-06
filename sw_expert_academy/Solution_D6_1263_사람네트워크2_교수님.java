package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D6_1263_사람네트워크2_교수님 {
	
	static class Edge implements Comparable<Edge>{
		int v;
		int cost;
		public Edge(int v, int cost) {
			super();
			this.v = v;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			boolean[][] arr = new boolean[N][];
			
			for(int i = 0; i < N;  i++) {
				for(int j = 0; j < N; j++) {
					if(Integer.parseInt(st.nextToken()) == 1) {
						arr[i][j] = true;
					}
				}
			}
			
			int result = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				
				int[] costs = new int[N];
				Arrays.fill(costs, Integer.MAX_VALUE);
				
				costs[i] = 0;
				PriorityQueue<Edge> queue = new PriorityQueue<>();
				for(int j = 0; j < N; j++) {
					if(arr[i][j]) {
						queue.add(new Edge(j, 1));
						costs[j] = 1;
					}
				}
				while(!queue.isEmpty()) {
					Edge e = queue.poll();
					if(e.cost > costs[e.v]) continue;
					for(int j = 0; j < N; j++) {
						if(arr[e.v][j] && costs[j] > e.cost) {
							costs[j] = e.cost + 1;
							queue.add(new Edge(j, costs[j]));
						}
					}
				}
				int sum = 0;
				for(int j = 0; j < N; j++) {
					sum += costs[j];
				}
				result = result<sum ? result : sum;
				
			}
			
			System.out.println("#" + t + " " + result);
			
		}
		

	}

}

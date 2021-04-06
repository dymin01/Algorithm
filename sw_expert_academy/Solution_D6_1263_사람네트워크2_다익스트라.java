package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D6_1263_사람네트워크2_다익스트라 {
	
	static class Node implements Comparable<Node>{
		int t, w;
		public Node(int t, int w) {
			super();
			this.t = t;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static int[] dist;
	static boolean[] check;
	static List<Node>[] list;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			list = new ArrayList[N];
			for(int i = 0; i < N; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < Math.pow(N, 2); i++) {
				if(Integer.parseInt(st.nextToken()) == 0) continue;
				int from = i/N;
				int to = i%N;
				list[from].add(new Node(to, 1));
			}
			
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				check = new boolean[N];
				dist = new int[N];
				Arrays.fill(dist, Integer.MAX_VALUE);
				int sum = 0;
				dijkstra(i);
				for(int j = 0; j < N; j++) {
					sum += dist[j];
				}
				min = Math.min(min, sum);
			}
			System.out.println("#" + t + " " + min);
		}
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(start, 0));
		dist[start] = 0;
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			int cur = curNode.t;
			
			if(check[cur]) continue;
			check[cur] = true;
			
			for(int i = 0; i < list[cur].size(); i++) {
				if(dist[cur] > dist[i] + list[cur].get(i).w) {
					dist[cur] = dist[i] + list[cur].get(i).w;
					queue.offer(new Node(list[cur].get(i).t, dist[list[cur].get(i).t]));
				}
			}
		}
	}

}

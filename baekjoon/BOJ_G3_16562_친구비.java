package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_16562_친구비 {

	static int[] parent;
	static int[] rank;
	static int[] cost;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		rank = new int[N+1];
		cost = new int[N+1];
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			union(from, to);
		}
		int ans = 0;
		for(int i = 1; i <= N; i++) {
			if(parent[i] == i) {
				ans += cost[i];
			}
		}
		if(ans > K) {
			System.out.println("Oh no");
		}else {
			System.out.println(ans);
		}
	}
	
	public static int getParent(int a) {
		if(a == parent[a]) return a;
		return parent[a] = getParent(parent[a]);
	}
	
	public static void union(int a, int b) {
		int A = getParent(a);
		int B = getParent(b);
		if(A == B) return;
		
		if(cost[A] < cost[B]) {
			parent[B] = A;
		}else if(cost[A] > cost[B]) {
			parent[A] = B;
		}else {
			if(A < B) {
				parent[B] = A;
			}else {
				parent[A] = B;
			}
		}
		
	}

}

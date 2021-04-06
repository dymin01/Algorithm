package jungol;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUNGOL_1863_종교 {

	static int N,M;
	static int[] parent;
	static int[] rank;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		rank = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			Union(from, to);
		}
		
		int ans = 0;
		
		for(int i = 1; i <= N; i++) {
			if(i == parent[i]) ans++;
		}
		System.out.println(ans);
		
	}
	
	public static int getParent(int a) {
		if(parent[a] == a) return a;
		return parent[a] = getParent(parent[a]);
	}
	
	public static void Union(int a, int b) {
		int aRoot = getParent(a);
		int bRoot = getParent(b);
		
		if(aRoot == bRoot) return;
		
		if(rank[aRoot] > rank[bRoot]) {
			parent[aRoot] = bRoot;
		}else {
			if(aRoot > bRoot) {
				parent[aRoot] = bRoot;
			}else {
				parent[bRoot] = aRoot;
			}
			
			if(rank[aRoot] == rank[bRoot]) {
				if(aRoot > bRoot) {
					rank[aRoot]++;
				}else {
					rank[bRoot]++;
				}
			}
			
		}
		
	}

}

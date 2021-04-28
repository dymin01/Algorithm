package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_2644_촌수계산 {

	static int ans;
	
	static int[][] map;
	static boolean[] v;
	static int n;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		int num = Integer.parseInt(br.readLine());
		for(int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		v = new boolean[n+1];
		v[start] = true;
		DFS(start, end, 0);

		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
		
	}

	private static void DFS(int start, int end, int cnt) {
		
		if(ans < cnt) return;
		
		if(start == end) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(map[start][i] == 0 || v[i]) continue;
			v[i] = true;
			DFS(i, end, cnt+1);
			v[i] = false;
		}
	}
}

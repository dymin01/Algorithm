package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ_G4_2458_키순서_DFS {

	static int N;
	static int[][] map;
	static boolean[] v;
	// 나보다 작은것
	static int[] sml;
	// 나보다 큰것
	static int[] tol;
	
	static int tt;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];

		sml = new int[N+1];
		tol = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 더 작은것
			int sm = Integer.parseInt(st.nextToken());
			// 더 큰것
			int tl = Integer.parseInt(st.nextToken());
			// sm은 tl보다 작다.
			map[sm][tl] = 1;
		}

		for(int i = 1; i <= N; i++) {
			tt = -1;
			v = new boolean[N+1];
			DFS(i);
			tol[i] = tt;
		}
		int ans = 0;
		for(int i = 1; i <= N; i++) {
			if(sml[i] + tol[i] == N-1) ans++;
		}
		
		System.out.println(ans);
		
	}

	private static void DFS(int cur) {
		tt++;
		for(int i = 1; i <= N; i++) {
			if(v[i]) continue;
			if(map[cur][i] == 1) {
				v[i] = true;
				sml[i]++;
				DFS(i);
			}
		}
		
	}

}

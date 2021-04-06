package baekjoon;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_S2_1260_DFSBFS {

	static int N, M, V;
	
	static int[][] map;
	static boolean[] v;
	static String str;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		map = new int[1001][1001];
		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = 1;
			map[b][a] = 1;
		}
		v = new boolean[N+1];
		str = "";
		DFS(V);
		System.out.println(str);
		v = new boolean[N+1];
		str = "";
		BFS(V);
		System.out.println(str);

	}
	
	public static void DFS(int cur) {
		if(v[cur]) return;
		v[cur] = true;
		str += (cur + " ");
		for(int i = 1; i <= N; i++) {
			if(map[cur][i] == 1 && !v[i]) {
				DFS(i);
			}
		}
	}
	
	public static void BFS(int cur) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(cur);
		v[cur] = true;
		while(!queue.isEmpty()) {
			int c = queue.poll();
			str += (c + " ");
			for(int i = 1; i <= N; i++) {
				if(map[c][i] == 1 && !v[i]) {
					v[i] = true;
					queue.offer(i);
				}
			}
		}
	}

}

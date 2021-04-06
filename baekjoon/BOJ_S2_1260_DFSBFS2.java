package baekjoon;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class BOJ_S2_1260_DFSBFS2 {

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
			//a노드
			int a = sc.nextInt();
			//b노드
			int b = sc.nextInt();
			//a 와 b가 서로 연결되어 있는것으로 초기화 한다.
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
		// 시작 노드를 queue에 넣어준다.
		queue.offer(cur);
		v[cur] = true;
		// 탐색할 노드가 없을때까지 탐색한다.
		while(!queue.isEmpty()) {
			// 현재 노드
			int c = queue.poll();
			str += (c + " ");
			for(int i = 1; i <= N; i++) {
				// 현재 노드에서 연결되어있는 노드들 중 방문하지 않은 노드를 찾는다.
				if(map[c][i] == 1 && !v[i]) {
					v[i] = true;
					// 찾은 노드를 queue에 추가한다.
					queue.offer(i);
				}
			}
		}
	}

}

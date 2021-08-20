package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 복구해야 하는 회선의 개수와 회선을 구하라
 * 
 * 문제 유형
 * - 그래프 탐색
 * - 다익스트라
 * 
 * 제약 사항
 * 1 <= N <= 1,000
 * 1 <= C <= 10
 * 
 * <풀이 요약>
 * 처음에는 크루스칼인줄 알았다.
 * 다익스트라 알고리즘을 다시 공부하면서 시작점에서 모든 정점까지의 최단거리가 나오는것을 알 수 있었다.
 * 회선 출력하는 방식에서 좀 막혔다.
 * 
 * 1. 입력을 인접리스트로 받는다.
 * 2. 현재 노드에서 연결된 가장 간선 중 거리가 짧은 회선을 선택한다.
 * 3. 회선을 선택했다면 Path에 연결된 회선을 저장한다.
 * 4. 모든 노드를 탐색했다면 종료한다.
 * 5. 회선의 개수와 회선을 출력한다. 
 * 
 * 그래프 탐색 좀 더 공부해야겠다.
 * 
 */

public class BOJ_G2_2211_네트워크복구 {

	static class Node implements Comparable<Node> {
		int end, dist;

		public Node(int end, int dist) {
			super();
			this.end = end;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
		
	}
	
	static final int INF = 987654321;
	
	static int[] dist;
	static ArrayList<Node>[] list;
	static int N, M;
	static boolean[] visited;
	static int[] path;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		
		list = new ArrayList[N+1];
		
		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
			dist[i] = INF;
		}
		visited = new boolean[N+1];
		
		// 연결된 간선을 저장한 배열
		// 1 -> 2 연결되는 길 이면
		// 인덱스 2 값 1
		path = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(end, dist));
			list[end].add(new Node(start, dist));
			
		}
		
		// 다익스트라 알고리즘
		dijkstra(1);
		// 회선의 개수는 N-1개 이다.
		System.out.println(N-1);
		for(int i = 2; i <= N; i++) {
			// 회선 출력			
			System.out.println(path[i] + " " + i);
		}
	}

	private static void dijkstra(int start) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			
			Node curNode = pq.poll();
			int cur = curNode.end;
			int curDist = curNode.dist;
			
			for(Node next : list[cur]) {
				// 이전에 길의 길이보다 cur에서 가는 길이 더 짧으면
				if(dist[next.end] > dist[cur] + next.dist) {
					dist[next.end] = dist[cur] + next.dist;
					// cur -> next 길로 연결되는것이다.
					path[next.end] = cur;
					pq.add(new Node(next.end, dist[next.end]));
				}
			}
			
		}
		
	}

}

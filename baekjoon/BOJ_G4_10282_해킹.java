package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/***
 * <문제 요약>
 * 문제 정의
 * - 총 몇대의 컴퓨터가 감염되며 그에 걸리는 시간이 얼마인지 구하라
 * 
 * 문제 유형
 * - 다익스트라
 * 
 * 제약 사항
 * 1 <= n <= 10,000
 * 1 <= d <= 100,000
 * 1 <= c <= n
 * 
 * 1 <= a, b <= n
 * a != b
 * 0 <= s <= 1,000
 * 
 * <풀이 요약>
 * 
 * 1. Node 인접행렬을 만든다.
 * 2. 다익스트라 알고리즘을 사용하여 시작지점부터 마지막 노느까지의 걸리는 시간을 구한다.
 * 3. dist 배열은 큰값으로 초기화 한다.
 * 3-1. 우선순위 큐에 시작노드를 저장한다.
 * 3-2. 현재 노드에서 연결된 노드 중 (현재노드까지 걸리는 시간 + 현재노드에서 다음노드 까지 걸리는 시간) 이 시작지점부터 다음 노드까지 걸리는 시간보다 작으면
 * 3-3. 걸리는 시간을 수정하고 우선순위 큐에 넣는다.
 * 3-4. 3-1 ~ 3-3 과정을 반복한다.
 * 4. 갈 수 있는 노드를 모두 순회하였으면 dist 거리 배열의 값이 INF가 아닌것의 갯구와 최대값을 구한다.
 * 5. 출력한다.
 * 
 * 
 */

public class BOJ_G4_10282_해킹 {
	
	static class Node implements Comparable<Node>{
		int next, time;

		public Node(int next, int time) {
			super();
			this.next = next;
			this.time = time;
		}
		// 시간(거리)가 짧은것 부터 선택한다.
		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
		
	}
	
	static int n, d;
	static int[] dist;
	static ArrayList<ArrayList<Node>> list;
	
	static final int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int TC = Integer.parseInt(br.readLine());
		
		while(TC-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 인접 리스트
			list = new ArrayList<>();
			
			for(int i = 0; i <= n; i++) {
				list.add(new ArrayList<>());
			}
			// 시작점부터 걸리는 시간(거리)
			dist = new int[n+1];
			Arrays.fill(dist, INF);
			
			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				list.get(b).add(new Node(a, s));
			}
			// 다익스트라 알고리즘
			dijkstra(c);
			
			int cnt = 0;
			int answer = 0;
			for(int i = 1; i <= n; i++) {
				if(dist[i] != INF) {
					cnt++;
					answer = Math.max(answer, dist[i]);
				}
			}
			
			System.out.println(cnt + " " + answer);
			
		}

	}

	private static void dijkstra(int start) {
		// 우선순위 큐
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.next;
			int time = curNode.time;
			
			// 걸리는 시간(거리)가 현재 거리보다 작으면 계산하지 않아도 된다.
			if(dist[cur] < time) continue;
			// 인접한, 갈 수 있는 노드 중
			for(Node next : list.get(cur)) {
				// 시작에서 다음노드 까지 걸리는 시간이 시작에서 현재노드까지 걸리는 시간 + 다음 노드로 이동하는데 걸리는 시간 보다 크면 
				if(dist[next.next] > dist[cur] + next.time) {
					// 시작에서 다음노드까지 걸리는 시간을 수정
					dist[next.next] = dist[cur] + next.time;
					// 우선순위 큐에 넣는다.
					pq.add(new Node(next.next, dist[next.next]));
				}
			}
		}
		
	}

}

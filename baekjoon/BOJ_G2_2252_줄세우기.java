package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 
 * 위상정렬 문제 더 풀어서 익숙해 져야 겠다.
 * 
 * <문제 요약>
 * 문제 정의
 * - 일부 학생들의 키를 비교한 결과를 가지고 학생을 줄세워라
 * 
 * 문제 유형
 * - 그래프 탐색
 * - 정렬
 * 
 * 제약 사항
 * 1 <= N <= 32,000
 * 1 <= M <= 100,000
 * 
 * <풀이 요약>
 * 위상정력을 잘 몰라서 알고리즘 공부하고 나중에 다시 풀어봐야 할것 같다.
 * 위상정렬 - 방향 그래프에 존재하는 각 정점들의 선행 순서를 위배하지 않으면서 모든 정점을 나열하는것!!!
 * 큐와 진입차수 배열을 가지고 한다.
 * 진입차수가 0인것을 큐에 넣고 큐에서 나올때 연결된 노드의 진입차수를 1 줄인다.
 * 모든 노드를 확인할때까지 반복한다.
 * 
 * 1. 입력을 받으며 인접리스트를 만든다. 이때 진입차수를 같이 구한다.
 * 2. 진입차수가 0인 노드를 큐에 넣고 반복한다.
 * 3. 큐에서 뺀 노드에 연결된 노드들의 진입차수를 1 감소한다.
 * 4. 2, 3 순서를 반복해서 노드를 모두 탐색한다.
 * 
 */

public class BOJ_G2_2252_줄세우기 {

	static int N, M;
	
	static ArrayList<ArrayList<Integer>> graph;
	static int[] cntOfLink;
	
	static StringBuilder answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		answer = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 인접리스트
		graph = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		cntOfLink = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			// A뒤에 B가 와야 한다.
			graph.get(A).add(B);
			// 진입차수를 증가시킨다.
			cntOfLink[B]++;
		}
		
		topologicalSort();
		
		System.out.println(answer.toString());
	}

	private static void topologicalSort() {
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			// 진입차수가 0인것 == 시작지점
			if(cntOfLink[i] == 0) {
				queue.add(i);
			}
		}
		
		// 노드의 개수만큼 반복한다.
		for(int i = 0; i < N; i++) {
			int curNode = queue.poll();
			answer.append(curNode + " ");
			
			// 현재 노드에 연결된 노드의 진입차수를 줄여준다.
			for(int nextNode : graph.get(curNode)) {
				cntOfLink[nextNode]--;
				
				// 진입차수가 0이 되었다면 큐에 넣는다.
				if(cntOfLink[nextNode] == 0) {
					queue.add(nextNode);
				}
				
			}
			
		}
		
	}

}

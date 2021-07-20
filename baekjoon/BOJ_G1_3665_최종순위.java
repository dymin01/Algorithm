package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * <문제 요약>
 * 문제 정의
 * - 올해 순위를 출력하라
 * 
 * 문제 유형
 * - 위상정렬
 * 
 * 제약 사항
 * 2 <= n <= 500
 * 1 <= ti <= n
 * 0 <= m <= 25000
 * 1 <= ai < bi <= n
 * 
 * <풀이 요약>
 * 위상정렬인것 같고... 여러개의 시작점이 있으면... impossible 인가...간선이 남았는데 종료된 경우...?
 * 인접행렬로 만들어 하려니까 좀 햇갈림
 * 
 * 1. 작년 팀 순서에 맞게 인접행렬을 만든다. 이때 1등이 2등앞에만 있는게 아니라 2 3 4 5 6 뒤에 모든 팀보다 앞에 있는것이다.
 * 1-1. 인접행렬을 만들면서 진입차수도 계산한다.
 * 2. 순위가 바뀐 팀의 개수만큼 인접행렬의 값과 진입차수를 바꿔준다.
 * 3. 위상정렬을 통해 답을 구한다.
 * 3-1. 큐에 진입차수가 0인것을 넣는다.
 * 3-2. 노드의 개수만큼 반복한다. 이때 큐의 개수가 2개이상이면 등수로 만들 수 없어 정답을 알 수 없고, 노드를 찾기전에 큐가 비면 모든 노드를 탐색하지 못해 정답을 구할 수 없다.
 * 3-3. 현재 노드에 연결된 노드와의 연결을 끊고 진입 차수를 줄인다.
 * 3-4. 진입차수가 0이 되면 큐에 넣는다.
 * 3-5. 3-2, 3-3, 3-4 과정을 반복한다.
 * 4. 결과에 맞춰 정답을 출력한다.
 * 
 * 
 */

public class BOJ_G1_3665_최종순위 {
	
	static StringBuilder answer;
	
	static int[][] graph;
	static int[] cntOfLink;
	static int n;
	
	static final int SUCCEESS = 0;
	static final int IMPOSSIBLE = 1;
	static final int NOT_DETERMINED = 2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		
		while(TC-- > 0) {
			// 팀의 개수
			n = Integer.parseInt(br.readLine());
			// 작년 팀순서를 통해 만든 인접행렬
			// 1이면 연결 0이면 연결안된것
			graph = new int[n+1][n+1];
			cntOfLink = new int[n+1];
			int[] team = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				team[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < n; i++) {
				for(int j = i+1; j < n; j++) {
					graph[team[i]][team[j]] = 1;
					cntOfLink[team[j]]++;
				}
			}
			
			int m = Integer.parseInt(br.readLine());
			// 순서가 바뀐만큼 인접행렬을 바꿔준다.
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(graph[a][b] == 1) {
					graph[a][b] = 0;
					graph[b][a] = 1;
					
					cntOfLink[a]++;
					cntOfLink[b]--;
					
				}else {
					graph[a][b] = 1;
					graph[b][a] = 0;
					
					cntOfLink[a]--;
					cntOfLink[b]++;
				}
			}
			
			answer = new StringBuilder();
			
			int flag = topologicalSort();
			
			if(flag == 0) {
				System.out.println(answer.toString());
			} else if( flag == 1) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println("?");
			}
			
		}

	}

	private static int topologicalSort() {
		int flag = SUCCEESS;
		Queue<Integer> queue = new LinkedList<>();
		
		// 진입차수가 0인것을 찾는다.
		for(int i = 1; i <= n; i++) {
			if(cntOfLink[i] == 0) {
				queue.add(i);
			}
		}
		
		// 노드의 개수만큼 반복한다.
		for(int i = 0; i < n; i++) {
			//큐에 들어간것이 2개 이상이면 답을 알 수 없다.
			if(queue.size() > 1) {
				return NOT_DETERMINED;
			}
			// 노드를 다 찾기도 전에 queue가 비면 노드를 확인한것이 아니기 때문에 정답을 정할 수 없다.
			else if (queue.isEmpty()) {
				return IMPOSSIBLE;
			}
			
			// 현재 노드
			int cur = queue.poll();
			// 이상없을 경우 출력한 답.
			answer.append(cur + " ");
			// 현재 노드에 연결된 모든 노드를 확인한다.
			for(int j = 1; j <= n; j++) {
				// 현재 노드에 연결되어 있으면
				if(graph[cur][j] == 1) {
					// 연결을 끊고
					graph[cur][j] = 0;
					// 진입차수를 줄인다.
					cntOfLink[j]--;

					// 진입차수가 0이 되면
					if(cntOfLink[j] == 0) {
						// 큐에 넣는다.
						queue.add(j);
					}
				}
			}
		}
		
		return flag;
	}

}

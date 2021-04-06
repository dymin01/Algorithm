package programmers;
/**
 * <문제 요약>
 * 구해야 하는 것: 몇개의 네트워크로 구성되어 있는지 구하라
 * 제약 사항: n은 200이하, [i][j]는 항상 1
 * 문제 유형: DFS/BFS
 * 요구 개념: DFS/BFS
 * 
 * 자기자신만 있는경우 1개의 네트워크다.
 * Ex) 1 0 0
 * 	   0 1 0
 *     0 0 1
 *  답 3
 * <풀이법 요약>
 * 1. map을 순환하면서 아직 확인하지 않았고, 연결되어 있는 컴퓨터일 경우 BFS를 실행한다.
 * 2. 큐를 만들어 BFS를 구현한다. 먼저 들어온 컴퓨터를 큐에 넣고, 확인했다고 체크한다.
 * 3. 현재 컴퓨터와 연결된 컴퓨터중 이미 확인하지 않은 컴퓨터 들을 큐에 넣고 체크한다.
 * 4. 큐가 empty일때까지 위 2, 3을 반복한다.
 * 5. BFS를 나오면 한개의 네트워크를 탐색한 것으로 answer에 1을 더해준다.
 */

import java.util.LinkedList;
import java.util.Queue;

public class PM_L3_네트워크 {

	public static void main(String[] args) {
		PM_L3_네트워크 sol = new PM_L3_네트워크();
		
		int n = 3;
		int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1} };
		
		System.out.println(sol.solution(n, computers));
	}

	static boolean[] check;
	
	public int solution(int n, int[][] computers) {
        int answer = 0;
        
        check = new boolean[n+1];
        
        for(int i = 0; i < n; i++) {
        	if(!check[i]) {
        		bfs(i, n, computers);
        		answer++;
        	}
        }
        
        return answer;
    }
	
	public void bfs(int cur, int n, int[][] computers) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(cur);
		check[cur] = true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int next = 0; next < n; next++) {
				if(!check[next] && computers[current][next] == 1) {
					queue.offer(next);
					check[next] = true;
				}
			}
		}
	}
	
}

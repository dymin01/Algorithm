package programmers;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <문제 요약>
 * 구해야 하는 것: 1번 노드에서 가장 먼 노드를 구하라 
 * 유형: 그래프 탐색
 * 
 * <풀이법 요약>
 * 1. 간선을 이용하여 인접행렬을 만든다.
 * 2. 큐를 이용하여 1번 간선부터 이동 가능한 노드를 BFS를 이용하여 탐색한다.
 * 3. 가장 긴 간선의 길이의 노드의 갯수를 구한다.
 * 
 */

public class PM_L3_가장먼노드 {

	public static void main(String[] args) {
		PM_L3_가장먼노드 sol = new PM_L3_가장먼노드();
		
		int n = 6;
		int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

		System.out.println(sol.solution(n, edge));
	}
	
	public int solution(int n, int[][] edge) {
        int answer = 0;
        
        // 간선의 길이를 저장하는 배열
        int[] dis = new int[n+1];
        
        // 방문 체크
        boolean[][] map = new boolean[n+1][n+1];
        
        // 간선을 인접행렬로 저장한다.
        for(int i = 0; i < edge.length; i++) {
        	map[edge[i][0]][edge[i][1]] = map[edge[i][1]][edge[i][0]] = true;
        }
        
        //BFS를 이용한 탐색
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        dis[1] = 1;
        int max = 0;
        while(!queue.isEmpty()) {
        	int cur = queue.poll();
        	
        	// 1을 제외한 노드 중 방문하지 않았고 연결되어있는 노드를 찾는다.
        	for(int i = 2; i <= n; i++) {
        		if(dis[i] != 0 || !map[cur][i]) continue;
        		dis[i] = dis[cur] + 1;
        		queue.add(i);
        		// 가장 긴 간선의 길이를 저장한다.
        		max = Math.max(max, dis[i]);
        	}
        	
        }
        
        // 간선의 길이가 가장 긴 노드의 갯수를 구한다.
        for(int a : dis) {
        	if(max == a) {
        		answer++;
        	}
        }
        
        return answer;
    }
}

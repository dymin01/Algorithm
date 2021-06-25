package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class PM_L3_경주로건설 {

	public static void main(String[] args) {

		PM_L3_경주로건설 sol = new PM_L3_경주로건설();
		
		/*int[][] board = {	{0,0,0},
							{0,0,0},
							{0,0,0}};*/
		
		int[][] board = {	{0,0,0,0,0,0,0,1},
							{0,0,0,0,0,0,0,0},
							{0,0,0,0,0,1,0,0},
							{0,0,0,0,1,0,0,0},
							{0,0,0,1,0,0,0,1},
							{0,0,1,0,0,0,1,0},
							{0,1,0,0,0,1,0,0},
							{1,0,0,0,0,0,0,0}};
		
		System.out.println(sol.solution(board));
		
	}

	
	public static int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        int N = board.length;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 0, 4});
        
        board[0][0] = 1;
        
        while(!queue.isEmpty()) {
        	int[] cur = queue.poll();
        	int r = cur[0];
        	int c = cur[1];
        	int cost = cur[2];
        	int dir = cur[3];
        	
        	if(r == N-1 && c == N-1) {
        		answer = Math.min(answer, cost);
        		continue;
        	}
        	
        	for(int d = 0; d < 4 ; d++) {
        		int nr = r + dr[d];
        		int nc = c + dc[d];
        		
        		if(nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 1) continue;
        		
       			int ncost = 0;
       			
       			if(d != dir) {
       				ncost = cost + 600;
       			}else {
       				ncost = cost + 100;
       			}
       			
       			if(board[nr][nc] == 0 || board[nr][nc] >= ncost) {
       				board[nr][nc] = ncost;
       				queue.add(new int[] {nr, nc, ncost, d});
       			}
        		
        	}
        	
        }
        
        return answer-500;
    }


}

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 이동 회수를 최소로 하여 정상에 오를 수 있는 이동 회수를 구하라
 * 
 * 문제 유형
 * - 그래프 탐색
 * 
 * 제약 사항
 * 1 <= n <= 50,000
 * 1 <= T <= 200,000
 * 
 * <풀이 요약>
 * 구글링으로 조금 도움을 받음. 방문배열을 사용하는 대신 좌표를 지운다.
 * 
 * 
 */

public class BOJ_G3_2412_암벽등반 {

	static class Rock{
		int x, y;

		public Rock(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static ArrayList<Integer>[] rocks;
	
	static int T;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		rocks = new ArrayList[200001];
		for(int i = 0; i < 200001; i++) {
			rocks[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			rocks[y].add(x);
		}
		
		for(int i = 0; i < 200001; i++) {
			Collections.sort(rocks[i]);
		}
		
		System.out.println(BFS());

	}

	private static int BFS() {
		Queue<Rock> queue = new LinkedList<>();
		queue.add(new Rock(0, 0));
		
		int move = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s = 0; s < size; s++) {
				Rock rock = queue.poll();
				int curX = rock.x;
				int curY = rock.y;
				
				// T를 넘은경우
				if(curY >= T) return move;
				
				for(int y = curY - 2; y <= curY + 2; y++) {
					if(y < 0 || y >= 200001) continue;
					for(int i = 0; i < rocks[y].size(); i++) {
						
						int x = rocks[y].get(i);
						
						if(curX + 2 < x) continue;
						else if(curX - 2 > x) continue;
						
						rocks[y].remove(i);
						queue.add(new Rock(x, y));
						i--;
					}
				}
				
			}
			move++;
		}
		
		return -1;
	}
	


}

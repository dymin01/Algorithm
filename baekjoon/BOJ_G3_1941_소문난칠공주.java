package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 소문난 칠공주를 결성할 수 있는 모든 경우의 수를 구하라
 * 
 * 문제 유형
 * - BFS/ DFS
 * 
 * 제약 사항
 * 1.이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
 * 2. 강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
 * 3. 화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
 * 4. 그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다. 따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다. 
 *  
 * <풀이 요약>
 * 큰 틀
 * S에서 시작해 Y의 갯수가 3명보다 많아지면 만들 수 없다고 판단하고 다른 경우의 수를 찾는다.
 * 즉 S에서 시작해서 Y가 나와도 지금의 Y의 개수가 4보다 작으면 구성할 수 있다.
 * ... 단순 그래프 탐색 문제가 아니었다... Y에서 시작해도 7명을 찾을 수 있는 경우가 있고, 같은 경우의 수가 나온다.
 * 25개의 자리에서 7개를 선택해서 S의 개수가 4개 이상인지, 다 인접해있는지 확인하는 과정이 필요할것같다.
 * 
 * 1. map을 입력 받는다.
 * 2. 25개의 자리중 7개를 선택한다.
 * 3. 7개를 선택했을때 S의 개수가 4개 이상이면 BFS를 실행한다.
 * 4. BFS탐색을 통해 인접해 있는지 확인하고 모두 인접해 있으면 정답의 갯수는 +1 한다.
 * 
 */

public class BOJ_G3_1941_소문난칠공주 {

	static int ans;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static char[][] map;
	static int[] pos;
	static boolean[] v;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[5][5];
		v = new boolean[25];
		pos = new int[25];
		ans = 0;
		
		for(int i = 0; i < 5; i++) {
			String str = br.readLine();
			for(int j = 0; j < 5; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		
		// 25개의 자리에서 7개의 자리를 선택하는 nCr
		nCr(0, 0);
		
		System.out.println(ans);
		
		
	}

	private static void nCr(int cnt, int start) {
		if( cnt == 7) {
			// 7개를 모두 선택한 경우
			int[][] chose = new int[5][5];
			int cntS = 0;
			int r = 0;
			int c = 0;
			for(int i = 0; i < 25; i++) {
				if(v[i]) {
					r = i / 5;
					c = i % 5;
					chose[r][c] = 1;
					
					if(map[r][c] == 'S') cntS++;
				}
			}
			
			if(cntS >= 4) {
				BFS(r, c, chose);
			}
			
			return;
			
		}
		
		for(int i = start; i < 25; i++) {
			if(v[i]) continue;
			
			v[i] = true;
			nCr(cnt+1, i+1);
			v[i] = false;
			
		}
		
	}

	private static void BFS(int sr, int sc, int[][] chose) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr, sc});
		
		boolean[][] vis = new boolean[5][5];
		vis[sr][sc] = true;
		
		int cnt = 1;
		while(!queue.isEmpty()) {
			
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || vis[nr][nc] || chose[nr][nc] == 0) continue;
				
				queue.add(new int[] {nr, nc});
				vis[nr][nc] = true;
				cnt++;
				
			}
			
		}
		
		if(cnt == 7)
			ans++;
		
	}



}

package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Solution_5656_벽돌깨기_re {

	static int N, W, H;
	static int[][] map;
	static int[][] sub;
	static int ans;
	
	static int blockCnt;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int[] list;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			list = new int[N];
			
			ans = Integer.MAX_VALUE;
			blockCnt = 0;
			//map 초기화
			map = new int[H][W];
			sub = new int[H][W];
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] != 0) blockCnt++;
				}
			}
			ans = blockCnt;
			DFS(0, 0);

			System.out.println("#" + t + " " + ans);
		}
	}
	
	static void DFS(int cnt, int sum) {
		// 구슬을 다 던진 경우
		if(cnt == N) {
			ans = Math.min(ans, blockCnt - sum);
			return;
		}
		
		for(int c = 0; c < W; c++) {
			int[][] temp = copy();
			
			int r = findTop(c);
			int count = Boom(r, c);
			if(blockCnt - sum != count) {
				DFS(cnt + 1, sum + count);
			}else {
				ans = 0;
				return;
			}
			map = temp;
			
		}
	}
	
	private static int[][] copy() {
		int[][] temp = new int[H][W];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}

	private static int Boom(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {r, c, map[r][c]});
		map[r][c] = 0;
		int count = 1;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			int dis = cur[2];
			for(int d = 0; d < 4; d++) {
				for(int i = 1; i < dis; i++) {
					int nr = cr + dr[d] * i;
					int nc = cc + dc[d] * i;
					if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
					if(map[nr][nc] == 0) continue;
					queue.offer(new int[] {nr, nc, map[nr][nc]});
					map[nr][nc] = 0;
					count++;
				}
			}
		}
		
		Stack<Integer> stack = new Stack<>();
		
		for(int j = 0; j < W; j++) {
			int i;
			for(i = 0; i < H; i++) {
				if(map[i][j] != 0) {
					stack.add(map[i][j]);
					map[i][j] = 0;
				}
			}
			
			i = H-1;
			while(!stack.isEmpty()) {
				map[i--][j] = stack.pop();
			}
		}
		
		return count;
	}

	private static int findTop(int c) {
		for(int i = 0; i < H; i++) {
			if(map[i][c] != 0) return i;
		}
		return 0;
	}

	
}

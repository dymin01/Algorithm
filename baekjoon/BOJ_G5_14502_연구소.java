package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_14502_연구소 {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int[][] map;
	static int[][] sub;
	static ArrayList<int[]> virus;
	
	static int N, M;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		sub = new int[N][M];
		ans = Integer.MIN_VALUE;
		virus = new ArrayList<>();
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sub[i][j] = map[i][j];
				if(map[i][j] == 2) {
					virus.add(new int[] {i,j});
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					copy();
					map[i][j] = 1;
					DFS(1);
					map[i][j] = 0;
				}
			}
		}
		
		System.out.println(ans);
		
	}
	
	static void BFS() {
		
		int[][] newMap = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		Queue<int[]> queue = new LinkedList<int[]>();
		for(int[] a : virus) {
			queue.add(a);
		}
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if(newMap[nr][nc] == 0) {
					newMap[nr][nc] = 2;
					queue.add(new int[] {nr, nc});
				}
			}
			
		}
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(newMap[i][j] == 0) cnt++;
			}
		}
		
		ans = Math.max(ans, cnt);
		
	}
	
	static void DFS(int cnt) {
		if(cnt == 3) {
			BFS();
			return;
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					DFS(cnt+1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	
	static void copy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sub[i][j];
			}
		}
	}
	
	

}

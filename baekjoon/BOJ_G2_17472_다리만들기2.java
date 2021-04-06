package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G2_17472_다리만들기2 {

	static int[][] map;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		// 지도 초기화
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) map[i][j] = -1;
			}
		}
		
		// 섬의 갯수
		int island = 1;
		// 섬에 이름 붙이기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == -1) {
					label(i, j, island++);
				}
			}
		}
		
		// 간선의 비용 구하기
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		for(int i = 1; i < island; i++) {
			for(int j = i+1; j < island; j++) {
				int cost = getBridge(i, j);
				if(cost != 0) {
					pq.add(new int[] {i, j, cost});
				}
			}
		}
		
		//makeset
		parent = new int[island];
		for(int i = 1; i < island; i++) {
			parent[i] = i;
		}
		
		int ans = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int y = find(cur[0]);
			int x = find(cur[1]);
			if(y != x) {
				union(cur[0], cur[1]);
				ans += cur[2];
			}
		}
		
		boolean flag = true;
		
		for(int i = 1; i < island; i++) {
			if(find(i) != 1) {
				flag = false;
				break;
			}
		}
		System.out.println(flag ? ans : -1);
	}
	

	private static void union(int i, int j) {
		int y = find(i);
		int x = find(j);
		
		if(y < x) parent[x] = y;
		else parent[y] = x;
		
	}


	private static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}



	private static int getBridge(int start, int end) {
		
		boolean[][][] visit = new boolean[N][M][4];
		Queue<int[]> queue = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != start) continue;
				for(int d = 0; d < 4; d++) {
					int r = i + dr[d];
					int c = j + dc[d];
					if(r < 0 || r >= N || c < 0 || c >= M) continue;
					if(map[r][c] != 0) continue;
					queue.add(new int[] {r, c, 0, d});
					visit[r][d][d] = true;
				}
			}
		}
		
		int ret = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int cnt = cur[2];
			int d = cur[3];
			
			if(map[r][d] == end) {
				if(cnt == 1) continue;
				ret = cnt;
				break;
			}
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
			if(visit[nr][nc][cnt]) continue;
			if(map[nr][nc] == 0 || map[nr][nc] == end) {
				visit[nr][nc][d] = true;
				queue.add(new int[] {nr, nc, cnt+1, d});
			}
		}
		
		return ret;
		
	}

	private static void label(int i, int j, int cnt) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {i, j});
		map[i][j] = cnt;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(map[nr][nc] == -1) {
					map[nr][nc] = cnt;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}
}

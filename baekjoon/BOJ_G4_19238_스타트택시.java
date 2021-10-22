package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_19238_스타트택시 {

	static int N, M, F;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int[][] map;
	static int[][] guestInfo;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					map[i][j] = -1;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int tr = Integer.parseInt(st.nextToken())-1;
		int tc = Integer.parseInt(st.nextToken())-1;
		guestInfo = new int[M+1][4]; 
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			guestInfo[i][0] = Integer.parseInt(st.nextToken())-1;
			guestInfo[i][1] = Integer.parseInt(st.nextToken())-1;
			guestInfo[i][2] = Integer.parseInt(st.nextToken())-1;
			guestInfo[i][3] = Integer.parseInt(st.nextToken())-1;
			map[guestInfo[i][0]][guestInfo[i][1]] = i;
		}
		int moveCnt = M;
		for(int i = 0; i < moveCnt; i++) {
			
			// 가장 가까운 사람 고르기
			int guest = picup(tr, tc);
			
			// 기름이 없거나 손님을 못태우면
			if(F <= 0 || guest == -1) {
				System.out.println(-1);
				return;
			}
			
			// 도착지점 이동하기
			int usedFuel = moveGoal(guest);
			F -= usedFuel;
			if(F < 0) {
				System.out.println(-1);
				return;
			}
			M--;
			F += (usedFuel * 2);
			
			tr = guestInfo[guest][2];
			tc = guestInfo[guest][3];
		}
		
		System.out.println(F);

	}

	private static int moveGoal(int guest) {
		// 도착지점
		int sr = guestInfo[guest][0];
		int sc = guestInfo[guest][1];
		int er = guestInfo[guest][2];
		int ec = guestInfo[guest][3];
	
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr, sc, 0});
		boolean[][] V = new boolean[N][N];
		V[sr][sc] = true;
		while(!queue.isEmpty()) {
			
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int dis = cur[2];
			
			// 도착지점에 도착했으면
			if(r == er && c == ec) {
				return dis;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위 확인
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				// 벽이거나 이미 방문한곳 못감
				if(map[nr][nc] == -1 || V[nr][nc]) continue;
				
				V[nr][nc] = true;
				queue.add(new int[] {nr, nc, dis+1});
				
			}
			
		}
		
		return 0;
		
		
	}

	private static int picup(int sr, int sc) {
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr, sc, 0});
		boolean[][] V = new boolean[N][N];
		V[sr][sc] = true;
		
		int cnt = 0;
		int minDis = 987654321;
		int minMan = 0;
		int minR = 0;
		int minC = 0;
		while(!queue.isEmpty()) {
			
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int dis = cur[2];
			// 손님이 있으면
			if(map[r][c] > 0) {
				cnt++;
				if(minDis > dis) {
					minDis = dis;
					minMan = map[r][c];
					minR = r;
					minC = c;
				}else if(minDis == dis) {
					
					if(minR > r) {
						minDis = dis;
						minMan = map[r][c];
						minR = r;
						minC = c;
					}else if(minR == r) {
						if(minC > c) {
							minDis = dis;
							minMan = map[r][c];
							minR = r;
							minC = c;
						}
					}
				}
				// 손님을 모두 확인해 봤으면
				if(cnt == M) {
					// 가장 짧은 손님을 찾는다.
					// 연료 감소
					F -= minDis;
					map[minR][minC] = 0;
					return minMan;
				}
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위 확인
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				// 벽이거나 이미 방문한곳 못감
				if(map[nr][nc] == -1 || V[nr][nc]) continue;
				
				V[nr][nc] = true;
				queue.add(new int[] {nr, nc, dis+1});
				
			}
			
		}
		
		return -1;
	}
	
	
}

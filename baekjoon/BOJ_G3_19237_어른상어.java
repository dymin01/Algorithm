package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * 
 * 문제 유형
 * 
 * 제약 사항
 * 
 * <풀이 요약>
 * 1의 번호를 가진 어른 상어는 가장 강력해서 나머지 모두를 쫓아낼 수 있다.
 * 
 * 맨 처음에는 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다
 * 
 * 1초마다 모든 상어가 동시에 상하좌우로 인접한 칸 중 하나로 이동하고, 자신의 냄새를 그 칸에 뿌린다. 냄새는 상어가 k번 이동하고 나면 사라진다.
 * 
 * 각 상어가 이동 방향을 결정할 때는, 먼저 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다. 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다
 * 
 * 이때 가능한 칸이 여러 개일 수 있는데, 그 경우에는 특정한 우선순위를 따른다
 * 
 */

public class BOJ_G3_19237_어른상어 {

	static int N, M, K;
	
	static class Shark{
		int r, c, d;

		public Shark(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
		
	}
	
	static int[][] whosSmell;
	static int[][] smellTime;
	static int[][][] direction;
	static Shark[] sharks;
	
	// 0위 1아 2왼 3오
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int aliveCnt;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		whosSmell = new int[N][N];
		smellTime = new int[N][N];
		direction = new int[M+1][4][4];
		
		sharks = new Shark[M+1];
		
		aliveCnt = M;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int shark = Integer.parseInt(st.nextToken());
				
				if(shark > 0) {
					whosSmell[i][j] = shark;
					sharks[shark] = new Shark(i, j, 0);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= M; i++) {
			sharks[i].d = Integer.parseInt(st.nextToken()) - 1;
		}
		
		for(int i = 1; i <= M; i++) {
			for(int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int d = 0; d < 4; d++) {
					// i번째 상어의 J방향의 우선순위 d
					direction[i][j][d] = Integer.parseInt(st.nextToken())-1;
				}
			}
		}
		
		int answer = sol();
		
		System.out.println(answer);
		
	}

	private static int sol() {
		int time = 0;
		
		while(time < 1000) {
			time++;
			// 상어가 있는 자리 냄세 뿌리기 
			for(int i = 1; i <= M; i++) {
				if(sharks[i] != null) {
					whosSmell[sharks[i].r][sharks[i].c] = i;
					smellTime[sharks[i].r][sharks[i].c] = K;
				}
			}
			
			print();
			
			
			int[][] map = new int[N][N];
			// 상어 움직이기
			for(int i = 1; i <= M; i++) {
				if(sharks[i] != null) {
					// i번째 상어 움직이기.
					moveShark(map, i);
					
				}
			}
			
			// 냄새 1씩 줄이기
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(smellTime[i][j] > 0) {
						smellTime[i][j]--;
					}
					if(smellTime[i][j] == 0) {
						whosSmell[i][j] = 0;
					}
				}
			}
			// 1번 상어 혼자 살아있으면
			if(aliveCnt == 1 && sharks[1] != null) {
				return time;
			}
			
		}
		return -1;
		
	}

	private static void moveShark(int[][] map, int i) {
		
		int r = sharks[i].r;
		int c = sharks[i].c;
		int d = sharks[i].d;
		
		boolean isFind = false;
		int nr = 0;
		int nc = 0;
		int nd = 0;
		for(int j = 0; j < 4; j++) {
			nd = direction[i][d][j];
			nr = r + dr[nd];
			nc = c + dc[nd];
			
			// 범위 체크
			if(!isbound(nr, nc)) continue;
			
			// 냄새가 있으면
			if(smellTime[nr][nc] != 0) continue;
			
			// 이동가능
			isFind = true;
			break;
		}
		
		// 빈칸이 없으면
		if(!isFind) {
			
			int md = 0;
			for(int j = 0; j < 4; j++) {
				md = direction[i][d][j];
				// 자기 냄새가 있는곳으로 이동
				nr = r + dr[md];
				nc = c + dc[md];
				if(!isbound(nr, nc)) continue;
				// 자기와 같은 냄새면
				if(whosSmell[nr][nc] == i) {
					break;
				}
			}
			
			// 이미 큰 상어가 왔다면
			if(map[nr][nc] != 0) {
				sharks[i] = null;
				aliveCnt--;
				return;
			}
			
			// 상어 이동
			map[nr][nc] = i;
			sharks[i].r = nr;
			sharks[i].c = nc;
			sharks[i].d = md;
			
		}
		// 빈칸이 있으면
		else {
			// 이미 상어가 있으면 자신보다 큰 상어(숫자는 작은). 격자 밖으로 이동
			if(map[nr][nc] != 0) {
				sharks[i] = null;
				aliveCnt--;
				return;
			}
			// 일반 이동
			map[nr][nc] = i;
			sharks[i].r = nr;
			sharks[i].c = nc;
			sharks[i].d = nd;
		}
		
	}

	private static boolean isbound(int nr, int nc) {
		if(nr < 0 || nr >= N || nc < 0 || nc >= N) return false;
		return true;
	}

	
	public static void print() {
		System.out.println();
		System.out.println("냄새 시간");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(smellTime[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("어떤 상어의 냄새냐");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(whosSmell[i][j] + " ");
			}
			System.out.println();
		}
		System.out.print("살아있는 상어는 : ");
		for(int i = 1; i <= M; i++) {
			if(sharks[i] != null) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
		System.out.println("상어의 방향");
		for(int i = 1; i <= M; i++) {
			if(sharks[i] != null) {
				System.out.println(i + ": " + sharks[i].d);
			}
		}
		System.out.println();
	}



}

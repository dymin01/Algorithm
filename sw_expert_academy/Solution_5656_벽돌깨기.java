package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : N개의 구슬을 던져 벽돌을 최소한으로 남게 만들었을때 벽돌의 갯수를 구하라
 * 요구 개념/문제 유형 : 시뮬레이션, 순열 등등...
 * 
 * <풀이법 요약 >
 * 1. nPr을 이용하여 구슬을 던질 곳을 정한다.
 * 2. 구슬을 던지고 Boom 함수를 이용하여 벽돌을 터트린다.
 * 3. Move 함수를 이용하여 벽돌을 내려준다.
 * 4. 남은 벽돌의 갯수를 구해 최솟값을 찾는다...
 * 
 * 말로하면 정말 구현이 쉬워보인다... 테스트 케이스는 답이 나왔지만....왜 RunTime Err이 나오는걸까...
 * 다시 푼다..
 * 
 * 
 */

public class Solution_5656_벽돌깨기 {

	static int N, W, H;
	static int[][] map;
	static int[][] sub;
	static int ans;
	
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
			
			//map 초기화
			map = new int[H][W];
			sub = new int[H][W];
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					sub[i][j] = map[i][j];
				}
			}
			
			nPr(0);

			System.out.println("#" + t + " " + ans);
			
		}
		
		
	}
	
	static void nPr(int cnt) {
		if(cnt == N) {
			
			// 구슬의 갯수만큼 boom
			for(int i = 0; i < N; i++) {
				int r = findtop(list[i]);
				if(r == H) continue;
				Boom(r, list[i]);
				Move();
			}
//			System.out.println();
//			for(int i = 0; i < H; i++) {
//				for(int j = 0; j < W; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			// count block;
			int res = 0;
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(map[i][j] != 0) res++;
				}
			}
			
			ans = Math.min(ans, res);
			
			for(int i = 0; i < H; i++) {
				map[i] = Arrays.copyOf(sub[i], sub.length);
			}
			
			return;
		}
		
		for(int c = 0; c < W; c++) {
			int r = findtop(c);
			if(r == H) continue;
			list[cnt] = c;
			nPr(cnt+1);
		}
	}

	private static void Move() {
		
		for(int j = 0 ; j < W; j++) {
			for(int i = 1; i < H; i++) {
				if(i == 0)continue;
				if(map[i][j] == 0 && map[i-1][j] != 0) {
					map[i][j] = map[i-1][j];
					map[i-1][j] = 0;
					i-=2;
				}
			}
		}
		
	}

	private static void Boom(int r, int c) {
		if(map[r][c] == 1) {
			map[r][c] = 0;
			return;
		}
		// 2 이상 (사방검색);
		int num = map[r][c];
		map[r][c] = 0;
		for(int d = 0; d < 4; d++) {
			int nr = r;
			int nc = c;
			for(int i = 1; i < num; i++) {
				nr += dr[d];
				nc += dc[d];
				if(nr < 0 || nr >= H || nc < 0 || nc >= W) break;
				if(map[nr][nc] > 1) {
					Boom(nr, nc);
				}
				else {
					map[nr][nc] = 0;
				}
			}
		}
	}

	private static int findtop(int c) {
		for(int r = 0; r < H; r++) {
			if(map[r][c] != 0) return r;
		}
		return H;
	}
	
	
}

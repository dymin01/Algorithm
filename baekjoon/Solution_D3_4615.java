package baekjoon;
import java.util.Scanner;
/***
 * <문제 요약> 
 * 구해야 하는 것 : 케이스가 끝난 후 흑돌과 백돌의 갯수
 * 문제 핵심 요약 : 구현
 * <풀이법 요약> 
 * 1. 오셀로의 기본 보드를 만든다
 * 2. 돌을 놓고 바꿀 수 있는지 확인한다.
 * 3. 4방을 탐색해서 바꿀 수 있는 돌들을 바꿔준다.
 * 4. 2, 3을 반복한다.
 * 5. 오셀로의 돌의 갯수를 구한다.
 */

public class Solution_D3_4615 {
	
	static int[][] map;
	static int N;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			
			N = sc.nextInt();
			int M = sc.nextInt();
			
			// 오셀로 기본 보드
			map = new int[N][N];
			map[N/2-1][N/2-1] = 2;
			map[N/2-1][N/2] = 1;
			map[N/2][N/2-1] = 1;
			map[N/2][N/2] = 2;
			
			for(int m = 0; m < M; m++) {
				int R = sc.nextInt()-1;
				int C = sc.nextInt()-1;
				int color = sc.nextInt();
				put(R, C, color);
			}
			
			int b = 0;
			int w = 0;
			
			// 백돌과 흑돌의 갯수 구하기
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 1) {
						b++;
					}else if(map[i][j] == 2) {
						w++;
					}
				}
			}
			
			System.out.println("#" + t + " " + b + " " + w);
			
		}
	}

	private static void put(int r, int c, int color) {
		map[r][c] = color;
		for(int d = 0; d < 8; d++) {
			// 돌을 놓을 수 있으면 돌을 놓는다.
			if(canChange(r, c, color, d)) {
				change(r, c, color, d);
			}
		}
		
	}

	private static void change(int r, int c, int color, int d) {
		int nr = 0;
		int nc = 0;
		for(int i = 0; i < N-1; i++) {
			nr = r + dr[d];
			nc = c + dc[d];
			// 같은 색의 돌을 만나면 정지
			if(map[nr][nc] == color) {
				return;
			}else {
				// 다른색의 돌을 만나면 돌의 색을 바꾼다.
				map[nr][nc] = color;
				r = nr;
				c = nc;
			}
		}
	}

	private static boolean canChange(int r, int c, int color, int d) {
		int nr = 0;
		int nc = 0;
		// 사방 탐색
		for(int i = 0; i < N-1; i++) {
			nr = r + dr[d];
			nc = c + dc[d];
			// 경계안에 있고
			if(check(nr, nc)) {
				// 다음 위치에 돌이 같은색이면 종료
				if(map[nr][nc] == color) {
					return true;
				}
				// 오셀로 판이 비어있으면 종료
				else if(map[nr][nc] == 0) {
					return false;
				}
				// 다음 위치의 돌이 다른색이면 그 다음으로 이동
				else {
					r = nr;
					c = nc;
				}
			}
			// 경계까지 갔을경우 종료
			else {
				return false;
			}
		}
		return false;
	}
	
	public static boolean check(int r, int c) {
		return(r >= 0 && r < N && c >= 0 && c < N);
	}

}

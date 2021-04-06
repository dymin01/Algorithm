package jungol;
import java.util.Scanner;
/***
 * <문제 요약> 
 * 구해야 하는 것 : 달팽이사각형
 * 문제 핵심 요약 : 구현
 * <풀이법 요약> 
 * 1. N*N개 만큼 돌린다.
 * 2. 숫자를 만나거나, 경계를 넘어가면 방향을 돌려준다. 방향은 오른쪽, 아래, 왼쪽, 위 순서로 돌린다.
 * 3. 1, 2 반복한다.
 */

public class JUNGOL_1707 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		//오 아 왼 위
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		int[][] map = new int[N][N];
		
		int r = 0;
		int c = 0;
		int d = 0;
		int nr = 0;
		int nc = 0;
		for(int i = 1; i <= N*N; i++) {
			map[r][c] = i;
			nr = r + dr[d];
			nc = c + dc[d];
			if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 0) {
				r = nr;
				c = nc;
			}else {
				d = (d+1)%4;
				r = r + dr[d];
				c = c + dc[d];
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}

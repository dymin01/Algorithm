package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약> 문제 정의
 * 
 * 문제 유형
 * 
 * 제약 사항
 * 
 * <풀이 요약>
 * 
 * 1. 뱜은 몸길이를 늘려 모라룰 다음칸에 위치시킨다.
 * 2. 이동한 칸에 사과가 있다면, 그 칸에 있더 ㄴ사과가 없어지고 꼬리는 움직이지 않는다.
 * 3. 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉 몸길이는 변하지 않는다.
 * 
 */

public class BOJ_G5_3190_뱀 {
	// 우 하 좌 상
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 보드의 크기
		int N = Integer.parseInt(br.readLine());
		// 사과가 있는 보드
		int[][] board = new int[N + 1][N + 1];
		// 뱀이 있는 보드
		boolean[][] snake = new boolean[N + 1][N + 1];
		snake[1][1] = true;
		// 뱀의 꼬리 위치를 확인하는 큐
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 1, 1 });

		// 사과의 개수
		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[r][c] = 1;

		}
		// 뱀의 방향 변환 횟수
		int L = Integer.parseInt(br.readLine());
		int[][] list = new int[L][2];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			// -1 : 왼쪽, 1 : 오른쪽
			int d = -1;
			if (dir.equals("D")) {
				d = 1;
			}

			list[i][0] = time;
			list[i][1] = d;
		}

		// 방향
		int d = 0;
		// r c
		int r = 1;
		int c = 1;
		// 걸린시간
		int ans = 0;
		// 턴
		int turn = 0;
		
		while (true) {
			// move
			ans++;
			
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 벽을 만나거나
			// 뱀의 몸통을 만나면 끝
			if (nr <= 0 || nr > N || nc <= 0 || nc > N || snake[nr][nc]) {
				break;
			}

			// 머리가 한칸 앞으로 이동
			queue.add(new int[] { nr, nc });
			snake[nr][nc] = true;

			// 사과면 꼬리 이동안함.
			// 사과가 아니면 꼬리 앞으로 한칸 이동
			if (board[nr][nc] != 1) {
				int[] tail = queue.poll();
				snake[tail[0]][tail[1]] = false;
			}else{
				board[nr][nc] = 0;
			}

			r = nr;
			c = nc;
			
			if(turn < L) {
				// 방향 전환 해야 하는 시간이면
				if(ans == list[turn][0]) {
					d += list[turn][1];
					if(d < 0) d = 3;
					d %= 4;
					turn++;
				}
			}
		}

		System.out.println(ans);

	}

}

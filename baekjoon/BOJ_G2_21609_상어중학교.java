package baekjoon;
/***
 * <미완>
 * 예제만 맞고 답이 틀립 뭐가 문제일까???
 * 
 * <문제 요약>
 * 문제 정의 : 오토 플레이를 진행 후 획득한 점수의 합
 * 문제 유형 : BFS, 구현
 * 제약 사항 : -1은 벽으로 중력에 작용하지 않는다.
 * [오토플레이 기능]
 * 1. 크기가 가장 큰 블록 그룹을 찾는다.
 * 그러한 블록 그룹이 여러 개라면 포함된 무지개 블록의 수가 가장 많은 블록 그룹, 그러한 블록도 여러개라면 기준 블록의 행이 가장 큰 것을, 그 것도 여러개이면 열이 가장 큰 것을 찾는다.
 * 2. 1에서 찾은 블록 그룹의 모든 블록을 제거한다. 블록 그룹에 포함된 블록의 수를 B라고 했을 때, B2점을 획득한다.
 * 3. 격자에 중력이 작용한다.
 * 4. 격자가 90도 반시계 방향으로 회전한다.
 * 5. 다시 격자에 중력이 작용한다.
 * 
 * <풀이 요약>
 * 1. 가장 큰 블록 그룹을 BFS를 이용하여 찾는다.
 * 2. 오토 플레이 제한사항을 따라 구현한다.
 * 
 * <실수>
 * 1번 조건에서 무지개블록 갯수, 행과 열이 가장 큰것으로 해야함....
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_G2_21609_상어중학교 {

	// 4방 탐색
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int[][] map;
	static boolean[][] v;

	static int N, M;
	static int tot;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 한변의 크기
		N = Integer.parseInt(st.nextToken());
		// 색상의 개수
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		tot = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		v = new boolean[N][N];
		// 1
		while (go()) {
//			System.out.println();
//			print();
			v = new boolean[N][N];
		}

		System.out.println(tot);

	}

	// 가장 큰 블록 그룹을 찾는다.
	public static boolean go() {

		int maxR = 0;
		int maxC = 0;
		int max = 0;
		int maxRainbow = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 1;
				int rainbow = 0;
				
				// 방문하지 않고 0이 아닌곳
				if (!v[i][j] && map[i][j] > 0) {
					
					boolean[][] check = new boolean[N][N];

					Queue<int[]> queue = new LinkedList<>();
					queue.add(new int[] { i, j });
					v[i][j] = true;
					while (!queue.isEmpty()) {
						int[] cur = queue.poll();
						int r = cur[0];
						int c = cur[1];

						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];

							// 범위를 넘어가거나, 방문했거나, 다른 숫자면 pass
							if (nr < 0 || nr >= N || nc < 0 || nc >= N)
								continue;
							if (check[nr][nc])
		                        continue;
							// 같은 색이거나 무지개색 일경우만 카운트
							if (map[nr][nc] == map[i][j] || map[nr][nc] == 0) {

								cnt++;
								v[nr][nc] = true;
								check[nr][nc] = true;
								queue.add(new int[] { nr, nc });
								if(map[nr][nc] == 0) {
									rainbow++;
									v[nr][nc] = false;
								}
							}

						}

					}
					
				}
				
				// 그룹의 크기 비교
				if(max < cnt) {
					max = cnt;
					maxRainbow = rainbow;
					maxR = i;
					maxC = j;
				}else if(max == cnt) {
					// 무지개블록 갯수 비교
					if(maxRainbow < rainbow) {
						max = cnt;
						maxRainbow = rainbow;
						maxR = i;
						maxC = j;
					}else if(maxRainbow == rainbow) {
						// 행 비교
						if(maxR < i) {
							max = cnt;
							maxRainbow = rainbow;
							maxR = i;
							maxC = j;
						}else if(maxR == i) {
							// 열 비교
							if(maxC < j) {
								max = cnt;
								maxRainbow = rainbow;
								maxR = i;
								maxC = j;
							}
						}
					}
				}
				
				
			}
		}

		if (max < 2) {
			return false;
		}

		// 가장 큰 블록 그룹을 지운다.
		// 2
		remove(maxR, maxC);
		// 3 중력 작용
		gravity();
		// 4 반시계 90도 회전
		turn();
		// 5 다시 중력 작용
		gravity();

		return true;

	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%3d", map[i][j]);
			}
			System.out.println();
		}
	}

	// 가장 큰 블록 그룹을 지운다. + 점수를 추가한다.
	public static void remove(int sr, int sc) {

		int color = map[sr][sc];

		int cnt = 1;
		map[sr][sc] = -2;

		// 지울때 -2로 채워넣는다.
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { sr, sc });
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				// 범위 넘어가면 지운다.
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;

				// 같은 색이거나 0이면 지우고 점수 ++;
				if (map[nr][nc] == color || map[nr][nc] == 0) {
					cnt++;
					map[nr][nc] = -2;
					queue.add(new int[] { nr, nc });
				}

			}
		}

		tot += (int) Math.pow(cnt, 2);

	}

	// 중력 작용
	public static void gravity() {

		Stack<Integer> stack = new Stack<>();

		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				if (map[i][j] == -2)
					continue;

				if (map[i][j] == -1) {
					int idx = i - 1;
					while (!stack.isEmpty()) {
						map[idx--][j] = stack.pop();
					}
					continue;
				}

				stack.add(map[i][j]);
				map[i][j] = -2;

			}

			int idx = N - 1;
			while (!stack.isEmpty()) {
				map[idx--][j] = stack.pop();
			}
		}

	}

	// 반시계 90도 회전
	public static void turn() {

		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			temp[i] = Arrays.copyOf(map[i], N);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = temp[j][N - 1 - i];
			}
		}

	}

}

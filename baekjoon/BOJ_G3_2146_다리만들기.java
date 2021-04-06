package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약> 구해야 하는 것: 섬을 연결하는 다리 중 가장 짧은 다리. 제약 사항: 지도의 크기는 100, 섬은 여러개가 있음 문제
 * 유형: BFS 요구 개념: BFS
 * 
 * <풀이법 요약>
 * 1. BFS를 통해 섬에 번호를 입력한다.
 * 2. 섬에서 4방향 중 다리를 만들 수 있는 바다가 있으면 다리를 만든다.
 * 3. 가장 짧게 만들어진 다리의 길이를 출력한다.
 * 
 * 왜 틀렸을까.....
 */

public class BOJ_G3_2146_다리만들기 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static int[][] map, distance, number;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int num; // 넘버링을 위한 변수
	static Queue<Point> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		distance = new int[N][N];
		number = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬의 개수
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && number[i][j] == 0) { // 섬이고 넘버링되지 않았다면
					numbering(i, j); // 각각의 섬 넘버링
				}
			}
		}

		checkDistance();

		int answer = Integer.MAX_VALUE; // max값 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++) { // 인접 위치를 확인하면서
					int x = i + dx[k];
					int y = j + dy[k];
					if (x < 0 || x >= N || y < 0 || y >= N || number[i][j] == number[x][y]) { // 각 칸과 인접한 칸의 그룹 번호가 다르면 다리 만들기 가능!
						continue;
					}
					answer = Math.min(answer, distance[i][j] + distance[x][y]); // 최솟값 저장
				}
			}
		}

		System.out.println(answer);

	}

	// 각 섬 넘버링
	private static void numbering(int x, int y) {
		q = new LinkedList<>();
		q.offer(new Point(x, y));
		number[x][y] = ++num;

		while (!q.isEmpty()) {
			Point point = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = point.x + dx[i];
				int ny = point.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != 1 || number[nx][ny] != 0) {
					continue;
				}
				number[nx][ny] = num; // 해당 위치에 섬의 넘버를 저장해준다
				q.offer(new Point(nx, ny));
			}
		}
	}

	// 다른 섬까지의 거리 측정
	private static void checkDistance() {
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				distance[i][j] = -1; // 바다 -1 저장
				if (map[i][j] == 1) { // 육지에 해당하는 곳
					distance[i][j] = 0; // distance 0으로 변경
					q.offer(new Point(i, j)); // queue에 해당 위치 저장
				}
			}
		}

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || distance[nx][ny] != -1) { // -1인 곳만 체크
					continue;
				}
				distance[nx][ny] = distance[p.x][p.y] + 1; // 이전 거리 + 1 (한칸 이동했기 때문에)
				number[nx][ny] = number[p.x][p.y]; // 다리 체크를 위하여 섬의 번호도 플러드 필
				q.offer(new Point(nx, ny));
			}
		}
	}
}

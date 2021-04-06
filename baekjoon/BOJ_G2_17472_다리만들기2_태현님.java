package baekjoon;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 모든 섬을 연결하는 다리 길이의 최솟값.
 * 요구 개념/문제 유형 : BFS(섬 넘버링), 시뮬레이션(다리 연결), 크루스칼 알고리즘(다리의 최소 길이) 
 * <풀이법>
 * 1. 지도의 좌상단부터 우하단으로 진행하며 섬을 만날때마다 넘버링을 한다.
 * -> 0이 아닌 좌표를 찾는다.
 * -> 해당 좌표에서 BFS를 진행하여 섬을 넘버링한다.
 * -> 넘버링을 위하여, 해당 섬을 넘버링한적이 있는지 판단할 방문 배열을 만든다.
 * 2. 각의 섬의 각 모든 좌표에대하여 상하좌우로 다리를 뻗어 시뮬레이션을 진행한다(다리가 섬이랑 연결 되는지).
 * -> 만일, 다른 섬과 연결이 된다면 해당 현재섬, 해당섬, 다리의 길이를 저장한다.
 * -> 다리의 길이는 2이상이어야 하고, 각 끝 지점에 섬이 위치하는 형태여야 한다는 조건을 유의하여 시뮬레이션.
 * 3. 저장된 정보를 바탕으로 그래프를 만든다.
 * 4. 그래프에서 크루스칼 알고리즘을 진행하고, 최소 길이를 출력한다.
 * <주의점>
 * 크루스칼 알고리즘 정확히 이해하자!!
 */

public class BOJ_G2_17472_다리만들기2_태현님 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int number; // 넘버링 해줄 변수.
	// 간선, 비용을 저장할 우선순위 큐
	static PriorityQueue<Edge> bridge;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	// 최소 거리
	static int[] set;
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 초기화
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];
		number = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 섬 넘버링
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 현재 지역을 방문한 적이 없으며(넘버링 한적이 없고), 해당 지역이 섬(=1)이라면 bfs를 진행하며 섬 넘버링.
				if (!visited[i][j] && map[i][j] != 0) {
					bfs(i, j);
				}
			}
		}

		// 초기화
		bridge = new PriorityQueue<Edge>();
		// 다리 연결 시뮬레이션(0이 아닌 좌표의 상하좌우로 다리를 연결한다).
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					simulation(i, j);
				}
			}
		}

		// 크루스칼 알고리즘 진행
		ans = 0;
		set = new int[number - 1];
		for (int i = 0; i < number - 1; i++) {
			set[i] = i;
		}
		int iter = bridge.size();
		for (int i = 0; i < iter; i++) {
			Edge cur = bridge.poll();
			if (!find(set, cur.from - 1, cur.to - 1)) {
				ans += cur.dis;
				unionParent(set, cur.from - 1, cur.to - 1);
			}
		}

		// 모든 다리의 연결성 확인
		int cur = getParent(set, 0);
		for (int i = 1; i < number - 1; i++) {
			if (cur != getParent(set, i)) {
				System.out.println(-1);
				sc.close();
				return;
			}
		}
		System.out.println(ans);
		sc.close();
	}

	static int getParent(int set[], int x) {
		if (set[x] == x)
			return x;
		return set[x] = getParent(set, set[x]);
	}

	static void unionParent(int set[], int a, int b) {
		a = getParent(set, a);
		b = getParent(set, b);
		if (a < b) {
			set[b] = a;
		} else {
			set[a] = b;
		}
	}

	static boolean find(int set[], int a, int b) {
		a = getParent(set, a);
		b = getParent(set, b);
		if (a == b) {
			return true;
		} else {
			return false;
		}
	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int dis;

		public Edge(int from, int to, int dis) {
			this.from = from;
			this.to = to;
			this.dis = dis;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dis, o.dis);
		}
	}

	// 다리 연결 시뮬레이션
	private static void simulation(int row, int col) {
		// 현재 섬의 번호
		int now = map[row][col];

		// 해당 좌표의 상하 좌우 끝까지 탐색한다.
		for (int i = 0; i < 4; i++) {
			int nr = row;
			int nc = col;
			// 범위를 벗어날 때 까지 다리 연결 시뮬레이션
			int cnt = 0; // 다리 길이
			while (true) {
				nr += dr[i];
				nc += dc[i];
				// 범위 벗어나면 종료
				if (!checkBoundary(nr, nc)) {
					break;
				}
				// 만일, 같은 섬을 만나면 종료
				if (map[nr][nc] == now) {
					break;
				}
				// 만일, 다른 섬을 만나면 다리 길이를 체크하고 다리 정보 저장(시작 섬, 끝 섬, 다리 길이).
				if (map[nr][nc] != 0 && map[nr][nc] != now) {
					if (cnt < 2) {
						break;
					}
					bridge.offer(new Edge(now, map[nr][nc], cnt));
					System.out.println(bridge);
					break;
				}
				// 여기까지 오면 다리를 건설하고 있는 것이므로 cnt + 1.
				cnt++;
			}
		}

	}

	private static void bfs(int row, int col) {
		Queue<int[]> q = new LinkedList<int[]>();
		// 넘버링을 시작할 출발 섬을 큐에 넣고, 해당 지역 방문 체크
		q.offer(new int[] { row, col });
		visited[row][col] = true;
		map[row][col] = number;

		// bfs를 진행하여 넘버링
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				// 범위, 방문 여부, 주변 대상이 1인지 체크
				if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					// 맞다면, 우선 방문 체크
					visited[nr][nc] = true;
					// 해당 맵을 넘버링하고 다음 bfs대상에 넣기
					map[nr][nc] = number;
					q.offer(new int[] { nr, nc });
				}
			}
		}
		// 다음 넘버링을위해 +1.
		number++;
	}

	private static boolean checkBoundary(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
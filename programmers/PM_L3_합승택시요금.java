package programmers;

import java.util.Arrays;

/**
 * <문제 요약>
 * 구해야 하는 것: 가장 적은 택시요금을 구하라
 * 제약 사항: 길이 연결되어있지 않은 정점이 있다.
 * 문제 유형: 플로이드와샬, 그래프탐색
 * 요구 개념: 플로이드와샬, 그래프탐색
 * 
 * <풀이법 요약>
 * 1. 플로이드 와샬을 사용하여 모든 구간의 최단거리를 구한다.
 * 2. 합승의 위치를 바꿔가며 s -> 합승 + 합승 -> A + 합승 -> B의 최소값을 구한다.
 * 
 */

public class PM_L3_합승택시요금 {

	public static void main(String[] args) {

		PM_L3_합승택시요금 sol = new PM_L3_합승택시요금();

		int n = 7;
		int s = 3;
		int a = 4;
		int b = 1;
		int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};

		System.out.println(sol.solution(n, s, a, b, fares));
	}

	static final int INF = 100000000;

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = 0;

		int[][] map = new int[n + 1][n + 1];

		for (int i = 0; i < n + 1; i++) {
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}

		// 인접행렬을 구한다. 이때 연결되지 않은곳은 INF로 초기화 하고, 자기 자신은 0으로 초기화 한다.
		for (int i = 0; i < fares.length; i++) {
			int from = fares[i][0];
			int to = fares[i][1];
			int cost = fares[i][2];
			map[from][to] = cost;
			map[to][from] = cost;
		}
		
		// 플로이드 와샬
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int k = 1; k <= n; k++) {
			min = Math.min(min, map[s][k] + map[k][a] + map[k][b]);
		}
		
		answer = min;

		return answer;

	}

}

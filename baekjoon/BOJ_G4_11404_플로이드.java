package baekjoon;
/**
 * <문제 요약>
 * 구해야 하는 것: 플로이드 i에서 j 까지 도착하는 최소 비용
 * 제약 사항: 
 * 문제 유형: 플로이드-와샬
 * 요구 개념: 플로이드-와샬
 * 
 * 플로이드-와샬 구현 문제이다.
 * 
 * <풀이법 요약>
 * 1. map을 초기화 한다. 이때 i == j 는 0으로 초기화 한다.
 * 2. 기본 정보를 입력받는다. 만약 입력된 값보다 작은 값이 추가로 들어오면 작은값을 넣어준다.
 * 3. 3중 포문을 이용하여 중간에 k도시를 거쳐갔을때  그냥 가는 비용과 k를 거쳐갔을때 비용을 비교해 map을 update한다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_11404_플로이드 {

	static int N, M;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N][N];

		//map을 초기화 한다. 이때 i == j 인 위치는 0으로 초기화 한다.
		for(int i = 0; i < map.length; i++) {
			Arrays.fill(map[i], 987654321);
			map[i][i] = 0;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int dis = Integer.parseInt(st.nextToken());
			// 같은 간선이 2개 들어오는 경우가 있다. 그럴경우 비용이 더 작은 것으로 초기화 한다.
			if(map[start][end] < dis) continue;
			map[start][end] = dis;
		}
		
		// k 는 중간에 거쳐가는 도시
		for(int k = 0; k < N; k++) {
			// i 는 시작 도시
			for(int i = 0; i < N; i++) {
				// j 는 도착 도시
				for(int j = 0; j < N; j++) {
					// i에서 j 로 가는 비용이 i -> k + k -> j 로 가는 비용과 비교해서 더 작은 비용으로 바꿔준다.
					if(map[i][k] + map[k][j] < map[i][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 987654321) System.out.print(0 + " ");
				else System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}

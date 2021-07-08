package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 친구들의 이동 거리의 총합이 최소가 되도록 하는 모임 장소를 찾아라
 * 
 * 문제 유형
 * - 그래프 탐색
 * - 다익스트라...?
 * - 플로이드와샬
 * 
 * 제약 사항
 * - 2 <= N <= 100
 * - N-1 <= M <= N(N-1)/2
 * - c는 1이상 1,000이하의 자연수
 * - 1 <= K <= N
 * 
 * <풀이 요약>
 * 다익스트라고 생각했는데 만약 K가 N이면 전체 를 다 확인해야 해서.. 다익스트라 계산을 N번 해야한다고 생각해서 어차피 다 봐야하기 때문에 플로이드와샬로 했음.
 * 
 * 1. 비밀통로를 입력받는 map[][]변수를 만들고 INF로 초기화 한 후 입력받는다. (자기자신으로 가는것은 0으로 초기화 예) i == j 일 경우)
 * 2. 플로이드 와샬을 구한다.
 * 	2-1. i -> j로 갈때 k를 경유하는것과 그냥 가는것 중 짧은 것으로 값을 바꾼다.
 * 3. 각 방에서 학생이 있는 방까지 거리를 더한값 중 작은 방의 번호를 구한다.
 * 
 */

public class BOJ_G4_13424_비밀모임 {

	static final int INF = 987654321;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N+1][N+1];
			
			for(int i = 0; i < N+1; i++) {
				for(int j = 0; j < N+1; j++) {
					// 자기 자신으로 가는 것은 0
					if(i == j) map[i][j] = 0;
					// 다른 방으로 연결은 아직 안됬으니 INF로 초기화
					else map[i][j] = INF;
				}
			}
			
			// map을 입력받는다. 
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				
				map[a][b] = map[b][a] = dist;
				
			}
			
			int K = Integer.parseInt(br.readLine());
			int[] students = new int[K];
			st = new StringTokenizer(br.readLine());
			
			// 학생이 있는 방의 배열
			for(int i = 0; i < K; i++) {
				students[i] = Integer.parseInt(st.nextToken());
			}
			
			// 플로이드 와샬
			for(int k = 1; k <= N; k++) {
				for(int i = 1; i <= N; i++) {
					for(int j = 1; j <= N; j++) {
						// i -> j로 가는것이 i -> k + k -> j로 가는 방법보다 크면
						if(map[i][k] + map[k][j] < map[i][j]) {
							// 바꿔준다.
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
			
			int min = INF;
			int ans = 0;
			
			// 각 방마다
			for(int i = 1; i <= N; i++) {
				int temp = 0;
				// 학생이 있는 방까지 거리를 더한다.
				for(int j = 0; j < K; j++) {
					 temp += map[i][students[j]];
				}
				// 가장 작은 거리일 경우
				if(min > temp) {
					// 최소값 바꿔주기
					min = temp;
					// 방 저장
					ans = i;
				}
			}
			
			// 방 출력
			System.out.println(ans);
			
		}
	}

}

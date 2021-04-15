package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 자신의 키가 몇 번째인지 알 수 있는 학생이 모두 몇명인지 구하라
 * 제약 사항: 키에대한 비교를 다 하지 않는다.
 * 문제 유형: 그래프, 그래트 탐색, 플로이드 와샬
 * 요구 개념: 플로이드 와샬
 * 
 * <풀이법 요약>
 * 접근방법이 너무 생각이  안나서 구선생의 도움을 받음
 * 1. 2차원 배열을 만들어 INF로 초기화 하고 키 순서를 입력 받는다. 작으면 -1, 크면 1
 * 2. 나보다 작은 학생보다 작은학생은 전부 나보다 작다고 갱신한다.
 * 3. 나보다 큰 학생보다 큰 학생은 다 나보다 크다고 갱신한다.
 * 4. 생신을 마치고 INF가 하나라도 남아있으면 순서를 알 수 없는 학생이다.
 * 5. INF가 남아있지 않은 학생의 수를 출력한다.
 * 
 */

public class BOJ_G4_2458_키순서 {

	static final int INF = 987654321;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][N+1];
		
		// INF로 초기화
		for(int i = 0; i <= N; i++) {
			Arrays.fill(map[i], INF);
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 더 작은것
			int sm = Integer.parseInt(st.nextToken());
			// 더 큰것
			int tl = Integer.parseInt(st.nextToken());
			// sm은 tl보다 작다.
			map[sm][tl] = -1;
			// tl은 sm보다 크다.
			map[tl][sm] = 1;
		}
		
		// 중간에 들어갈 정점
		for(int k = 1; k <= N; k++) {
			// 시작정점
			for(int i = 1; i <= N; i++) {
				// 끝 정점
				for(int j = 1; j <= N; j++) {
					if(i == j) continue;
					// 아직 작은지 큰지 모를경우
					if(map[i][j] == INF) {
						// i가 k보다 크고, k가 j보다 크면 i는 j보다 크다.
						if(map[i][k] == 1 && map[k][j] == 1) map[i][j] = 1;
						// i가 k보다 작고, k가 j보다 작으면 i는 j보다 작다.
						if(map[i][k] == -1 && map[k][j] == -1) map[i][j] = -1;
					}
					
				}
			}
		}
		
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			boolean ans = true;
			for(int j = 1; j <= N; j++) {
				// 나보다 작거나 큰지 알 수 있으면 몇번째 인지 알 수 있다.
				// 그렇지 않으면 모른다.
				if(i != j && map[i][j] == INF) {
					ans = false;
					break;
				}
			}
			// 다 값이 있는경우
			if(ans) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
		

	}

}

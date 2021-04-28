package sw_expert_academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution_D4_5643_키순서 {

	static final int INF = 987654321;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
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
			
			System.out.println("#" + t + " " + cnt);
			
		}

	}

}

package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S1_9205_맥주마시면서걸어가기 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			// 정점의 갯수 + 편의점 갯수
			int N = Integer.parseInt(br.readLine()) + 2;
			
			
			int[][] arr = new int[N][2];
			// 0 출발 N+1 은 도착지
			int[][] map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				Arrays.fill(map[i], 987654321);
				map[i][i] = 0;
			}
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				arr[i][0] = r;
				arr[i][1] = c;
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(i == j) continue;
					int dis = Math.abs(arr[i][0] - arr[j][0]) + Math.abs(arr[i][1] - arr[j][1]);
					if(dis <= 1000) {
						map[i][j] = 1;
						map[j][i] = 1;
					}
				}
			}
			
			for(int k = 0; k < N; k++) {
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
			
			if(map[0][N-1] != 987654321) System.out.println("happy");
			else System.out.println("sad");
			
		}

	}

}

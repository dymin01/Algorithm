package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D6_1263_사람네트워크2 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int N = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				Arrays.fill(map[i], N+N);
				map[i][i] = 0;
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if(temp == 1) {
						map[i][j] = 1;
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
			
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				int sum = 0;
				for(int j = 0; j < N; j++) {
					sum += map[i][j];
				}
				if(min > sum) {
					min = sum;
				}
			}
			System.out.println("#" + t + " " + min);
			
		}
		

	}

}

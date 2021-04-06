package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1149_RGB거리 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[N][N];
		
		StringTokenizer st = null;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				dp[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + dp[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + dp[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + dp[i][2];
		}
		int min = Math.min(dp[N-1][0], dp[N-1][1]);
		min = Math.min(min, dp[N-1][2]);
		
		System.out.println(min);
	}
}

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_17070_파이프옮기기1_DP {

	static int N;
	static int[][] map;
	static int[][][] dp;
	// 0 = 오른쪽
	// 1 = 오른쪽 아래
	// 2 = 아래
	static int[] dr = {0, -1, -1};
	static int[] dc = {-1, -1, 0};
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		dp = new int[N][N][3];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][1][0] = 1;
		
		for(int i = 0; i < N; i++) {
			for(int j = 2; j < N; j++) {
				
				if(map[i][j] == 1) continue;
				
				// 가로방향 파이프를 놓을 수 있는것은 가로, 대각
				dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
				
				// 맨 윗줄은 가로 말고는 파이프를 놓을 수 없다.
				if(i == 0) continue;
				
				// 세로방향 파이프를 놓을 수 있는것은 세로, 대각
				dp[i][j][2] = dp[i-1][j][2] + dp[i-1][j][1];
				
				// 대각선 파이프를 놓을 수 없으면
				if(map[i-1][j] == 1 || map[i][j-1] == 1) continue;
				
				dp[i][j][1] =  dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
			}
		}
		
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
	}

}

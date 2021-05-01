package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <문제 요약> 구해야 하는 것: 구간 합
 * 유형 : DP, 구간합
 * 요구 개념 : DP
 * 
 * <풀이법 요약>
 * 먼저 2중 for문으로 그냥 다 돌렸더니 시간초과
 * DP 만들어서 했는데 시간초과...DP를 잘못 만들었음...
 * 
 * 입력 받을때 자기의 위치보다 왼쪽과, 위에 있는 값들의 합을 저장한다. --> 구간합
 * 
 * 5 5 3 3 3
 * 5 5 3 3 3
 * 4 4 1 0 0
 * 4 4 0 0 0
 * 4 4 0 0 2
 * 
 * 1 ~ 2 구간을 구하기 위해서는 2까지 구간합을 구하고
 * 5와 3 범위의 합과, 5와 4 범위의 합을 빼고, 5의 범위를 더한다.
 * 입력을 할때부터 구간합을 구해놔야 한다.
 * 
 * 숫자는 자리를 의미함
 * 1  2  3  4  5
 * 6  7  8  9  10
 * 11 12 13 14 15
 * 0  0  0  0  0
 * 자기 자신은 자기 자신의 행과 열 까지 합을 의미한다. 즉 (3, 2)의 값은 1 2 6 7 11 12 의값을 더한 값이다.
 * 
 * 
 */

public class BOJ_S1_11660_구간합구하기5 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] dpSum = new int[N+1][N+1];
		
		int pre = 0;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int num = Integer.parseInt(st.nextToken());
				dpSum[i][j] = dpSum[i-1][j] + dpSum[i][j-1] - dpSum[i-1][j-1] + num;  
			}
		}
		
		
		for(int t = 0; t < M; t++) {
			
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			
			int ans = dpSum[r2][c2] - dpSum[r1-1][c2] -dpSum[r2][c1-1] + dpSum[r1-1][c1-1];
			
			System.out.println(ans);
			
			
		}

	}

}

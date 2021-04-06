package baekjoon;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_S3_1463_1로만들기 {

	public static void main(String[] args) {
		
		 Scanner sc = new Scanner(System.in);
		 
		 int N = sc.nextInt();
		 int[] dp = new int[N+1];
		 dp[0] = 0;
		 dp[1] = 0;
		 dp[2] = 1;
		 dp[3] = 1;
		 for(int i = 4; i <= N; i++) {
			 dp[i] =dp[i-1];
			 
			 if(i % 2 == 0) {
				 dp[i] = Math.min(dp[i], dp[i/2] + 1);
			 }
			 if(i % 3 == 0) {
				 dp[i] = Math.min(dp[i], dp[i/3] + 1);
			 }
			 
		 }
		 System.out.println(Arrays.toString(dp));
		 System.out.println(dp[N]);

	}

}

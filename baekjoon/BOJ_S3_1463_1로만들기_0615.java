package baekjoon;
import java.util.Scanner;

/***
 * 
 * <문제 요약>
 * 문제 정의 : 정해진 연산을 이용해 1을 만드려고 할때 최소 연산의 횟수
 * 문제 유형 : DP
 * 제약 사항 : 1 <= N <= 10^6
 * <풀이 요약>
 * 1. dp를 이용하여 푼다.
 * 2. 0, 1 은 미리 값을 넣어놓는다.
 * 3. 규칙에 맞춰 N까지 값을 구한다.
 * 4. dp[N]의 값을 출력한다.
 * 
 */


public class BOJ_S3_1463_1로만들기_0615 {

	public static void main(String[] args) {
		
		 Scanner sc = new Scanner(System.in);
		 
		 int N = sc.nextInt();
		 
		 int[] dp = new int[N+1];
		 
		 dp[0] = 0;
		 dp[1] = 0;
		 
		 for(int i = 2; i <= N; i++) {
			 // 1을 빼는 연산
			 dp[i] = dp[i-1] + 1;
			 
			 // 3으로 나누어 떨어지면 3으로 나누는 연산
			 if(i % 3 == 0) {
				 dp[i] = Math.min(dp[i], dp[i/3] + 1);
			 }
			 // 2로 나누어 떨어지면 2로 나누는 연산
			 if(i % 2 == 0) {
				 dp[i] = Math.min(dp[i], dp[i/2] + 1);
			 }
		 }
		 
		 System.out.println(dp[N]);
		 
	}

}

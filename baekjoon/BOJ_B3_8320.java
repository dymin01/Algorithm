package baekjoon;
import java.util.Scanner;
/***
 * <문제 요약> 
 * 구해야 하는 것 : 만들 수 있는 직사각형의 갯수
 * 문제 핵심 요약 : 약수의 갯수/2가 사각형의 갯수
 * <풀이법 요약> 
 * 1. 1~n 까지 숫자의 약수를 구한다.
 * 2. 약수의 갯수 / 2가 만들 수 있는 사각형의 갯수이므로 다 더한다.
 */

public class BOJ_B3_8320 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int ans = 0;
		// 1개 ~ n개로 만들 수 있는 사각형의 갯수
		for (int n = 1; n <= N; n++) {
			int cnt = 0;
			// 약수 구하기
			for (int i = 1; i * i <= n; i++) {
				if (n % i == 0) {
					cnt++;
					if (i * i < n) {
						cnt++;
					}
				}
			}
			// 약수의 갯수가 홀수일 경우 1을 더해준다.
			if(cnt % 2 != 0) cnt++;
			//약수의 갯수 /2 사각형의 갯수
			ans += cnt/2;
		}

		System.out.println(ans);
	}
}

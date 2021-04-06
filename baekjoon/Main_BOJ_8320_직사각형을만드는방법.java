package baekjoon;
import java.util.Scanner;

public class Main_BOJ_8320_직사각형을만드는방법 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int ans = 0;
		for (int n = 1; n <= N; n++) {
			int cnt = 0;
			for (int i = 1; i * i <= n; i++) {
				if (n % i == 0) {
					cnt++;
					if (i * i < n) {
						cnt++;
					}
				}
			}
			if(cnt % 2 != 0) cnt++;
			
			ans += cnt/2;
		}

		System.out.println(ans);
	}

}

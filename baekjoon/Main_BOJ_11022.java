package baekjoon;
import java.util.Scanner;

public class Main_BOJ_11022 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			int A, B;
			A = sc.nextInt();
			B = sc.nextInt();
			
			System.out.printf("Case #%d: %d + %d = %d\n", t, A, B, A+B);
		}

	}

}

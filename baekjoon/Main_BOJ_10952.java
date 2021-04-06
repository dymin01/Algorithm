package baekjoon;
import java.util.Scanner;

public class Main_BOJ_10952 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int A, B;
		A = sc.nextInt();
		B = sc.nextInt();
		while(A != 0 && B != 0) {
			System.out.println(A+B);
			A = sc.nextInt();
			B = sc.nextInt();
		}

	}

}

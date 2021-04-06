package baekjoon;
import java.util.Scanner;

public class Main_BOJ_2577 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[] num;
		num = new int[10];
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		int sum = A * B * C;

		while(sum != 0) {
			num[sum%10]++;
			sum /= 10;
		}
		
		for(int a : num) {
			System.out.println(a);
		}
	}

}

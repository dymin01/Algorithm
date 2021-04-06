package baekjoon;
import java.util.Scanner;

public class Main_BOJ_3052 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] num = new int[42];
		int A;
		int sum = 0;
		for(int i = 0; i < 10; i++) {
			A = sc.nextInt();
			num[A % 42]++;
		}
		
		for(int a : num) {
			if(a != 0) {
				sum++;
			}
		}
		System.out.println(sum);

	}

}

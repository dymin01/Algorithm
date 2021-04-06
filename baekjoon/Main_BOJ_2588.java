package baekjoon;
import java.util.Scanner;

public class Main_BOJ_2588 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		
		int il, sip, bak;
		il = num2 % 10;
		sip = (num2 % 100) / 10;
		bak = num2 / 100;
		
		System.out.println(num1 * il);
		System.out.println(num1 * sip);
		System.out.println(num1 * bak);
		System.out.println(num1 * num2);

	}

}

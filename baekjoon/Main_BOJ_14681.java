package baekjoon;
import java.util.Scanner;

public class Main_BOJ_14681 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X, Y;
		X = sc.nextInt();
		Y = sc.nextInt();
		
		if(X < 0) {
			if(Y < 0) {
				System.out.println(3);
			} else {
				System.out.println(2);
			}
		}else {
			if(Y < 0) {
				System.out.println(4);
			} else {
				System.out.println(1);
			}
		}

	}

}

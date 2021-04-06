package jungol;
import java.util.Scanner;

public class Main_1719_별삼각형3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		if(n > 100 || n%2 == 0) {
			System.out.println("INPUT ERROR!");
			return;
		}
		
		for(int i = 0; i < n; i++) {
			
			for(int j = 0; j < -Math.abs(i-n/2)+n/2; j++) {
				System.out.print(" ");
			}
			
			for(int j = 0; j < (-Math.abs(i-n/2)+n/2)*2 + 1; j++) {
				System.out.print("*");
			}
			System.out.println();
			
		}
		
	}
}

package jungol;
import java.util.Scanner;

public class Main_1523_별삼각형1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int number1 = sc.nextInt();
		int number2 = sc.nextInt();
		
		if(0 > number1 || 100 < number1) {
			System.out.println("INPUT ERROR!");
			return;
		}
		if(number2 < 1 || number2 > 3) {
			System.out.println("INPUT ERROR!");
			return;
		}
		
		switch(number2) {
			case 1 :
					for(int i=1; i<=number1; i++) {
						for(int j=0; j<i; j++) {
							System.out.print("*");
						}
						System.out.println();
					}
				break;
			
			case 2 :
					for(int i=number1; i>=0; i--) {
						
						for(int j=0; j<i; j++) {
							System.out.print("*");
						}
						System.out.println();
					}
				break;
			
			case 3 :
					for(int i=0; i<number1; i++) {
						
						for(int j=1; j<number1 - i; j++) {
							System.out.print(" ");
						}
						
						for(int k=0; k<2*i+1; k++) {
							System.out.print("*");
						}
						System.out.println();
					}
				break;
		}
	}
}

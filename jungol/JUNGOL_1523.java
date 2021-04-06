package jungol;
import java.util.Scanner;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 별삼각형 출력
 * 문제 핵심 요약 : 구현
 * <풀이법 요약> 
 * 1. m이 1일결우 삼각형 출력
 * 2. m이 2일결우 삼각형을 거꾸로 출력
 * 3. m이 3일결우 빈공간과 삼각형을 출력
 */

public class JUNGOL_1523 {

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
						//공백의 갯수
						for(int j=1; j<number1 - i; j++) {
							System.out.print(" ");
						}
						// 별의 갯수
						for(int k=0; k<2*i+1; k++) {
							System.out.print("*");
						}
						System.out.println();
					}
				break;
		}
	}
}

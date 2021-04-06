package jungol;
import java.util.Scanner;
/***
 * <문제 요약> 
 * 구해야 하는 것 : 별삼각형 출력
 * 문제 핵심 요약 : 구현
 * <풀이법 요약> 
 * 1. m에 따라 다른 별을 출력한다.
 */

public class JUNGOL_1719 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		if(n > 100 || m < 1 || m > 4 || n%2 == 0) {
			System.out.println("INPUT ERROR!");
			return;
		}
		
		switch (m) {
		case 1:
			for(int i = 0; i < n; i++) {
				// 모래시계 만드는 공식을 사용해서 별을 출력한다.
				for(int j = 0; j <= -Math.abs(i-n/2) + n/2; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 2:
			for(int i = 0; i < n; i++) {
				// 모래시계에서 별을 출력하는 갯수/2 만큼 공백을 출력한다.
				for(int j = 0; j < (n-2*(-Math.abs(i-n/2) + n/2))/2; j++){
					System.out.print(" ");
				}
				// 모래시계에서 공백의 갯수만큼 별을 출력한다.
				for(int j = 0; j <= -Math.abs(i-n/2) + n/2; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 3:
			// 모래시계 출력
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < -Math.abs(i-n/2) + n/2; j++) {
					System.out.print(" ");
				}
				for(int j = 0; j < n-2*(-Math.abs(i-n/2) + n/2); j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 4:
			// 위에 삼각형
			for(int i = 0; i < n/2; i++) {
				for(int j = 0; j < i; j++) {
					System.out.print(" ");
				}
				for(int j = 0; j <= n/2-i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			// 아래쪽 삼각형
			for(int i = 0; i < n/2 + 1; i++) {
				for(int j = 0; j < n/2; j++) {
					System.out.print(" ");
				}
				for(int j = 0; j <= i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			
			break;

		default:
			break;
		}
	}
}

package jungol;
import java.util.Scanner;
/***
 * <문제 요약> 
 * 구해야 하는 것 : 별삼각형 출력
 * 문제 핵심 요약 : 구현
 * <풀이법 요약> 
 * 1. 모래시게 만드는 공식으로 빈공간을 만든다.
 * 2. 빈공간의 갯수 *2개 만큼 별을 출력한다.
 * 3. 높이만큼 반복한다.
 */

public class JUNGOL_1329 {

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

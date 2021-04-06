package baekjoon;
import java.util.Scanner;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 얻을 수 있는 수익
 * 문제 핵심 요약 : 
 * <풀이법 요약> 
 * 1. 다이아 몬드 출력과 같이 빈공간을 만드는 위치부터 별을 찍는 위치까지 숫자를 더한다.
 */

public class Solution_D3_2805 {

	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int size = sc.nextInt();
			int ans = 0;
			map = new int[size][size];
			//초기화
			for(int i = 0; i < size; i++) {
				String str = sc.next();
				for(int j = 0; j < size; j++) {
					map[i][j] = str.charAt(j)-'0';
				}
			}
			
			for(int i = 0; i < size; i++) {
				// J의 범위는 다이아몬드의 끝
				for(int j = 0; j < size-2*Math.abs(i-size/2); j++) {
					// J의 시작위치는 다이아몬드의 공백문자의 끝
					ans += map[i][j + Math.abs(i-size/2)];
				}
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

}
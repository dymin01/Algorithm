package sw_expert_academy;
import java.util.Scanner;

public class Solution_D3_2805_농작물수확 {

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
				for(int j = 0; j < size-2*Math.abs(i-size/2); j++) {
					ans += map[i][j + Math.abs(i-size/2)];
				}
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

}
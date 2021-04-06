package baekjoon;
import java.util.Scanner;

public class Main_BOJ_2999_비밀이메일 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		int len = str.length();
		int R = findR(len);
		int C = len / R;
		char[][] map = new char[R][C];
		
		for(int j = 0; j < C; j++) {
			for(int i = 0; i < R; i++) {
				map[i][j] = str.charAt(i + j*R);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < R; i++) {
			sb.append(String.valueOf(map[i]));
		}
		System.out.println(sb.toString());
		
	}
	
	public static int findR(int n) {
		int ans = 0;
		
		for(int i = (int)Math.sqrt((double)n); i >= 1; i--) {
			if(n % i == 0) {
				ans = Math.max(ans, i);
				break;
			}
		}
		
		return ans;
	}

}

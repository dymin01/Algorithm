package baekjoon;
import java.util.Scanner;

public class Main_BOJ_2675 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 0; t < T; t++) {
			int num = sc.nextInt();
			String str = sc.next();
			String nStr = "";
			for(int i = 0; i < str.length(); i++) {
				for(int j = 0; j < num; j++) {
					nStr += str.charAt(i);
				}
			}
			System.out.println(nStr);
		}

	}

}

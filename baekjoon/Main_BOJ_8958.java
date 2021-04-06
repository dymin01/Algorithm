package baekjoon;
import java.util.Scanner;

public class Main_BOJ_8958 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 0; t < T; t++) {
			String OX = sc.next();
			int point = 1;
			int sum = 0;
			for(int i = 0; i < OX.length(); i++) {
				if(OX.charAt(i) == 'O') {
					sum += point;
					point++;
				} else {
					point = 1;
				}
			}
			System.out.println(sum);
			
		}

	}

}

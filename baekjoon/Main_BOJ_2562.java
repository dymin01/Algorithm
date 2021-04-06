package baekjoon;
import java.util.Scanner;

public class Main_BOJ_2562 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int max = -100000;
		int idx = 0;
		int num;
		for(int i = 0; i < 9; i++) {
			num = sc.nextInt();
			if(max < num) {
				max = num;
				idx = i+1;
			}
		}
		System.out.printf("%d\n%d", max, idx);
	}

}

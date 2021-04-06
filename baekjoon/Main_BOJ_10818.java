package baekjoon;
import java.util.Scanner;

public class Main_BOJ_10818 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int max = -1000001;
		int min = 1000001;
		int num;
		for(int i = 0; i < N; i++) {
			num = sc.nextInt();
			if(max < num) {
				max = num;
			}
			if(min > num) {
				min = num;
			}
		}
		System.out.printf("%d %d", min, max);
	}

}

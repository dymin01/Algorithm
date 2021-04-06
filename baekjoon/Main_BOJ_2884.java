package baekjoon;
import java.util.Scanner;

public class Main_BOJ_2884 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int H, M;
		int hh, mm;
		
		H = sc.nextInt();
		M = sc.nextInt();
		hh = H;
		mm = M - 45;
		if(mm < 0) {
			hh = H-1;
			mm = 60 + mm;
		}
		if(hh < 0) {
			hh = 24 + hh;
		}
		System.out.printf("%d %d", hh, mm);
	}

}

package baekjoon;
import java.util.Scanner;

public class Main_BOJ_2851_슈퍼마리오 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[10];
		
		for(int i = 0; i < 10; i++) {
			arr[i] = sc.nextInt();
		}
		
		int ans = 0;
		for(int i = 0; i < 10; i++) {
			ans += arr[i];
			if(ans >= 100) {
				if(100 - ans - arr[i-1] == ans + arr[i] - 100) {
					ans += arr[i];
				}
				break;
			}
		}
		
		System.out.println(ans);

	}

}

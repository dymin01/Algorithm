package baekjoon;
import java.util.Scanner;

public class Main_BOJ_1316 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int ans = 0;
		for(int t = 0; t < T; t++) {
			String str = sc.next();
			int[] check = new int[26];
			check[(int)str.charAt(0)-97]++;
			ans++;
			for(int i = 1; i < str.length(); i++) {
				if(str.charAt(i-1) == str.charAt(i)) {
					continue;
				} else if(check[(int)str.charAt(i)-97] == 0) {
					check[(int)str.charAt(i)-97]++;
				} else {
					ans--;
					break;
				}
				
			}
		}
		System.out.println(ans);

	}

}

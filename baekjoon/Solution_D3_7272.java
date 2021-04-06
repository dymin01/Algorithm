package baekjoon;
import java.util.Scanner;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 경근이가 봤을때 두 문자가 같은지 다른지
 * 문제 핵심 요약 : 구현
 * <풀이법 요약> 
 * 1. 알파벳의 갯수가 다르면 무조건 다르다.
 * 2. 알파벳을 하나씩 비교해서 같은지 확인
 */

public class Solution_D3_7272 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			String str1 = sc.next();
			String str2 = sc.next();
			String ans = "SAME";
			if (str1.length() != str2.length()) {
				System.out.println("#" + t + " DIFF");
				continue;
			}
			for (int i = 0; i < str1.length(); i++) {
				// 알파벳의 동그라미의 갯수가 같으면 같은 알파벳, 다르면 다른 알파벳
				if (countAlpha(str1.charAt(i)) != countAlpha(str2.charAt(i))) {
					ans = "DIFF";
					break;
				}
			}
			System.out.println("#" + t + " " + ans);
			
		}

	}

	public static int countAlpha(char c) {
		int ans = 0;

		switch (c) {
		case 'A':
		case 'D':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
			ans = 1;
			break;
		case 'B':
			ans = 2;
			break;
		default:
			ans = 0;
			break;
		}
		return ans;

	}

}

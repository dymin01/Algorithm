package sw_expert_academy;
import java.util.Scanner;

public class Solution_D3_7272_안경이없어 {

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

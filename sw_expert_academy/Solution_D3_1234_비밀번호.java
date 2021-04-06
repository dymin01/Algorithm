package sw_expert_academy;
import java.util.Scanner;

public class Solution_D3_1234_비밀번호 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = 10;
		for(int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			StringBuilder str = new StringBuilder(sc.next());
			
			for(int i = 0; i < str.length()-1; i++) {
				if(str.charAt(i) == str.charAt(i+1)) {
					str.deleteCharAt(i+1);
					str.deleteCharAt(i);
					i = -1;
				}
			}
			System.out.println("#" + t + " " + str);
		}
	}
}

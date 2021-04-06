package baekjoon;
import java.util.Scanner;

public class Main_BOJ_2941 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		str+= "   ";
		int ans = 0;
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(ch == ' ') {
				break;
			}
			
			if (ch == 'n' || ch == 'l') {
	            if (str.charAt(i+1) == 'j') continue;
	        }
	        else if (ch == 'c' || ch == 's' || ch == 'z') {
	            if (str.charAt(i+1) == '=' || str.charAt(i+1) == '-') continue;
	        }
	        else if (ch == 'd') {
	            if (str.charAt(i+1) == '-') continue;
	            if (str.charAt(i+1) == 'z' && str.charAt(i+2) == '=') continue;
	        }
			ans++;
		}
		
		System.out.println(ans);
	}

}

package baekjoon;
import java.util.Scanner;
/***
 * <문제 요약> 
 * 구해야 하는 것 : 크로아티아 알파벳의 갯수
 * 문제 핵심 요약 : 구현
 * <풀이법 요약> 
 * 1. 입력받은 글자가 n, l일 경우 뒤에 j가 나오면 글자를 더하지 않고 넘어간다.
 * 2. 입력받은 글자가 c, s, z 일 경우 뒤에가 = - 일경우 더하지 않고 넘어간다.
 * 3. 입력받은 글자가 d 이고 -, z = 일경우 넘어간다.
 */
public class BOJ_S5_2941 {

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

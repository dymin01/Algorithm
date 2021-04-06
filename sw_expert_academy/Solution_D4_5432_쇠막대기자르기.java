package sw_expert_academy;


import java.util.Scanner;

public class Solution_D4_5432_쇠막대기자르기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			String str = sc.next();
			int answer = 0;
			int cnt = 0;
			for(int i = 0; i < str.length(); i++) {
				//괄호가 열려있으면
				if(str.charAt(i) == '(') {
					cnt++;
				}//괄호가 닫혀있으면
				else {
					cnt--;
					//쇠막대가 끝나면
					if(str.charAt(i-1) == ')') {
						answer++;
					}// 쇠막대를 자르면
					else if(cnt != 0) {
						answer += cnt;
					}
				}
			}
			System.out.println("#" + t + " " + answer);
		}
		
	}

}

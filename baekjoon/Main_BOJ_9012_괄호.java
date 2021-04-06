package baekjoon;
import java.util.Scanner;
import java.util.Stack;

public class Main_BOJ_9012_괄호 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Character> stack = new Stack<>();
		int N = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			String str = sc.next();
			boolean isBreak = false;
			stack.clear();
			for(int j = 0; j < str.length(); j++) {
				char temp = str.charAt(j);
				if(temp == '(') {
					stack.push(temp);
				}else {
					if(stack.isEmpty()) {
						isBreak = true;
						break;
					}
					stack.pop();
				}
			}
			if(!isBreak && stack.isEmpty()) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
	}
}

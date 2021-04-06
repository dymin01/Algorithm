package baekjoon;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main_BOJ_1874_스택수열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Stack<Integer> stack = new Stack<>();
		int idx = 1;
		int num = 1;
		int arr = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		while(num <= N) {
			if(num == arr) {
				stack.push(num);
				sb.append("+\n");
				stack.pop();
				arr = sc.nextInt();
				idx++;
				sb.append("-\n");
				num++;
			}else if(arr < num) {
				int temp = stack.pop();
				if(temp != arr) {
					sb = new StringBuilder();
					sb.append("NO");
					break;
				}
				arr = sc.nextInt();
				idx++;
				sb.append("-\n");
			}else if(num < arr) {
				stack.push(num);
				sb.append("+\n");
				num++;
			}
		}
		while(!stack.isEmpty() && sb.length() != 0) {
			if(arr != stack.pop()) {
				sb = new StringBuilder();
				sb.append("NO");
				break;
			}
			sb.append("-\n");
			if(stack.isEmpty())
				break;
			if(idx <= N) {
				arr = sc.nextInt();
				idx++;
			}
		}
		
		System.out.print(sb);
	}

}

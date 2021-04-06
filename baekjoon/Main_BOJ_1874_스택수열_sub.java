package baekjoon;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main_BOJ_1874_스택수열_sub {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		Stack<Integer> stack = new Stack<>();
		
		int N = sc.nextInt();
		int start = 0;
		
		//N번 반복
		while(N-- > 0) {
			int value = sc.nextInt();
			if(value > start) {
				// start + 1 부터 입력받은 value 까지 push 한다.
				for(int i = start + 1; i <= value; i++) {
					stack.push(i);
					sb.append("+\n");
				}
				//다음 push 할 때 오름차순을 유지하기 위한 변수
				start = value;
				// top에 있는 원소가 입력받은 값과 같지 않은 경우
			} else if(stack.peek() != value) {
				System.out.println("NO");
				return;
			}
			stack.pop();
			sb.append("-\n");
			
		}
		System.out.println(sb);
		
	}

}

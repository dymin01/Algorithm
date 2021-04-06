package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_10828_스택{

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			if(st.equals("push")) {
				stack.push(Integer.parseInt(st.nextToken()));
			}else if(st.equals("top")) {
				if(stack.empty())
					System.out.println(-1);
				else
					System.out.println(stack.peek());
			}else if(st.equals("size")) {
				System.out.println(stack.size());
			}else if(st.equals("empty")) {
				if(stack.empty())
					System.out.println(1);
				else
					System.out.println(0);
			}else if(st.equals("pop")) {
				if(stack.empty())
					System.out.println(-1);
				else
					System.out.println(stack.pop());
			}		
		}
	}
}



/*import java.util.Scanner;
import java.util.Stack;

public class Main_BOJ_10828_스택 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Stack<Integer> stack = new Stack<>();
		
		 for(int i = 0; i < N; i++) {
	    	  String str = sc.next();
	    	  if(str.contains("push")) {
	    		int num = sc.nextInt();
	    		stack.push(num);
	    	  }else if(str.equals("pop")){
	    		 System.out.println(stack.isEmpty() ? -1 : stack.pop());
	    	  }else if(str.equals("size")) {
	    		  System.out.println(stack.size());
	    	  }else if(str.equals("empty")) {
	    		 System.out.println(stack.isEmpty()? 1 : 0);
	    	  }else if(str.equals("top")) {
	    		System.out.println(stack.isEmpty()? -1 : stack.peek());
	    	  }
	      }
	}

}
*/
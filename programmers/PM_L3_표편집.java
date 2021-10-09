package programmers;

import java.util.*;

public class PM_L3_표편집 {

	public static void main(String[] args) {
		
		PM_L3_표편집 sol = new PM_L3_표편집();
		
		int n = 8;
		int k = 2;
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
		
		System.out.println(sol.solution(n, k, cmd));

	}
	
	public String solution(int n, int k, String[] cmd) {
        
        Stack<Integer> stack = new Stack<>();
        
        
        StringTokenizer st = null;
        int newSize = n;
        for(String str : cmd) {
        	st = new StringTokenizer(str);
        	String comand = st.nextToken();
        	if(comand.charAt(0) == 'U') {
        		k -= Integer.parseInt(st.nextToken());
        		
        	}else if(comand.charAt(0) == 'D') {
        		k += Integer.parseInt(st.nextToken());
        		
        	}else if(comand.charAt(0) == 'C') {
        		stack.add(k);
        		newSize--;
        		if(k == newSize) k--;
        	}else {
        		int num = stack.pop();
        		if(num <= k) k++;
        		newSize++;
        	}
        	
        }
        
        
        StringBuilder ans = new StringBuilder();
        
        for(int i = 0; i < newSize; i++) {
        	ans.append("O");
        }
        while(!stack.isEmpty()) {
        	ans.insert(stack.pop(), "X");
        }
        
        return ans.toString();
        
        
    }

}

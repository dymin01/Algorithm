package programmers;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class PM_L2_수식최대화 {

	public static void main(String[] args) {

		PM_L2_수식최대화 sol = new PM_L2_수식최대화();
		
		//String expression = "100-200*300-500+20";
		String expression = "50*6-3*2";
		
		System.out.println(sol.solution(expression));
	}
	
	static char[] op = {'+', '-', '*'};
	
	static boolean[] v;
	
	static long ans;
	
    public static long solution(String expression) {
        long answer = 0;
        
        int size = expression.length();

        ans = 0;
        // + - *
        v = new boolean[3];
        
        StringTokenizer st = new StringTokenizer(expression, "-+*");
        ArrayList<Long> nums = new ArrayList<>();
        ArrayList<Character> opers = new ArrayList<>();
        int opidx = 0;
        while(st.hasMoreTokens()) {
        	String strnum = st.nextToken();
        	
        	Long num = Long.parseLong(strnum);
        	nums.add(num);
        	int len = strnum.length();
        	opidx += len;
        	if(opidx < size) {
        		opers.add(expression.charAt(opidx));
        		opidx++;
        	}
        }
        
        DFS(0, nums, opers);
        
        answer = ans;
        return answer;
    }
    
    public static void DFS(int cnt, ArrayList<Long> numbers, ArrayList<Character> opers) {
    	if(cnt == 3) {
    		ans = Math.max(ans, Math.abs(numbers.get(0)));
    		return;
    	}
    	
    	for(int i  = 0; i < 3; i++) {
    		if(v[i]) continue;
    		v[i] = true;
    		ArrayList<Long> num = new ArrayList<>();
    		ArrayList<Character> ope = new ArrayList<>();
    		
    		num.addAll(numbers);
    		ope.addAll(opers);
    		
    		long pre = num.get(0);
    		for(int j = 0; j < ope.size(); j++) {
    			long nn = num.get(j+1);
    			char oo = ope.get(j);
    			if(op[i] == ope.get(j)) {
    				if(oo == '+') {
    					pre += nn;
    				}else if (oo == '-') {
    					pre -= nn;
    				}else {
    					pre *= nn;
    				}
    				num.remove(j+1);
    				num.remove(j);
    				ope.remove(j);
    				num.add(j, pre);
    				j--;
    			}else {
    				pre = nn;
    			}
    		}
    		
    		DFS(cnt+1, num, ope);
    		v[i] = false;
    	}
    	
    }

}

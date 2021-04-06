package programmers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 이중우선순위큐_linkedlist {

	public static void main(String[] args) throws Exception {
		
		//String[] operations = {"I 16", "D 1"};
		String[] operations = {"I 7","I 5","I -5","D -1"};
		int[] answer = solution(operations);
		System.out.println(answer[0] + " " + answer[1]);
		
	}
	
	 public static int[] solution(String[] operations) throws Exception {
	        int[] answer = {0,0};
	        StringTokenizer st = null;
	        
	        LinkedList<Integer> list = new LinkedList<>();
	        
	        for(int i = 0; i < operations.length; i++) {
	        	st = new StringTokenizer(operations[i], " ");
	        	String order = st.nextToken();
	        	int num = Integer.parseInt(st.nextToken());
	        	if(order.contains("I")) {
	        		list.add(num);
	        	}else {
	        		if(!list.isEmpty()) {
	        			if(num < 0) {
	        				list.removeFirst();
	        			}else {
	        				list.removeLast();
	        			}
	        		}
	        	}
	        	Collections.sort(list);
	        }
	        if(!list.isEmpty()) {
	        	answer[0] = list.get(list.size()-1);
	        	answer[1] = list.get(0);
	        }

	        return answer;
	    }

}

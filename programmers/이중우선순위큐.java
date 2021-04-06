package programmers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 이중우선순위큐 {

	public static void main(String[] args) throws Exception {
		
		//String[] operations = {"I 16", "D 1"};
		String[] operations = {"I 7","I 5","I -5","D -1"};
		int[] answer = solution(operations);
		System.out.println(answer[0] + " " + answer[1]);
		
	}
	
	 public static int[] solution(String[] operations) throws Exception {
	        int[] answer = {0,0};
	        
	        //오름차순
	        PriorityQueue<Integer> AcQueue = new PriorityQueue<>();
	        //내림차순
	        PriorityQueue<Integer> DcQueue = new PriorityQueue<>(Collections.reverseOrder());
	        
	        StringTokenizer st = null;
	        
	        
	        for(int i = 0; i < operations.length; i++) {
	        	st = new StringTokenizer(operations[i], " ");
	        	String order = st.nextToken();
	        	int num = Integer.parseInt(st.nextToken());
	        	// 숫자 삽입
	        	if(order.contains("I")) {
	        		AcQueue.offer(num);
	        		DcQueue.offer(num);
	        	}
	        	// 숫자 삭제
	        	else {
	        		if(AcQueue.isEmpty()) continue;
	        		
	        		if(num > 0) {
	        			int max = DcQueue.poll();
	        			AcQueue.remove(max);
	        		}else {
	        			int min = AcQueue.poll();
	        			DcQueue.remove(min);
	        		}
	        	}
	        }
	        
	        if(!AcQueue.isEmpty()) {
	        	answer[0] = DcQueue.peek();
	        	answer[1] = AcQueue.peek();
	        }

	        return answer;
	    }

}

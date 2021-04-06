package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 디스크컨트롤러 {

	public static void main(String[] args) {
		
		int[][] jobs = {{0,3},{1,9},{2,6}};
		int answer = solution(jobs);
		
		System.out.println(answer);
		
	}
	
    public static int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); 
        
        int idx = 0;
        int now = 0;
        int count = 0;
        while(count < jobs.length) {
        	
        	while(idx < jobs.length && jobs[idx][0] <= now) {
        		queue.add(jobs[idx+1]);
        	}
        	
        	if(queue.isEmpty()) {
        		now = jobs[idx][0];
        	}else {
        		int[] temp = queue.poll();
        		answer += temp[1] + now - temp[0];
        		now += temp[1];
        		count++;
        	}

        }
        
        return answer;
    }

}

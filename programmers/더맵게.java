package programmers;

import java.util.PriorityQueue;

public class 더맵게 {

	public static void main(String[] args) {
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K = 7;
		System.out.println(solution(scoville, K));
	}
	
	public static int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> foods = new PriorityQueue<>();
        
        for(int a : scoville) {
        	foods.add(a);
        }
        
        while(foods.size() >= 2) {
        	if(foods.peek() >= K) {
        		break;
        	}
        	int first = foods.poll();
        	int second = foods.poll();
        	int temp = first + (second * 2);
        	foods.add(temp);
        	answer++;
        }
        
        if(foods.peek() < K) {
        	answer = -1;
        }
        
        return answer;
    }

}

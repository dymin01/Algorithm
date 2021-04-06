package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {

	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = {7,4,5,6};
		System.out.println(solution(bridge_length, weight, truck_weights));
	}
	
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        int numOfTruck = truck_weights.length;
        //다리
        Queue<Integer> queue = new LinkedList<>();
        //트럭별 걸리는 시간
        int[] time = new int[numOfTruck];
        
        int truckIndex = 0;
        //다리에 올라가 있는 틀럭들의 무게
        int sum = 0;
        while(truckIndex < numOfTruck) {
        	//건너갈 수 있다.
        	if(sum + truck_weights[truckIndex] <= weight) {
        		queue.add(truck_weights[truckIndex]);
        		time[truckIndex] = bridge_length;
        		sum += truck_weights[truckIndex];
        		truckIndex++;
        	}
        	
        	//1초 지남 -> 트럭별 1초 감소
        	for(int i = 0; i < truckIndex; i++) {
        		time[i]--;
        	}
        	//다 지나간 트럭 제거
        	for(int i = 0; i < truckIndex; i++) {
        		if(time[i] == 0) {
        			time[i] = -1;
        			sum -= queue.remove();
        		}
        	}
        	answer++;
        }
      //남아있는 다리의 길이(시간) + 다리를 나오는 1초
        int temp = time[numOfTruck-1];
        answer+= (temp+1);

        return answer;
    }

}

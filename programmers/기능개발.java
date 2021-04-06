package programmers;

import java.util.Arrays;

public class 기능개발 {

	public static void main(String[] args) {
		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		int[] answer = solution(progresses, speeds);
		
		for(int a : answer)
			System.out.print(a + " ");
	}
	
	
	public static int[] solution(int[] progresses, int[] speeds) {
        int[] dayOfend = new int[100];
        int day = -1;
        for(int i=0; i<progresses.length; i++) {
            while(progresses[i] + (day*speeds[i]) < 100) {
                day++;
            }
            dayOfend[day]++;
        }
        return Arrays.stream(dayOfend).filter(i -> i!=0).toArray();
    }
	
	
	

	/*public static int[] solution(int[] progresses, int[] speeds) {
		int[] answer = {};
		int size = progresses.length;
		answer = new int[size];
		int[] day = new int[size];
		
		for(int i = 0; i < size; i++) {
			if((100 - progresses[i]) % speeds[i] != 0) {
				// speed로 나누어 떨어지는 경우
				day[i] = (100 - progresses[i]) / speeds[i] + 1;
			}else {
				//아닌경우
				day[i] = (100 - progresses[i]) / speeds[i];
			}
		}
		
		int cnt = 1;
		int temp = day[0];
		int idx = 0;
		for(int i = 1; i < day.length; i++) {
			if(day[i] <= temp) {
				cnt++;
			} else {
				answer[idx++] = cnt;
				cnt = 1;
				temp = day[i];
			}
		}
		answer[idx] = cnt;
		
		return Arrays.copyOf(answer, idx+1);
	}*/

}

package programmers;

/**
 * <문제 요약>
 * 구해야 하는 것: 가장 많은 사람들이 시청할 수 있는 광고 삽입 구간
 * 제약 사항: play_time, adv_time은 HH:MM:SS 형식이며, 00:00:01 이상 99:59:59 이하입니다.
 * 문제 유형: 투포인터, 슬라이딩윈도우
 * 요구 개념: 투포인터, 슬라이딩윈도우
 * 
 * <풀이법 요약>
 * 1. 시간을 초로 표현한다.
 * 2. 0 ~ play_time 사이에 사람들이 시청하는 시간에 +1을 한다.
 * 3. 0 ~ play_time 까지 adv_time 크기만큼만 확인하면서 구간 합을 구한다.
 * 4. 구간합이 가장 큰 곳의 시작 시간(초)를 저장한다.
 * 5. 초를 시간(String)으로 다시 바꾼 후 반환한다.
 * 
 */


public class PM_L3_광고삽입 {

	public static void main(String[] args) {
		
		PM_L3_광고삽입 sol = new PM_L3_광고삽입();
		
		String play_time="99:59:59";
		String adv_time="25:00:00";
		String[] logs= {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
		String result="01:30:59";
		System.out.println(sol.solution(play_time,adv_time,logs));

	}

	public static String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";
		
		if(play_time.equals(adv_time)) return "00:00:00";
		int playtime = change(play_time);
		
		int[] map = new int[playtime+1];
		
		int advtime = change(adv_time);
		int size = logs.length;
		for(int i = 0; i < size; i++) {
			String[] str = logs[i].split("-");
			int start = change(str[0]);
			int end = change(str[1]);
			for(int j = start; j < end; j++) {
				map[j] += 1;
			}
		}
		
		int left = 0;
		int right = advtime;
		
		long sum = 0;
		for(int i = left; i < right; i++) {
			sum += map[i];
		}
		long max = sum;
		int idx = 0;
		while(right <= playtime) {
			sum -= map[left++];
			sum += map[right++];
			if(sum > max) {
				max = sum;
				idx = left;
			}
			
		}
		
		answer = intToTime(idx);
		
        return answer;
	}

	private static String intToTime(int i) {
		int h = i / 3600;
		int m = i % 3600 / 60;
		int s = i % 3600 % 60;
		System.out.println(i);
		return String.format("%02d:%02d:%02d", h, m, s);
	}

	private static int change(String time) {
		
		String[] str = time.split(":");
		int h = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		int s = Integer.parseInt(str[2]);
		return (h * 3600) + (m * 60) + s;
	}

}

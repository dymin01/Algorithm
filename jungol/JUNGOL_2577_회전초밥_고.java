package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 회전초밥을 연속으로 먹을때 가장 많은 종류를 먹는 경우의 수
 * 제약 사항: 쿠폰이 있다. 배열의 길이가 3000000이다.
 * 문제 유형: 투포인터, 슬라이딩 윈도우 라고 한다.
 * 요구 개념: 투포인터
 * 
 * <풀이법 요약>
 * 1. map을 이용하여 중복을 체크하였다.
 * 2. 시간초과가 뜬다....map을 탐색하고 get으로 받아오고 하는 것이 시간이 오래 걸리는것 같다.
 * 
 * 
 */


public class JUNGOL_2577_회전초밥_고 {

	static int N, d, k, c;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int[] belt = new int[N];
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
			if(i < k) {
				if(map.containsKey(belt[i]))
					map.put(belt[i], map.get(belt[i])+1);
				else {
					map.put(belt[i], 1);
				}
			}
		}
		int left = 0;
		int idx = k;
		
		int max = map.size();
		int ans = 0;
		
		do {
			// 선택한 초밥의 종류가 많은경우
			if(map.size() >= max) {
				// 쿠폰 사용할 수 있나...
				// 쿠폰의 초밥이 있는경우
				max = map.size();
				if(!map.containsKey(c))
					max += 1;
			}
			
			if(max == d) break;
			
			// 새로운것 추가
			if(map.containsKey(belt[idx]))
				map.put(belt[idx], map.get(belt[idx]) + 1);
			else
				map.put(belt[idx], 1);
			// 가장 왼쪽거 제거
			map.put(belt[left], map.get(belt[left]) - 1);
			if(map.get(belt[left]) == 0) map.remove(belt[left]);
			
			
			left++;
			idx++;
			if(idx == N) idx = 0;
		}while(idx != k);
		
		System.out.println(max);
		
	}

}

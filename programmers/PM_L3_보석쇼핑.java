package programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class PM_L3_보석쇼핑 {

	public static void main(String[] args) {
		
		PM_L3_보석쇼핑 sol = new PM_L3_보석쇼핑();
		
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		//String[] gems = {"AA", "AB", "AC", "AA", "AC"};
		//String[] gems = {"XYZ", "XYZ", "XYZ"};
		//String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
		//String[] gems = {"DIA", "EM", "EM", "RUB", "DIA"};
		// 3 5
		
		int[] ans = sol.solution(gems);
		
		for(int a : ans) {
			System.out.print(a + " ");
		}
	}
	
	// 투포인터
	// 효율성 2문제 틀림.
//	public static int[] solution(String[] gems) {
//		
//		int[] answer = new int[2];
//        HashMap<String, Integer> hashmap = new HashMap<>();
//        
//        int size = gems.length;
//        for(int i = 0; i < size; i++) {
//        	hashmap.put(gems[i], 0);
//        }
//        int total = hashmap.size();
//        
//        int left = 0;
//        int right = 0;
//        hashmap.put(gems[left], 1);
//        
//        int dis = Integer.MAX_VALUE;
//        answer[0] = 1;
//        answer[1] = 1;
//        
//        if(hashmap.size() == 1) return new int[] {1, 1};
//        
//        while(right < size-1) {
//        	right++;
//        	hashmap.put(gems[right], hashmap.get(gems[right])+1);
//        	
//        	// left의 갯수가 2개 이상이면 left++;
//        	while(hashmap.get(gems[left]) >= 2) {
//        		hashmap.put(gems[left], hashmap.get(gems[left])-1);
//        		left++;
//        	}
//        	
//        	if(!hashmap.containsValue(0)) {
//        		int d = right - left + 1;
//        		if(dis > d) {
//        			dis = d;
//        			answer[0] = left+1;
//        			answer[1] = right+1;
//        		}
//        	}
//        }
//        
//        return answer;
//		
//    }
	
	public int[] solution(String[] gems) {
        Queue<String> q = new LinkedList<>();
        HashSet<String> hs = new HashSet<>();
        HashMap<String, Integer> hm = new HashMap<>();
        int start = 0;
        int end = Integer.MAX_VALUE;
        
        for(String s : gems) {        //보석종류
            hs.add(s);
        }
        int startPoint = 0;
        for(int i = 0; i < gems.length; i++) {
            hm.put(gems[i], hm.getOrDefault(gems[i], 0) + 1);
            
            // 슬라이딩 윈도우(구매할 보석의 구간)
            q.add(gems[i]);
            
            while(true) {
                String temp = q.peek();
                //구간 내 보석이 2이상 있으면 맨 앞(왼쪽)에 있는 보석은 빼도 된다.
                // start++
                if(hm.get(temp) > 1) {
                	// 구간에서 뺸다.
                    q.poll();
                    // 시작지점++
                    start++;
                    // 보석의 갯수 -1
                    hm.put(temp, hm.get(temp) -1);
                }else {
                    break;
                }
            }
            // 보석이 모두 들어가있고, 구간의 길이가 전의 찾은 길이보다 작으면
            // 구간의 시작점을 바꾼다.
            if(hm.size() == hs.size() && end > q.size()) {
                end = q.size();
                startPoint = start;
            }
            
            
        }
        return new int[] {startPoint+1, startPoint+ end };
        
    }



}

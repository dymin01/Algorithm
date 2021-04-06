package programmers;
/**
 * <문제 요약>
 * 구해야 하는 것: 출발지 "ICN"에서 출발해서 여행지를 다 도는 경로를 구하라
 * 제약 사항: tickets의 모든 경로를 다 돌아야 한다. 가능한 경로가 여러개일 경우 알파벳 순서가 앞서는 경로를 출력한다.
 * 문제 유형: DFS/BFS
 * 요구 개념: DFS/BFS
 * 
 * <풀이법 요약>
 * 1. tickets의 경로를 오름차순으로 정렬한다. 출발지가 같은경우 도착경로로 오름차순 정렬한다.
 * 2. ICN을 출발로 하여 DFS를 통해 다음 경로를 확인한다.
 * 3. 경로를 찾으면 ArrayList에 넣는다.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class PM_L3_여행경로 {

	public static void main(String[] args) {
		
		PM_L3_여행경로 sol = new PM_L3_여행경로();
	
		String[][] tickets = {{"ICN", "A"}, {"A", "C"}, {"A", "D"}, {"D", "B"}, {"B", "A"}} ;
		//String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL","SFO"}, {"ATL", "ICN"}};
		
		String[] ans = sol.solution(tickets);
		
		for(int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}

	}
	
	static boolean[] v; 
	static ArrayList<String> ans;
	
	public String[] solution(String[][] tickets) {
        String[] answer = {};
        v = new boolean[tickets.length];
        
        // 경로를 알파벳 오름차순으로 미리 정렬한다.
        Arrays.sort(tickets, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				if(o1[0].equals(o2[0])) {
					return o1[1].compareTo(o2[1]);
				}
				return o1[0].compareTo(o2[0]);
			}
		});
        
        ans = new ArrayList<>();
        // 시작은 무조건 ICN에서 출발한다.
        ans.add("ICN");
        DFS(tickets, "ICN", 1);
        //ans.add(0, "ICN");
        answer = new String[ans.size()];
        for(int i = 0; i < ans.size(); i++) {
        	answer[i] = ans.get(i);
        }
        
        return answer;
    }
	
	public void DFS(String[][] tickets, String cur, int cnt) {
		
		// 출발지가 cur과 같고 사용하지 않은 ticket이라면 사용한다.
		for(int i = 0; i < tickets.length; i++) {
			if(!v[i] && tickets[i][0].equals(cur)) {
				v[i] = true;
				// ArrayList에 그냥 Add하면 답이 잘못나오는 경우가 있다. ex) 경로가 중간에 끊기는 경우
				// 그래서 cnt 자리에 추가하거나 DFS를 나오고 맨 앞에 추가한다.
				ans.add(cnt, tickets[i][1]);
				DFS(tickets, tickets[i][1], cnt+1);
				//ans.add(0, tickets[i][1]);
			}
		}
	}

}

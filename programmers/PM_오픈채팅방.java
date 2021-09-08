package programmers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class PM_오픈채팅방 {

	public static void main(String[] args) {
		
		PM_오픈채팅방 sol = new PM_오픈채팅방();
		
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		
		String[] answer = sol.solution(record);
		
		for(int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
		
	}
	
	static class node{
		char action;
		String id;
		public node(char action, String id) {
			super();
			this.action = action;
			this.id = id;
		}
		
	}
	
    public String[] solution(String[] record) {
        String[] answer = {};
        
        Map<String, String> list = new HashMap<String, String>();
        Queue<node> chat = new LinkedList<>();
        
        StringTokenizer st = null;
        
        for(int i = 0; i < record.length; i++) {
        	st = new StringTokenizer(record[i]);
        	
        	String action = st.nextToken();
        	String id = st.nextToken();
        	String name = "";
        	if(action.charAt(0) != 'L')
        		name = st.nextToken();
        	
        	// 입장
        	if(action.charAt(0) == 'E') {
        		chat.add(new node('E', id));
        		list.put(id, name);
        	}
        	// 이름 바꾸기
        	else if(action.charAt(0) == 'C') {
        		list.put(id, name);
        	}
        	// 나가기
        	else {
        		chat.add(new node('L', id));
        	}
        	
        }
        
        String ent = "님이 들어왔습니다.";
        String leav = "님이 나갔습니다.";
        
        int size = chat.size();
        answer = new String[size];
        for(int i = 0; i < size; i++) {
        	node cur = chat.poll();
        	char action = cur.action;
        	String id = cur.id;
        	if(action == 'E') {
        		answer[i] = list.get(id) + ent;
        	}else {
        		answer[i] = list.get(id) + leav;
        	}
        }
        
        return answer;
    }

}

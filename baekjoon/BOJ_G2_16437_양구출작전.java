package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약> 구해야 하는 것: 섬을 지나면서 1번서에 도착해 구할 수 있는 양의 수
 * 유형 : DFS, 백트레킹
 * 요구 개념 : DFS, 백트레킹
 * 
 * <풀이법 요약>
 * 1. 섬의 정보를 가지고 있는 list배열, 섬의 연결상태를 가지고 있는 map으로 구분하여 값을 저장한다.
 * 2. 1번섬에서 연결된 섬으로 끝의 섬까지 DFS를 통해 들어간다.
 * 3. 마지막 섬에서 양일경우 갯수를 그대로 반환하고, 늑대일경우 0을 반환한다.
 * 4. 다음 섬에서 넘어온 양의 수를 다 더한 후, 현재 섬의 type에 따라
 * 		양이면 그대로 더해서 반환, 늑대일경우 양의 수에서 뺀다(음수가 나올경우 0으로 반환한다.)
 * 5. 결과값 출력
 */


public class BOJ_G2_16437_양구출작전 {

	static class Island{
		char type;
		int amount;
		public Island(char type, int amount) {
			super();
			this.type = type;
			this.amount = amount;
		}
	}
	
	static int N;
	
	static Island[] list;
	static ArrayList<Integer>[] map;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		list = new Island[N+1];
		
		map = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
		
		list[1] = new Island('S', 0);
		for(int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			char type = st.nextToken().charAt(0);
			int amount = Integer.parseInt(st.nextToken());
			int pre = Integer.parseInt(st.nextToken());
			list[i] = new Island(type, amount);
			map[pre].add(i);
		}
		
		long ans = DFS(1);
		
		System.out.println(ans);
	}

	private static long DFS(int cur) {
		
		if(map[cur].size() == 0) {
			if(list[cur].type == 'S') {
				return list[cur].amount;
			}else {
				return 0;
			}
		}
		
		long res = 0L;
		
		for(int i = 0; i < map[cur].size(); i++) {
			res += DFS(map[cur].get(i));
		}
		if(list[cur].type == 'W') {
			res -= list[cur].amount;
			res = res < 0 ? 0 : res;
		}else {
			res += list[cur].amount;
		}
		return res;
		
	}

}

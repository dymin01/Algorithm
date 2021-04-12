package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_9019_DSLR {

	static class ans{
		String ans;
		int num;
		public ans(String ans, int num) {
			this.ans = ans;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			boolean[] v = new boolean[100000];
			String[] str = new String[100000];
			
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(start);
			v[start] = true;
			str[start] = "";
			
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				
				int D = (cur * 2)  > 9999 ? (cur*2)%10000 : cur*2;
				int S = cur - 1 < 0 ? 9999 : cur-1;
				int L = (cur % 1000) * 10 + (cur / 1000);
				int R = cur / 10 + (cur % 10) * 1000;
				
				
				if(!v[D]) {
					str[D] = str[cur] + "D";
					v[D] = true;
					queue.add(D);
					if(D == end) {
						System.out.println(str[D]);
						break;
					}
				}
				if(!v[S]) {
					str[S] = str[cur] + "S";
					v[S] = true;
					queue.add(S);
					if(S == end) {
						System.out.println(str[S]);
						break;
					}
				}
				if(!v[L]) {
					str[L] = str[cur] + "L";
					v[L] = true;
					queue.add(L);
					if(L == end) {
						System.out.println(str[L]);
						break;
					}
				}
				if(!v[R]) {
					str[R] = str[cur] + "R";
					v[R] = true;
					queue.add(R);
					if(R == end) {
						System.out.println(str[R]);
						break;
					}
				}
			}
		}
		

	}

}

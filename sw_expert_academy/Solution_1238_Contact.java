package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1238_Contact {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		StringTokenizer st = null;
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[101][101];
			int[] v = new int[101];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i+=2) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				map[from][to] = 1;
			}
			int ans = 0;
			
			Queue<Integer> queue = new LinkedList<>();
			queue.add(start);
			
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				for(int i = 0; i < 101; i++) {
					if(map[cur][i] == 1) {
						int next = i;
						if(v[next] == 0) {
							v[next] = v[cur] + 1;
							queue.add(next);
						}
					}
				}
			}
			
			int max = Integer.MIN_VALUE;
			for(int i = 1; i < 101; i++) {
				if(max <= v[i]) {
					max = v[i];
					ans = i;
				}
			}
			
			System.out.println("#" + t +  " " + ans);
		}
	}

}

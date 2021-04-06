package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_1219_길찾기_DFS_민동엽 {

	static boolean isfind;
	static int[][] d;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			d = new int[100][100];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				if(d[from][0] == 0) {
					d[from][0] = to;
				}else {
					d[from][1] = to;
				}
			}
			boolean[] v = new boolean[100];
			
			isfind = false;
			
			DFS(0);
			
			if(isfind) System.out.println("#" + T + " " + 1);
			else System.out.println("#" + T + " " + 0);
			
		}
	}
	
	static void DFS(int cur) {
		if(cur == 99) {
			isfind = true;
			return;
		}
		if(d[cur][0] != 0) DFS(d[cur][0]);
		if(d[cur][1] != 0) DFS(d[cur][1]);
	}
	
}

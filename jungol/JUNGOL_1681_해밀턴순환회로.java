package jungol;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUNGOL_1681_해밀턴순환회로 {

	static int[][] map;
	static boolean[] v;
	static int N;
	static int min, ans;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		v = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		ans = 0;
		v[0] = true;
		find(0, 0, 0);
		
		System.out.println(min);

	}
	
	public static void find(int cur, int cnt, int sum) {
		if(sum >= min) return;
		
		if(cnt == N-1) {
			if(map[cur][0] != 0) {
				sum += map[cur][0];
				min = Math.min(sum,  min);
			}
			return;
		}
		for(int i = 0; i < N; i++) {
			if(map[cur][i] != 0 && !v[i]) {
				v[i] = true;
				find(i, cnt+1, sum + map[cur][i]);
				v[i] = false;
			}
		}
	}
}

package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_14889_스타트와링크 {
	
	static int[][] map;
	static int[] list;
	static int ans;
	static boolean[] start;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		start = new boolean[N];
		ans = Integer.MAX_VALUE;
		
		StringTokenizer st = null;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		nCr(0, 0);
		
		System.out.println(ans);
		
	}
	
	public static void nCr(int s, int cnt) {
		
		if(cnt == N/2) {
			
			int sumS = 0;
			int sumL = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(start[i] && start[j]) {
						sumS += map[i][j];
					}else if(!start[i] && !start[j]) {
						sumL += map[i][j];
					}
				}
			}
			ans = Math.min(ans, Math.abs(sumS - sumL));
			
			return;
		}
		for(int i = s; i < N; i++) {
			start[i] = true;
			nCr(i+1, cnt+1);
			start[i] = false;
		}
	}

}

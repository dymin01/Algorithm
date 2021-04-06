package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2798_블랙잭 {

	static int N, M;
	static int[] arr;
	static int ans;
	static int[] num;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		num = new int[3];
		ans = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		nCr(0,0,0);
		System.out.println(ans);

	}
	
	public static void nCr(int cnt, int start, int sum) {
		if(sum > M) {
			return;
		}
		if(cnt == 3) {
			ans = Math.max(ans, sum);
			return;
		}
		for(int i = start; i < N; i++) {
			nCr(cnt+1, i+1, sum + arr[i]);
		}
		
	}

}

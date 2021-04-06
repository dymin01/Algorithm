package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4008_숫자만들기_np {

	static int N, max, min;
	static int[] nums;
	static int[] exps;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			nums = new int[N];
			exps = new int[N-1];
			
			v = new boolean[N-1];
			int index = 0;
			
			st = new StringTokenizer(br.readLine());
			int plus = Integer.parseInt(st.nextToken());
			for(int i = 0; i < plus; i++) {
				exps[index++] = 0;
			}
			
			int minus = Integer.parseInt(st.nextToken());
			for(int i = 0; i < minus; i++) {
				exps[index++] = 1;
			}
			
			int mult = Integer.parseInt(st.nextToken());
			for(int i = 0; i < mult; i++) {
				exps[index++] = 2;
			}
			
			int div = Integer.parseInt(st.nextToken());
			for(int i = 0; i < div; i++) {
				exps[index++] = 3;
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(exps);
			
			do {
				int res = nums[0];
				for(int i = 0; i < N-1; i++) {
					if(exps[i] == 0) {
						res += nums[i+1];
					}
					else if(exps[i] == 1) {
						res -= nums[i+1];
					}
					else if(exps[i] == 2) {
						res *= nums[i+1];
					}
					else if(exps[i] == 3) {
						res /= nums[i+1];
					}
				}
				max = Math.max(max, res);
				min = Math.min(min, res);
				
			}while(np(N-2));
			
			System.out.println("#" + t + " " + (max - min));
		}
	}
	
	public static boolean np(int size) {
		
		int i = size;
		while(i > 0 && exps[i-1] >= exps[i]) i--;
		
		if(i == 0) {
			return false;
		}
		
		int j = size;
		while(exps[i-1] >= exps[j]) j--;
		
		int temp = exps[i-1];
		exps[i-1] = exps[j];
		exps[j] = temp;
		
		int k = size;		
		while(i <= k) {
			temp = exps[i];
			exps[i] = exps[k];
			exps[k] = temp;
			i++;
			k--;
		}
		
		return true;
	}
	
}

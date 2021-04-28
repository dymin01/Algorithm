package sw_expert_academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_5607_조합 {
	
	static final int MOD = 1234567891;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			long fac[] = new long[n + 1];
			fac[0] = 1;
			for(int i = 1; i <= n; i++) fac[i] = (fac[i-1] * i) % MOD;
			
			long bottom = (fac[r] * fac[n-r]) % MOD;
			long temp = fermat(bottom, MOD - 2);
			
			System.out.println("#" + t + " " + (fac[n] * temp) % MOD);
			
		}
		
	}

	private static long fermat(long n, int x) {
		if(x == 0)
			return 1;
		
		long temp = fermat(n, x / 2);
		long res = (temp * temp) % MOD;
		
		if(x % 2 == 0)
			return res;
		else
			return (res * n) % MOD;
	}

}

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_B2_2846_오르막길 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int max;
		int min = max = Integer.parseInt(st.nextToken());
		int ans = 0;
		
		for(int i = 0; i < N-1; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num <= max) {
				int res = max - min;
				ans = Math.max(ans, res);
				min = num;
			}
			max = num;
		}
		
		int res = max - min;
		ans = Math.max(ans, res);
		
		System.out.println(ans);
		
		
	}

}

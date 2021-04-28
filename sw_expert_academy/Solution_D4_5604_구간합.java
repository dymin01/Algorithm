package sw_expert_academy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_5604_구간합 {
	static long number[], result,start, end, mul;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= test; t++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			start = Long.parseLong(st.nextToken());
			end = Long.parseLong(st.nextToken());
			// 초기화
			number = new long[10];
			result = 0;
			mul = 1;
			if(start==0) start=1;
			while (start <= end) {
				while(start%10!=0 && start<=end) {
					cal(start);
					start++;
				}
				if(start>end) break;
				while(end%10!=9 && start<=end) {
					cal(end);
					end--;
				}
				long diff = end/10 - start/10 +1;
				for(int i=0;i<10;i++)
					number[i]+=diff*mul;
				mul*=10;
				start/=10;
				end/=10;
			}		
			for (int i = 1; i < 10; i++)
				result += (i * number[i]);
			System.out.println("#" + t + " " + result); 
		}
	}
	
	public static void cal(long a) {
		for (long i = a; i > 0; i /= 10) {
			String s = Long.toString(i);
			int t = s.charAt(s.length()-1)-'0';
			number[t]+=mul;				
		}
	}
	
}
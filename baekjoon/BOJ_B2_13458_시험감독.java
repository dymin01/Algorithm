package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Math.ceil()
// 입력값(double)보다 크거나 같은 가장 작은 정수.
// ex) Math.ceil(11.4) -> 12





public class BOJ_B2_13458_시험감독 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long answer = 0;
		
		for(int i = 0; i < N; i++) {
			int temp = A[i]-B;
			answer++;
			if(temp <= 0) continue;
			
			if(temp < C) answer++;
			else {
				answer += (temp / C);
				if(temp % C > 0) answer++;
			}
		}
		
		// 대단한 코드야...
//		for(int a : A) {
//			
//			if(a <= B) {
//				answer++;
//			}else {
//				answer += (Math.ceil((double)(a-B)/C) + 1);
//			}
//		}
		
		
		System.out.println(answer);
	}

}

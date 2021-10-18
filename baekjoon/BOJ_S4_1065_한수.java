package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S4_1065_한수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if(N < 100) {
			System.out.println(N);
		}else {
			int answer = 99;
			
			for(int i = 100; i <= N; i++) {
				if(isHan(i)) {
					answer++;
				}
			}
			
			if(N == 1000) answer--;
			
			System.out.println(answer);
		}

	}

	private static boolean isHan(int i) {
		int A = i / 100 % 10;
		int B = i / 10 % 10;
		int C = i % 10;
		
		if(2 * B == A + C) {
			return true;
		}
		
		return false;
	}

}

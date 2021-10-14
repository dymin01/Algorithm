package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1169_주사위던지기1 {

	static int[] target;
	static boolean[] V;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		target = new int[N];
		V = new boolean[7];
		
		if(M == 1) {
			mode1(0, N);
		}
		else if(M == 2) {
			mode2(0, 1, N);
		}
		else {
			mode3(0, N);
		}
		
	}
	private static void mode3(int cnt, int n) {
		if(cnt == n) {
			for(int i = 0; i < n; i++) {
				System.out.print(target[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 1; i <= 6; i++) {
			if(V[i]) continue;
			V[i] = true;
			target[cnt] = i;
			mode3(cnt+1, n);
			V[i] = false;
		}
		
	}
	private static void mode2(int cnt, int start, int n) {
		
		if(cnt == n) {
			for(int i = 0; i < n; i++) {
				System.out.print(target[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = start; i <= 6; i++) {
			target[cnt] = i;
			mode2(cnt+1, i, n);
		}
		
		
	}
	private static void mode1(int cnt, int n) {
		if(cnt == n) {
			for(int i = 0; i < n; i++) {
				System.out.print(target[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 1; i <= 6; i++) {
			target[cnt] = i;
			mode1(cnt+1, n);
		}
		
	}

}

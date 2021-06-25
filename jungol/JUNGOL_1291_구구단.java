package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUNGOL_1291_구구단 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		while(true) {
			if(s < 2 || s > 9 || e < 2 || e > 9 ) {
				System.out.println("INPUT ERROR!");
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
			}
			else {
				break;
			}
		}
		
		
		if (s < e) {
			for (int j = 1; j <= 9; j++) {
				for (int i = s; i <= e; i++) {
					System.out.printf("%d * %d = %2d", i, j, i*j);
					System.out.print("   ");
				}
				System.out.println();
			}
		} else {
			for (int j = 1; j <= 9; j++) {
				for (int i = s; i >= e; i--) {
					System.out.printf("%d * %d = %2d", i, j, i*j);
					System.out.print("   ");
				}
				System.out.println();
			}

		}

	}

}

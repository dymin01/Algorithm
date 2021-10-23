package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_17779_게리맨더링2 {

	static int N;

	static int[][] A;

	static int answer; 

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());

		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 987654321;
		// 왼쪽
		int d1 = 1;
		// 오른쪽
		int d2 = 1;
		
		// d1
		for (d1 = 1; d1 < N; d1++) {
			// d2
			for (d2 = 1; d2 < N; d2++) {
				
				if(d1 + d2 > N-1) break;

				for (int r = 0; r < N; r++) {

					if (r + d1 + d2 >= N)
						break;
					
					for (int c = 1; c < N; c++) {
						// 기준 위 꼭짓점
						// 범위
						if (c - d1 < 0 || c + d2 >= N)
							continue;

						count(r, c, d1, d2);
//						System.out.println("범위나누기");
					}
				}
			}
		}
		
		System.out.println(answer);
	}

	private static void count(int r, int c, int d1, int d2) {

		
		int[] location = new int[6];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 5번 구역인지
				if(isBound(r, c, d1, d2, i, j)) {
					location[5] += A[i][j];
//					System.out.print(5 + " ");
				}
				// 1번 구역
				else if(i < (r + d1) && j <= c) {
					location[1] += A[i][j];
//					System.out.print(1 + " ");
				}
				// 2번 구역
				else if(i <= (r + d2) && c < j) {
					location[2] += A[i][j];
//					System.out.print(2 + " ");
				}
				// 3번 구역
				else if((r + d1) <= i && j < (c - d1 + d2)) {
					location[3] += A[i][j];
//					System.out.print(3 + " ");
				}
				// 4번 구역
				else if(r + d2 < i && (c - d1 + d2) <= j) {
					location[4] += A[i][j];
//					System.out.print(4 + " ");
				}
			}
//			System.out.println();
		}
		
		int min = 987654321;
		int max = 0;
		
		for(int i = 1; i <= 5; i++) {
			
			min = Math.min(location[i], min);
			max = Math.max(location[i], max);
		}
		
		answer = Math.min(answer, max - min);

	}

	private static boolean isBound(int r, int c, int d1, int d2, int i, int j) {
		
		int lr = r + d1;
		int lc = c - d1;
		int rr = r + d2;
		int rc = c + d2;
		int dr = r + d1 + d2;
		int dc = c - d1 + d2;
		
		int aa = lr + lc;
		int bb = r - c;
		int cc = rr + rc;
		int dd = dr - dc;
		
		// 5번이다.
		if(i + j - aa >= 0 && i + j - cc <= 0 && i - j - bb >= 0 && i - j - dd <= 0) {
			return true;
		}
		
		return false;
	}


}

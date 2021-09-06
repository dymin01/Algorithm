package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - N번째 방까지 몇개의 방을 지나가는지 구하라
 * 
 * 문제 유형
 * - 수학
 * 
 * 제약 사항
 * 1 <= N <= 1,000,000,000
 * 
 * <풀이 요약>
 * 
 * 
 */

public class BOJ_B2_2292_벌집 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 겹 수
		int count = 1;
		// 시작방과 끝방의 범위
		int range = 2;
		
		if(N == 1) {
			System.out.println(1);
		}
		else {
			while(range <= N) {
				range = range + (6 * count);
				count++;
			}
			System.out.println(count);
		}

	}

}

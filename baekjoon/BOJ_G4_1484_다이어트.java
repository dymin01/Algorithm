package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * 가능한 성원이으이 현재 몸무게를 오름차순으로 출력하라.
 * 
 * 문제 유형
 * - 구현?
 * - 투포인터
 * 
 * 제약 사항
 * G는 1000,000보다 작거나 같은 자연수이다.
 * 
 * <풀이 요약>
 * 1. 지난 몸무게와 현재 몸무게를 포인터로 놓는다.
 * 2. 제곱의 차가 G보다 작으면 현재 몸무게를 늘리고, 제곱의 차가 G보다 크면 과거 몸무게를 늘린다.
 * 3. 제곱의 차가 G와 같다면 출력한다.
 * 4. 만약 출력할값이 없으면 -1을 출력한다.
 * 
 * 
 */

public class BOJ_G4_1484_다이어트 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int G = Integer.parseInt(br.readLine());
		
		int before = 1;
		int after = 1;
	
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		
		while(true) {
			
			long before2 = (long)Math.pow(before, 2);
			long after2 = (long)Math.pow(after, 2);
			
			long res = after2 - before2;
			
			if(after - before == 1 && res > 100000) break;
			
			if(res == G) {
				sb.append(after).append("\n");
				flag = true;
			}
			
			if(res >= G) {
				before++;
			}else {
				after++;
			}
			
		}
		
		if(!flag)
			System.out.println(-1);
		else
			System.out.println(sb.toString());
		

	}

}

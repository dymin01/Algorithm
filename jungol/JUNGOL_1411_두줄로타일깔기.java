package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 
 * <문제 요약>
 * 문제 정의 : 2종류의 타일을 이용하여 가로2, 세로N사이즈의 판을 타일로 채우는 방법의 수 구하기
 * 문제 유형 : DP
 * 제약 사항 : 2*2, 2*1 두가지 사이즈의 타일을 사용할 수 있다.
 * 			1 <= N <= 100,000
 * 			결과를 2010529로 나눈 나머지를 출력한다.
 * <풀이 요약>
 * 
 * 
 * 
 */

public class JUNGOL_1411_두줄로타일깔기 {

	static int DP[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		DP = new int[100001];
		DP[1] = 1;
		DP[2] = 3;
		
		for(int i = 1; i <= N; i++) {
			DP[i] = go(i);
		}
		
		System.out.println(DP[N]);
		
	}

	private static int go(int i) {
		
		if(i == 1) {
			return 1;
		}
		else if(i == 2) {
			return 3;
		}
		
		if(DP[i] != 0) {
			return DP[i];
		}
		
		DP[i] = (DP[i-1] + DP[i-2]*2) % 2010529;
		
		return DP[i];
		
	}

}

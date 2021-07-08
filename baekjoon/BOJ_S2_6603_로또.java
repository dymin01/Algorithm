package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - S와 K가 주어졌을때, 수를 고를 수 있는 모든 방법을 구하라
 * 
 * 문제 유형
 * - NP
 * - 재귀
 * 
 * 제약 사항
 * - 6 < k < 13
 * 
 * <풀이 요약>
 * 1. NP로 0과 1로 출력할것 출력하지 않을것으로 나눠서 flag를 만든다.
 * 2. flag가 1인 것은 출력하지않는다.
 * 
 * 재귀로 해도 된다....
 * 
 */

public class BOJ_S2_6603_로또 {

	static int[] flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int k = Integer.parseInt(st.nextToken());
			
			if(k == 0) break;
			
			int[] number = new int[k];
			
			for(int i = 0; i < k; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			
			
			flag = new int[k];
			
			Arrays.fill(flag, 1);
			
			for(int i = 0; i < 6; i++) {
				flag[i] = 0;
			}
			
			StringBuilder sb = new StringBuilder();
			
			do {
				for(int i = 0; i < k; i++) {
					if(flag[i] == 0) {
						sb.append(number[i]).append(" ");
					}
				}
				sb.append("\n");
				
			}while(NP(k-1));
			
			System.out.println(sb.toString());
			
		}

	}

	private static boolean NP(int size) {
		
		int i = size;
		while(i > 0 && flag[i-1] >= flag[i]) i--;
		
		if(i == 0) return false;
		
		int j = size;
		while(flag[i-1] >= flag[j]) j--;
		int temp = flag[i-1];
		flag[i-1] = flag[j];
		flag[j] = temp;
		
		int k = size;
		while(i < k) {
			temp = flag[i];
			flag[i] = flag[k];
			flag[k] = temp;
			i++;
			k--;
		}
		return true;
	}

}

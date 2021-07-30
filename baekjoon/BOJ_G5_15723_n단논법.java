package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/***
 * <문제 요약>
 * 문제 정의
 * - N단 논법에
 * 
 * 문제 유형
 * - 플로이드 와샬
 * 
 * 제약 사항
 * 2 <= n <= 26
 *
 * <풀이 요약>
 * 1. 인접행렬을 만든다. 큰 값으로 채우고, 자기자신은 1로 넣는다. (boolean 형식으로 만들어도 된다.)
 * 2. 플로이드 와샬 알고리즘을 돌린다. i->k 와 k->j 둘다 모두 1이면 i -> j 도 1이다.
 * 
 * 3. 질문을 배열에 검색해 정답을 찾는다.
 * 
 * 
 */

public class BOJ_G5_15723_n단논법 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
//		int[][] map = new int[26][26];
		boolean[][] map = new boolean[26][26];
		
		for(int i = 0; i < 26; i++) {
//			Arrays.fill(map[i], 100);
			map[i][i] = true;
		}
		
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			int front = str.charAt(0) - 'a';
			int back = str.charAt(str.length() - 1) - 'a';
			
//			map[front][back] = 1;
			map[front][back] = true;
		}
		
		for(int k = 0; k < 26; k++) {
			for(int i = 0; i < 26; i++) {
				for(int j = 0; j < 26; j++) {
//					if(map[i][k] == 1 && map[k][j] == 1) {
//						map[i][j] = 1;
//					}
					if(map[i][k] && map[k][j]) {
						map[i][j] = true;
					}
				}
			}
		}
		
		StringBuilder answer = new StringBuilder();
		
		int m = Integer.parseInt(br.readLine());
		for(int i = 0; i < m; i++) {
			String str = br.readLine();
			int front = str.charAt(0) - 'a';
			int back = str.charAt(str.length() - 1) - 'a';
			
//			if(map[front][back] == 1) {
//				System.out.println("T");
//			}else {
//				System.out.println("F");
//			}
			
			if(map[front][back]) {
				System.out.println("T");
			} else {
				System.out.println("F");
			}
		}
		
	}

}

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 맨 위에 피자의 깊이를 구하라
 * 
 * 문제 유형
 * - 이분탐색
 * 
 * 제약 사항
 * - 1 <= D, N <= 30,000
 * 
 * <풀이 요약>
 * 우선순위 큐로 풀어볼라 하다가 마해서 이분탐색으로 다시 했다...
 * 
 * 1. 오븐을 입력받고 오븐에 넣을 수 있는 사이즈로 오븐의 크기를 다시 초기화한다.
 * 2. 2분탐색을 해 피자를 넣을 수 있는곳을 찾는다.
 * 3. 2분탐색을 할때 피자를 놓으면 2분탐색의 범위를 0부터 피자를 넣을곳 위까지로 바꾼다.
 * 
 */

public class BOJ_G5_1756_피자굽기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int D = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] oven = new int[D+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < D; i++) {
			oven[i] = Integer.parseInt(st.nextToken());
		}
		
		// 넣을 수 있는 사이즈로 바꾸기
		for(int i = 1; i < D; i++) {
			if(oven[i] > oven[i-1]) {
				oven[i] = oven[i-1];
			}
		}
		
		int bottom = D-1;
		int min = 987654321;
		int top = 0;
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int pizza = Integer.parseInt(st.nextToken());
			int res = -1;
			top = 0;
			while(top <= bottom) {
				int mid = (top + bottom) /2;
				if(oven[mid] >= pizza) {
					res = mid;
					top = mid+1;
				}else {
					bottom = mid-1;
				}
			}
			min = Math.min(min, res);
			bottom = res-1;
		}
		
		System.out.println(min + 1);
	}

}

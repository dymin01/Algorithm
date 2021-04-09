package baekjoon;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 연산자를 이용하여 최대값, 최소값을 구해라
 * 문제 핵심 요약 : 
 * <풀이법 요약> 
 * 1. 연산자별로 갯수를 저장한다.
 * 2. DFS를 이용하여 연산자의 갯수가 있으면 연산자를 이용하여 계산한다.
 * 3. N개의 연산이 끝나면 최대값 최소값을 비교하여 값을 구한다.
 */

import java.util.Scanner;

public class BOJ_S1_14888_연산자끼워넣기 {
	
	static int N;

	static int min, max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		int[] num = new int[N];
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;

		// 숫자 저장
		for(int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}
		// 연산자 저장
		int oper[] = new int[4];
		for(int i = 0; i < 4; i++) {
			oper[i] = sc.nextInt();
		}
		//	숫자배열, 연산자 배열, cnt, 계산한 값
		// sum에 시작을 num[0]로 하는 이유는 숫자 뒤에 연산자가 나와야 하기 때문에.
		DFS(num, oper, 1, num[0]);
		
		System.out.println(max);
		System.out.println(min);
		
	}
	
	public static void DFS(int[] num, int[] oper, int cnt, int sum) {
		
		if(cnt == N) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}
		// 더하기가 있는경우
		if(oper[0] > 0) {
			oper[0]--;
			DFS(num, oper, cnt+1, sum+num[cnt]);
			oper[0]++;
		}
		// 빼기가 있는경우
		if(oper[1] > 0) {
			oper[1]--;
			DFS(num, oper, cnt+1, sum-num[cnt]);
			oper[1]++;
		}
		// 곱하기가 있는경우
		if(oper[2] > 0) {
			oper[2]--;
			DFS(num, oper, cnt+1, sum*num[cnt]);
			oper[2]++;
		}
		// 나누기가 있는경우
		if(oper[3] > 0) {
			oper[3]--;
			DFS(num, oper, cnt+1, sum/num[cnt]);
			oper[3]++;
		}
		
	}

}

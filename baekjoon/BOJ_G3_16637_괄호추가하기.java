package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <문제 요약>
 * 구해야 하는 것: 괄호를 추가하여 가장 큰 값을 구하라
 * 제약 사항: 괄호안에는 하나의 연산자만 들어갈 수 있다. 괄호안에 중복으로 괄호가 들어갈 수 없다.
 * 문제 유형: DFS, 브로트포스
 * 요구 개념: DFS, 브루트포스
 * 
 * <풀이법 요약>
 * 1. 숫자와 연산자를 따로 입력 받는다.
 * 2. 재귀함수를 이용하여 괄호 없이 계산하는 경우, 괄호를 하는 경우로 나누어서 계산한다.
 * - 괄호없이 계산할 경우 자신과 다음 숫자를 계산한다.
 * - 괄호를 통해 계산할 경우 1+2+3 1번에서 2와 3을 괄호로 묶고 1과 계산한다.
 * 3. 끝까지 계산이 종료되면 가장 큰 값을 저장한다.
 * 
 */



public class BOJ_G3_16637_괄호추가하기 {
	
	static ArrayList<Integer> nums;
	static ArrayList<Character> oper;
	
	static long ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		String str = sc.next();
		
		nums = new ArrayList<>();
		oper = new ArrayList<>();
		ans = Integer.MIN_VALUE;
		
		for(int i = 0; i < N; i++) {
			if(i % 2 == 0) {
				nums.add(str.charAt(i) - '0');
			}
			else {
				oper.add(str.charAt(i));
			}
		}
		
		DFS((long)nums.get(0), 0);
		
		System.out.println(ans);
		
	}

	private static void DFS(long result, int idx) {
		
		if(idx >= oper.size()) {
			ans = Math.max(ans, result);
			return;
		}
		
		// 그냥 계산
		DFS(cal(oper.get(idx), result, nums.get(idx+1)), idx+1);
		// 괄호를 치고 계산
		if(idx + 1 < oper.size()) {
			// 괄호로 묶은 값
			long temp = cal(oper.get(idx+1), nums.get(idx+1), nums.get(idx+2));
			
			DFS(cal(oper.get(idx), result, temp), idx+2);
		}
	}

	private static long cal(char op, long res, long num) {
		
		if(op == '+') {
			return res + num;
		}else if(op == '-') {
			return res - num;
		}else {
			return res * num;
		}
		
	}
	
	

}

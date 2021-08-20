package baekjoon;

import java.awt.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 용액을 찾으시요
 * 
 * 문제 유형
 * - 투포인터
 * 
 * 제약 사항
 * 2 <= N <= 100,000
 * -1,000,000,000 <= 용액 <= 1,000,000,000
 * 
 * <풀이 요약>
 * 전형적인 투포인터 문제
 * 
 * 1. 용액을 배열로 저장한다.
 * 2. left = 0, right = N-1로 두고 시작한다.
 * 3. 두 용액을 더한 값의 절대값이 최소값보다 작으면 정답 용액을 바꾼다.
 * 4. 두 용액을 더한 값이 0보다 작으면 -용액을 --> 방향 용액으로 바꾼다. (left++)
 * 5. 두 용액을 더한 값이 0보다 크면 +용액을 <-- 방향 용액으로 바꾼다.(right--)
 * 6. 살아남은 두 용액을 출력한다.
 * 
 */

public class BOJ_G5_2467_용액 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] list = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = N-1;
		
		int answerLeft = 0;
		int answerRight = N-1;
		
		long min = Integer.MAX_VALUE;
		
		while(left < right) {
			long sum = list[left] + list[right];
			
			if(Math.abs(sum) < min) {
				answerLeft = list[left];
				answerRight = list[right];
				min = Math.abs(sum);
			}
			
			if(sum < 0) {
				left++;
			}else {
				right--;
			}
		}
		
		System.out.println(answerLeft + " " + answerRight);

	}

}

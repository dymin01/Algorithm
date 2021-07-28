package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * <문제 요약>
 * 문제 정의
 * - 특성값이 0에 가까운 용액을 만드는 두 용액을 찾아라
 * 
 * 문제 유형
 * - 투포인터
 * 
 * 제약 사항
 * 2 <= N <= 100,000
 * -1,000,000,000 <= <= 1,000,000,000
 *
 * <풀이 요약>
 * 
 * 단순 완전탐색을 하면 시간초과 날것 같다.
 * 나올 수 있는 가장 큰 값이 2,000,000,000 이다. 확인 잘 합시다.
 * 
 * 1. 입력받은 용액을 정렬한다.
 * 2. 작은값과 큰 값에서 출발해서 0에 가까운 값을 찾는다.
 * 3. 더한 값이 0보다 작으면 왼쪽의 인덱스를 ++ 하고
 * 4. 더한 값이 0보다 작으면 오늘쪽의 인덱스를 -- 한다. 
 * 5. 가장 0에 가깝게 나온 두 용액을 출력한다.
 * 
 */

public class BOJ_G5_2470_두용액 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] liquids = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			liquids[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(liquids);
		
		int ansLeft = 0;
		int ansRight = 0;
		
		int left = 0;
		int right = N-1;
		int max = Integer.MAX_VALUE;
		
		while(left < right) {
			
			int mixedLiquid = liquids[left] + liquids[right];
			
			if(Math.abs(mixedLiquid) < max) {
				ansLeft = liquids[left];
				ansRight = liquids[right];
				max = Math.abs(mixedLiquid);
			}
			
			if(mixedLiquid < 0) {
				left++;
			}else {
				right--;
			}
			
		}
		
		System.out.println(ansLeft + " " + ansRight);

	}

}

package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 회전초밥을 연속으로 먹을때 가장 많은 종류를 먹는 경우의 수
 * 제약 사항: 쿠폰이 있다. 배열의 길이가 3000000이다.
 * 문제 유형: 투포인터, 슬라이딩 윈도우 라고 한다.
 * 요구 개념: 투포인터
 * 
 * <풀이법 요약>
 * 기존 : map을 이용하여 중복을 체크하였다.
 * 		시간초과가 뜬다....map을 탐색하고 get으로 받아오고 하는 것이 시간이 오래 걸리는것 같다. map말고 그냥 배열로 해야 할것 같다.
 * 
 * 1. 회전초밥 배열을 입력한다. 이때 k개만큼 선택한 초밥으로 넣는다. 넣으면서 중복이 아닐경우의 개수를 구한다.
 * 2. left = 0, right = k로 해서 하나씩 늘려가면(슬라이딩 윈도우, 투포인터)방식으로 초밥을 하나씩 넣고 뺀다.
 * 3. 선택한 초밥의 종류가 기존의 종류보다 많거나 같으면
 * 		쿠폰의 초밥이 포함되어있지 않으면 +1과 비교, 포함되어있으면 그냥 비교해서 큰값을 넣는다.
 * 4. right 인덱스가 k가 되면 종료한다.	
 * 
 */

public class JUNGOL_2577_회전초밥_고_재도전 {

	static int N, d, k, c;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int[] belt = new int[N+1];
		int[] sushi = new int[3001];
		
		//
		int maxKind = 0;
		
		for(int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
			if(i < k && sushi[belt[i]]++ == 0) {
				maxKind++;
			}
		}
		
		// 빠질것
		int left = 0;
		// 새로 추가될 것
		int right = k;

		int kind = maxKind;
		
		do{
			
			if(maxKind == d) break;

			if(kind >= maxKind) {
				if(sushi[c] == 0) {
					maxKind = Math.max(maxKind, kind+1);
				}else {
					maxKind = Math.max(maxKind, kind);
				}
			}
			
			//새로운것 추가
			if(sushi[belt[right]]++ == 0) {
				kind++;
			}
			// 왼쪽거 제거
			if(--sushi[belt[left]] == 0) {
				kind--;
			}
			
			left++;
			right = (right + 1)%N;
			
			
		}while(right != k);
		
		System.out.println(maxKind);
		
	}

}

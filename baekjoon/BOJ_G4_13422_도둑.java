package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 도둑이 도둑질할 수 있는 집의 경우의 수
 * 
 * 문제 유형
 * - 슬라이딩 윈도우
 * - 투 포인터
 * 
 * 제약 사항
 * 1 <= N <= 100,000
 * 1 <= M <= N
 * 1 <= K <= 1,000,000,000
 *  
 * <풀이 요약>
 * 1. 집에 돈을 입력 받을때 0~M 까지 더해 sum을 구한다.
 * 1-2. N과 M이 같을경우 도둑질할 수 있는 집의 경우의 수가 1가지밖에 없다.
 * 2. left와 right 인덱스틑 정한다.
 * 3. 1씩 증가시키며 sum에 left를 빼고 right를 넣는다.
 * 4. sum이 K보다 작을경우 answer을 1 증가시킨다.
 * 
 * 
 * N과 M이 같은경우를 놓침....
 * 
 * 
 */

public class BOJ_G4_13422_도둑 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[] house = new int[N];
			int sum = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				house[i] = Integer.parseInt(st.nextToken());
				if(i < M) sum += house[i];
			}	
			
			int left = 0;
			int right = M;
			
			// 집의 개수와 도둑질할 집의 개수가 같으면
			if(N == M) {
				if(sum < K) {
					System.out.println(1);
				}else {
					System.out.println(0);
				}
			}
			else {
				int answer = 0;
				
				while(left < N) {
					if(sum < K) answer++;
					
					sum -= house[left++];
					sum += house[right++];
					
					right = right == N ? 0 : right;
					
				}
				
				System.out.println(answer);
				
			}
			
		}

	}

}

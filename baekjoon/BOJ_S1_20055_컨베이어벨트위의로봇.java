package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 몇번째 단계가 진행중일때 종료되었는지 구한다.
 * 
 * 문제 유형
 * - 구현
 * 
 * 제약 사항
 * 2 ≤ N ≤ 100
 * 1 ≤ K ≤ 2N
 * 1 ≤ Ai ≤ 1,000
 * 
 * <풀이 요약>
 * 
 * 
 */

public class BOJ_S1_20055_컨베이어벨트위의로봇 {

	
	static int N, K;
	
	static int[] belt;
	static boolean[] isRobot;
	
	static int zero = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt = new int[N*2];
		isRobot = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N*2; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		int zero = 0;
		int ans = 0;
		while(zero < K) {
			// 1. 벨트 한칸 회전
			int temp = belt[belt.length-1];
			for(int i = belt.length-1; i > 0; i--) {
				belt[i] = belt[i-1];
			}
			belt[0] = temp;
			
			// 1. 로봇이동
			for(int i = N-1; i > 0; i--) {
				isRobot[i] = isRobot[i-1];
			}
			isRobot[0] = false;
			// 로봇 빼기
			isRobot[N-1] = false;
			
			// 2. 로봇 한칸 이동
			for(int i = N-1; i > 0; i--) {
				if(isRobot[i-1] && !isRobot[i] && belt[i] >= 1) {
					isRobot[i] = true;
					isRobot[i-1] = false;
					belt[i]--;
					if(belt[i] == 0) zero++;
				}
			}
			// 3. 올라가는 위치에 로봇 올리기
			if(belt[0] > 0) {
				isRobot[0] = true;
				belt[0]--;
				if(belt[0] == 0) zero++;
			}
			
			ans++;
		}
		
		System.out.println(ans);
		

	}

}

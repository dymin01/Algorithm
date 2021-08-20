package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * 이 게임에서 얻은 수 있는 총 점수의 최댓값을 구하여라
 * 
 * 문제 유형
 * - DP?
 * 
 * 제약 사항
 * 1. 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
 * 2. 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
 * 3. 마지막 도착 계단은 반드시 밟아야 한다.
 * 계단의 개수는 300개 이하
 * 점수는 10,000이하의 자연수
 * 
 * <풀이 요약>
 * DP는 규칙찾는게 귀찮...
 * 
 * 1. DP를 2차원으로 만든다. 1칸 전에서 온것, 2칸 전에서 온것으로 구분한다.
 * 2. DP에서 1칸 전에서 온 값은 전 계단의 2칸전에서 온것 + 지금계단
 * 3. DP에서 2칸 전에서 온 값은 -2 계단에서 1칸전 값과, 2칸전 값중 큰 값을 선택한다.
 * 4. 마지막 계단까지 구했다면 최댓값을 출력한다.
 * 
 */


public class BOJ_S3_2579_계단오르기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] step = new int[N+1];
		
		// 0 : 1칸 전
		// 1 : 2칸 전
		int[][] DP = new int[N+1][2];
		
		for(int i = 1; i <= N; i++) {
			step[i] = Integer.parseInt(br.readLine());
		}
		
		// 0번쩨
		DP[0][0] = DP[0][1] = 0;
		// 1번째 개단
		DP[1][0] = DP[1][1] = step[1];
		
		for(int i = 2; i <= N; i++) {
			
			//1칸 전
			DP[i][0] = DP[i-1][1] + step[i];
			
			//2칸 전
			DP[i][1] = Math.max(DP[i-2][0], DP[i-2][1]) + step[i];
			
		}
		
		System.out.println(Math.max(DP[N][0], DP[N][1]));

	}

}

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <문제 요약> 구해야 하는 것: 파괴하는 장애물의 최솟값과 그러한 구간의 수
 * 유형 : 구간합
 * 요구 개념 : 구간합 
 * 
 * <풀이법 요약>
 * 시간초과가 많이 나고, 효율성 찾는게 어려운 문제....접근법을 찾기가 너무 머리아픔....
 * 
 * 1. 석순과 종유석을 나눠서 저장할때 그 높이의 갯수를 구함. 즉 1의 높이의 갯수가 배열의 [1]의 값
 * 2. 높이를 돌면서 H높이 이하의 갯수를 더해서 배열을 만듬 즉, 배열의 값은 배열의 인덱스 이하의 종유석, 석순의 갯수 
 * 3. 높이를 돌면서 각각의 높이에서 파괴시켜야 하는 석순, 종유석의 갯수를 구함
 * 3-1. 석순(바닥)은 전체 석순의 개수 - (전체-높이) 이하의 총합
 * 3-2. 종유석(탑)은 전체 종유석의 개수 - 높이-1 이하의 총합
 *  
 */

public class BOJ_G5_3020_개똥벌레 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[H][N];
		
		int[] bot = new int[H+1];
		int[] top = new int[H+1];
		
		for(int i = 0; i < N/2; i++) {
			bot[Integer.parseInt(br.readLine())]++;
			top[Integer.parseInt(br.readLine())]++;
		}
		
		int[] botSum = new int[H+1];
		int[] topSum = new int[H+1];
		
		for(int i = 1; i <= H; i++) {
			// i높이 이하의 석순의 개수
			botSum[i] = botSum[i-1] + bot[i];
			// i높이 이하의 종유석의 개수
			topSum[i] = topSum[i-1] + top[i];
		}
		
		int min = Integer.MAX_VALUE;
		int ans = 0;
		
		// 높이별로 돌면서
		for(int i = 1; i <= H; i++) {
			
			int crush = 0;
			// 파괴해야 하는 석순, 종유석의 갯수
			// 전체 석순 - i높이보다 작은 석순
			crush += botSum[H] - botSum[H-i];
			// 전체 종유석 - i높이보다 작은 종유석
			crush += topSum[H] - topSum[i-1];
			
			if(min > crush) {
				min = crush;
				ans = 1;
			}else if (min == crush) {
				ans++;
			}
		}
		System.out.println(min + " " + ans);

	}

}

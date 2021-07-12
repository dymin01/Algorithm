package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 상근이가 가지고 있는 숫자 카드인지 아닌지를 구하라
 * 
 * 문제 유형
 * - 이분탐색
 * 
 * 제약 사항
 * 1 <= N <= 500,000
 * 1 <= M <= 500,000
 * -10,000,000 <= 숫자 <= 10,000,000
 * 
 * 
 * <풀이 요약>
 * Hashmap 사용한 풀이랑
 * 이분탐색 이용한 풀이
 * 
 * Haspmap 풀이
 * 1. card를 입력받을때 Hashmap에 넣는다.
 * 2. 찾을 숫자가 Hashmap에 있으면 1을 출력하고 없으면 0을 출력한다.
 * 
 * 이분탐색 풀이
 * 1. card를 입력받아 정렬한다.
 * 2. 찾아야 할 숫자를 이분탐색을 이용해 card 안에 있는지 찾는다.
 * 	2-1 찾아야 할 숫자와 mid와 비교한다. 숫자가 같으면 찾은것
 *  2-2 찾아야 할 숫자가 작으면 왼쪽 범위로 줄이고
 *  2-3 찾아야 할 숫자가 크면 오른쪽으로 범위를 줄인다.
 * 
 */


public class BOJ_S4_10815_숫자카드_Hashmap {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		HashMap<Integer, Integer> map = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(!map.containsKey(num)) {
				map.put(num, 1);
			}
		}
		
		int numOfCard = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < numOfCard; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(map.containsKey(num)) {
				sb.append(1).append(" ");
			}else {
				sb.append(0).append(" ");
			}
		}
		
		System.out.println(sb.toString());

	}
}
	

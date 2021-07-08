package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - N개의 카드 묶음을 합치기 위해 최소 몇번의 비교가 필요한지 구라하
 * 
 * 문제 유형
 * - 그리디
 * 
 * 제약 사항
 *  1 <= N <= 100,000
 *  카드 묶음의 크기는 1,000 보다 작은 양의 정수
 *  
 * <풀이 요약>
 * 최소값 2개를 꺼내 더한다!!
 * 처음에 그냥 배열로 하다가 뭔가 안맞았다...
 * 앞에꺼 2개를 더했을때 뒤에꺼보다 클 수 있다.... 최소값으로만 2개 골라야 한다. 그래서 우선순위 큐를 썼다.
 * 
 * 1. 입력을 우선순위 큐로 받는다.
 * 2. 큐에서 가장 작은 2개의 수를 더한값을 answer에 더한다.
 * 3. 2개의 수를 더한값을 다시 우선순위 큐에 넣는다.
 * 4. 2,3 순서를 우선순위 큐의 크기가 1이 될때까지 반복한다.
 * 
 */


public class BOJ_G4_1715_카드정렬하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		int answer = 0;
		
		while(pq.size() > 1) {
			int a = pq.poll();
			int b = pq.poll();
			
			answer += a + b;
			
			pq.add(a + b);
		}
		
		System.out.println(answer);

	}

}

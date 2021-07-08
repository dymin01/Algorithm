package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 최소의 강의실을 사용해서 모든 수업이 가능하도록 하는 강의실의 개수를 구하라
 * 
 * 문제 유형
 * - 그리디
 * 
 * 제약 사항
 * - 1 <= N <= 200,000
 *  1 <= Si, < Ti <= 10^9
 *  
 * <풀이 요약>
 * 정말 이해가 안돼서 구선생님의 도움을 좀 받았습니다.
 * 
 * 1. 강의의 시작시간 오름차순 정렬한다.
 * 2. 첫 강의의 끝나는 시간을 우선순위 큐에 넣는다. - 우선순위 큐의 사이즈는 필요한 강의실의 수이다.
 * 3. 남은 강의들을 순회하면서
 * 	3-1 지금 가장 빨리 끝나는(우선순위 큐에 들어가 있는 peek) 종료시간보다 다음 강의의 시작 시간이 빠르면 강의실이 하나 더 필요하다. --> 우선순위 큐에 종료시간을 넣는다.
 *  3-2 가장 빨리 끝나는 강의보다 늦게 시작하면 강의를 종료 후 강의를 시작하면 된다. 우선숭위큐에 peek값을 poll하고 다음강의 종료시간을 넣는다.
 * 4. 모든 강의를 순회하고 우선순위큐에 남아있는 강의의 수가 필요한 강의실의 개수이다.
 * 
 * 
 */

public class BOJ_G5_11000_강의실배정 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// start
			arr[i][0] = Integer.parseInt(st.nextToken());
			// end
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 시작시간 순으로 오름차순 정렬
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
		});
		
		// 우선순위 큐는 가장 먼저 끝나는 강의의 종료시간을 구하기 위해 쓴다.
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		// 가장 먼저 시작한 강의의 끝나는 시간을 넣는다.
		pq.add(arr[0][1]);
		
		for(int i = 1; i < N; i++) {
			
			int start = arr[i][0];
			int end = arr[i][1];
			// 가장 빨리 끝나는 강의의 종료시간이 다음 강의시간의 시작시간보다 늦으면
			// 강의실 하나가 더 필요하다.
			// 가장 빨리 끝나는 강으의 종료시간보다 다음 강의 시간이 늦으면 강의실을 하나 더 추가할 필요가 없다.
			if(pq.peek() <= start) {
				pq.poll();
			}
			pq.add(end);
			
//			중복된것이 있어 줄였다.
//			if(pq.peek() > start) {
//				pq.add(end);
//			}
//			else {
//				pq.poll();
//				pq.add(end);
//			}
			
		}
		
		System.out.println(pq.size());

	}

}

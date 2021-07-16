package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * <문제 요약>
 * 문제 정의
 * - 건물을 짖는데 필요한 최소 시간을 구하라
 * 
 * 문제 유형
 * - 위상정렬
 * 
 * 제약 사항
 * 1 <= N <= 500
 * 각 건물을 짖는데 걸리는 시간은 100,000보다 작다.
 * 
 * <풀이 요약>
 * 위상정렬을 이용한 문제
 * 줄세우기 문제를 통해 위상정렬을 익혔다면 풀 수 있는 문제라고 생각함.
 * 
 * 1. 인접리스트를 통해 입력받는다.
 * 2. 건물을 짖는데 필요한 시간을 times배열에 입력받는다.
 * 3. 위상정렬을 사용하여 건물이 지어지기 까지 필요한 시간을 구한다.
 * 3-1. 진입차수가 0인것을 큐에 넣는다. 시작하는 지점은 선행하는 건물이 없기때문에 answer가 times와 같다.
 * 3-2. 선행하는 건물과 이어진 건물의 진입차수를 1 줄인다.
 * 3-3. 후행하는 건물이 지어지기까지 걸리는 시간은 선행하는 건물이 지어지기 까지 걸리는 시간 + 자신의 선물이 지어지는 시간의 최대값이다.
 * 3-4. 진입차수가 0이 되면 큐에 넣는다.
 * 3-5. 3-2 ~ 3-4 과정을 반복한다.
 * 
 */

public class BOJ_G3_1516_게임개발 {

	static int[] times;
	static int[] answer;
	static ArrayList<ArrayList<Integer>> list;
	static int[] cntOfLink;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		// 건물을 짖는대 필요한 시간
		times = new int[N+1];
		// 답을 저장한 배열
		answer = new int[N+1];
		list = new ArrayList<>();
		// 진입차수
		cntOfLink = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());
			
			int preBuild = Integer.parseInt(st.nextToken());
			while(preBuild != -1) {
				// preBuild가 i건물 전에 선행되어야 한다.
				list.get(preBuild).add(i);
				preBuild = Integer.parseInt(st.nextToken());
				// i의 진입차수 증가
				cntOfLink[i]++;
			}
		}
		
		topologicalSort();
		
		for(int i = 1; i <= N; i++) {
			System.out.println(answer[i]);
		}
		
	}

	private static void topologicalSort() {
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			// 진입차수가 0인것 == 시작지점
			if(cntOfLink[i] == 0) {
				queue.add(i);
				answer[i] = times[i];
			}
		}
		
		while(!queue.isEmpty()) {
			int currentBuild = queue.poll();
			
			for(int nextBulild : list.get(currentBuild)) {
				
				// 진입차수 줄이기
				cntOfLink[nextBulild]--;
				//다음 오는 건물의 건설속도는 다음건물짖는 속도 + 선행되는 건물짖는 속도 의 최대값
				answer[nextBulild] = Math.max(answer[nextBulild], (answer[currentBuild] + times[nextBulild]));
				
				if(cntOfLink[nextBulild] == 0) {
					queue.add(nextBulild);
				}
			}
		}
	}

}

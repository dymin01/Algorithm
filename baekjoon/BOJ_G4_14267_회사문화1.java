package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 각자 얼마나 칭찬을 받았나 출력하라
 * 
 * 문제 유형
 * - 그래프 탐색
 * 
 * 제약 사항
 * 2 <= n, m <= 100,000
 * 2 <= i <= n
 * 1 <= w <= 1,000
 * 
 * <풀이 요약>
 * 칭찬점수를 한번 탐색할때 한번에 계산해야 한다. 안그러면 시간초과...!!!
 * 
 * 1. 인접리스트로 상사의 부하직원 리스트를 저장한다.
 * 2. 칭찬 점수를 입력받고 칭찬점수 배열에 더한다.
 * 3. 사장부터 부하직원으로 탐색하면서 자신의 칭찬 점수를 부하직원의 칭찬점수에 더한다.
 * 4. 사원의 칭찬점수를 출력한다.
 * 
 */

public class BOJ_G4_14267_회사문화1 {

	static int n, m;
	
	static ArrayList<Integer>[] list;
	
	static int[] praisePoint;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 회사원 수
		n = Integer.parseInt(st.nextToken());
		// 칭찬 횟수
		m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 사원별 친찬점수
		praisePoint = new int[n+1];
		
		// 상사 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int boss = Integer.parseInt(st.nextToken());
			if(boss == -1) continue;
			
			// 부하직원 리스트
			list[boss].add(i);
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
			// 칭찬 점수를 개인에 일단 준다.
			praisePoint[idx] += point;
			
		}
		// 사장부터 부하직원들의 칭찬점수를 더해간다.
		praise(1);
		
		for(int i = 1; i <= n; i++) {
			System.out.print(praisePoint[i] + " ");
		}

	}

	private static void praise(int idx) {
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(idx);
		
		while(!queue.isEmpty()) {
			int boss = queue.poll();
			
			for(int next : list[boss]) {
				// 부하직원의 칭찬점수는 상사의 칭찬점수의 누적
				praisePoint[next] += praisePoint[boss];
				queue.add(next);
				
			}
			
		}
		
	}

}

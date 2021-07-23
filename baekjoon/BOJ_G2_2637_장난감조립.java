package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * <문제 요약>
 * 문제 정의
 * - 하나의 장난감 완제품을 조립하기 위하여 필요한 기본 부품의 종류별 개수를 구하라
 * 
 * 문제 유형
 * - 위상정렬
 * 
 * 제약 사항
 * 3 <= N <= 100
 * 1 ~ N-1 까지는 중간 부품, N은 완제품의 번호
 * 3 <= M <= 100
 * X Y K ==> X를 만드는데 Y가 K개 필요하다.
 * 
 * <풀이 요약>
 * 기본에서 -> 완제품으로 풀려고 하다가
 * 완제품에서 시작해서 기본으로 내려가는 방법으로 문제를 풀었다.
 * 
 * 1. 인접리스트로 입력을 받는다. 이때 완제품에서 기본부품으로 가는 방향으로 만든다.
 * 2. 위상정렬을 이용하여 완제품부터 시작해서 기본부품까지 필요한 부품의 개수를 증가시키며 정렬한다.
 * 3. 기본부품인 것들의 개수만 출력한다.
 * 
 */

public class BOJ_G2_2637_장난감조립 {

	static class Node {
		int partNum, cnt;

		public Node(int partNum, int cnt) {
			super();
			this.partNum = partNum;
			this.cnt = cnt;
		}
	}
	
	static int N, M;
	static int[] cntOfLink;
	static int[] cntOfPart;
	static ArrayList<ArrayList<Node>> list;
	static boolean[] isBasicPart;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 부품의 개수
		N = Integer.parseInt(br.readLine());
		// 관계의 개수
		M = Integer.parseInt(br.readLine());
		
		cntOfLink = new int[N+1];
		cntOfPart = new int[N+1];
		isBasicPart = new boolean[N+1];
		Arrays.fill(isBasicPart, true);
		
		list = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			list.get(X).add(new Node(Y, K));
			cntOfLink[Y]++;
			isBasicPart[X] = false;
		}
		// 위상정렬
		topologicalSort();
		
		for(int i = 1; i <= N; i++) {
			if(isBasicPart[i]) {
				System.out.println(i + " " + cntOfPart[i]);
			}
		}
	}

	private static void topologicalSort() {
		Queue<Integer> queue = new LinkedList<>();
		// 완제품에서 시작
		queue.add(N);
		cntOfPart[N] = 1;
		
		while(!queue.isEmpty()) {
			int curPart = queue.poll();
			int curCnt = cntOfPart[curPart];
			
			// 현재 부품에서 필요한 부품들
			for(Node next : list.get(curPart)) {
				// 진입차수 감소
				cntOfLink[next.partNum]--;
				// 현재 부품의 개수 * 필요한 부품의 개수
				cntOfPart[next.partNum] += (curCnt * next.cnt);
				// 진입차수가 0이면 Queue에 넣는다.
				if(cntOfLink[next.partNum] == 0) {
					queue.add(next.partNum);
				}
				
			}
			
		}
		
	}

}

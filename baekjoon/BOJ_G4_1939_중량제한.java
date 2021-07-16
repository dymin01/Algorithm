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
 * - 한번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값을 구하라
 * 
 * 문제 유형
 * - 그래프 탐색
 * - 이분 탐색
 * 
 * 제약 사항
 * 1 <= M <= 100,000
 * 1 <= A, B <= N
 * 1 <= C <= 1,000,000,000
 *  
 * <풀이 요약>
 * 문제를 접근하는데 오랜 시간이 걸림... 결국 구선생의 도움으로 이해할 수 있었음
 * 
 * 1. 인접리스트로 섬을 입력받는다. 입력 받으면서 중량제한의 가장 작은값과 큰값을 미리 구한다.
 * 2. 중량의 중간을 정한 후 중간값으로 시작섬에서 끝섬에 갈 수 있는지 판단한다.
 * 3-1. 끝섬에 갈 수 있으면 중간값을 큰쪽 범위로 옮겨 다시 실행한다.
 * 3-2. 끝섬에 갈 수 없으면 중간값을 작은쪽 범위로 옯겨 다시 실행한다.
 * 4. 이분탐색으로 나온 결과를 출력한다.
 * 
 */


public class BOJ_G4_1939_중량제한 {
	
	static class Node{
		int island;
		int limited;
		public Node(int island, int limited) {
			super();
			this.island = island;
			this.limited = limited;
		}
	}
	
	static ArrayList<Node>[] list;

	static int N, M;
	static int start, end;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		
		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		int min = 0;
		int max = 0;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int limit = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b, limit));
			list[b].add(new Node(a, limit));
			max = Math.max(max, limit);
			min = Math.min(min, limit);
		}
		
		st = new StringTokenizer(br.readLine());
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		
		while(min <= max) {
			int mid = (max + min) / 2;
			
			// 탐색이 가능하면
			if(BFS(mid)) {
				ans = mid;
				min = mid+1;
			}
			// 불가능 하면
			else {
				max = mid-1;
			}
			
		}
		
		System.out.println(ans);

	}

	private static boolean BFS(int mid) {
		
		boolean[] v = new boolean[N+1];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		v[start] = true;
		
		while(!queue.isEmpty()) {
			
			int cur = queue.poll();
			
			for(Node next : list[cur]) {
				// 중량제한이 설정한 중량보다 크면 통과
				if(next.limited >= mid) {
					// 다음 섬이 목적 섬 이면
					if(next.island == end) return true;
					 // 방문하지 않았으면
					 if(!v[next.island]) {
						 v[next.island] = true;
						 queue.add(next.island);
					 }
				}
			}
		}
		
		return false;
	}

}

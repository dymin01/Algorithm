package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G4_17140_이차원배열과연산 {
	
	static class Node implements Comparable<Node>{

		int num;
		int cnt;
		
		public Node(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			if(this.cnt == o.cnt) {
				return Integer.compare(this.num, o.num);
			}
			return Integer.compare(this.cnt, o.cnt);
		}
		
	}

	static int rowSize;
	static int colSize;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[100][100];
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rowSize = 3;
		colSize = 3;
		
		// 100번 까지만 확인
		int t = 0;
		boolean isAns = false;
		for(t = 0; t <= 100; t++) {
			
			if(map[r-1][c-1] == k) {
				isAns = true;
				break;
			}
			// 행의 개수가 열의 개수보다 큰 경우
			// R연산
			if(rowSize >= colSize) {
				R(map);
			}
			// C연산
			else {
				C(map);
			}
			
//			for(int i = 0; i < rowSize; i++) {
//				for(int j = 0; j < colSize; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			
		}
		
		if(!isAns)
			System.out.println(-1);
		else
			System.out.println(t);

	}

	private static void C(int[][] map) {
		int newRowSize = 0;
		
		for(int c = 0; c < colSize; c++) {
			PriorityQueue<Node> pq = new PriorityQueue<>();
			int[] cntOfnum = new int[101];
			
			for(int r = 0; r < rowSize; r++) {
				if(map[r][c] != 0) {
					cntOfnum[map[r][c]]++;
					map[r][c] = 0;					
				}
			}
			
			for(int i = 0; i <= 100; i++) {
				if(cntOfnum[i] != 0) {
					pq.add(new Node(i, cntOfnum[i]));
				}
			}
			
			int idx = 0;
			newRowSize = Math.max(pq.size()*2, newRowSize);
			while(!pq.isEmpty()) {
				if(idx == 100) break;
				Node temp = pq.poll();
				map[idx++][c] = temp.num;
				map[idx++][c] = temp.cnt; 
			}
			
		}
		
		rowSize = newRowSize;
	}

	private static void R(int[][] map) {
		
		int newColSize = 0;
		
		for(int r = 0; r < rowSize; r++) {
			PriorityQueue<Node> pq = new PriorityQueue<>();
			int[] cntOfnum = new int[101];
			
			for(int c = 0; c < colSize; c++) {
				if(map[r][c] != 0) {
					cntOfnum[map[r][c]]++;
					map[r][c] = 0;					
				}
			}
			
			for(int i = 0; i <= 100; i++) {
				if(cntOfnum[i] != 0) {
					pq.add(new Node(i, cntOfnum[i]));
				}
			}
			
			int idx = 0;
			newColSize = Math.max(pq.size()*2, newColSize);
			while(!pq.isEmpty()) {
				if(idx == 100) break;
				Node temp = pq.poll();
				map[r][idx++] = temp.num;
				map[r][idx++] = temp.cnt; 
			}
			
		}
		
		colSize = newColSize;
		
	}

}

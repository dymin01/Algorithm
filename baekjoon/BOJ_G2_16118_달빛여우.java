package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <문제 요약> 구해야 하는 것: 여우가 먼저 도착하는 나무그루터기의 갯수
 * 유형 : 다익스트라
 * 요구 개념 : 다익스트라
 * 
 * <풀이법 요약>
 * 다익스트라를 2개 사용해서 여우와 늑대 따로 거리를 구해주는것 까지는 이해했으나,
 * 거리를 저장하는데 잘 이해가 안되 구선생의 도움을 받았음....
 * 어느정도 이해는 됐으나 다시 풀어봐야함...
 *  
 */


public class BOJ_G2_16118_달빛여우 {

	static class Tree implements Comparable<Tree> {
		int to, weight;
		int idx;
		public Tree(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		public Tree(int to, int weight, int idx) {
			this.to = to;
			this.weight = weight;
			this.idx = idx;
		}

		@Override
		public int compareTo(Tree o) {
			return this.weight - o.weight;
		}
	}
	
	static int N, M;
	
	static int[] foxdist;
	static int[][] wofdist;
	static List<Tree>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		foxdist = new int[N+1];
		wofdist = new int[N+1][2];
		
		list = new ArrayList[N+1];

		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
			foxdist[i] = Integer.MAX_VALUE;
			wofdist[i][0] = Integer.MAX_VALUE;
			wofdist[i][1] = Integer.MAX_VALUE;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken())*2;
			
			list[from].add(new Tree(to, weight));
			list[to].add(new Tree(from, weight));
			
		}
		
		dijkstraFox(1);
		dijkstraWof(1);
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			if(foxdist[i] < Math.min(wofdist[i][0], wofdist[i][1])) cnt++;
		}
		System.out.println(cnt);
		
	}

	private static void dijkstraFox(int start) {
		PriorityQueue<Tree> pq = new PriorityQueue<>();
		pq.add(new Tree(start, 0));
		foxdist[start] = 0;
		while(!pq.isEmpty()) {
			Tree curTree = pq.poll();
			int cur = curTree.to;
			
			if(foxdist[cur] < curTree.weight) continue;
			
			for(Tree tr : list[cur]) {
				if(foxdist[tr.to] > foxdist[cur] + tr.weight) {
					foxdist[tr.to] = foxdist[cur] + tr.weight;
					pq.add(new Tree(tr.to, foxdist[tr.to]));
				}
			}
		}
	}
	
	private static void dijkstraWof(int start) {
		PriorityQueue<Tree> pq = new PriorityQueue<>();
		int idx = 0;
		pq.add(new Tree(start, idx, 0));
		wofdist[start][idx] = 0;
		while(!pq.isEmpty()) {
			
			Tree curTree = pq.poll();
			int cur = curTree.to;
			int curdis = curTree.weight;
			int curidx = curTree.idx;

			if(wofdist[cur][curidx] < curdis) continue;
			
			for(Tree tr : list[cur]) {
				int to = tr.to;
				int dist = curdis;
				int newidx = -1;
				if(curidx == 1) {
					dist += tr.weight * 2;
					newidx = 0;
				}else {
					dist += tr.weight / 2;
					newidx = 1;
				}
				if(wofdist[to][newidx] > dist ) {
					wofdist[to][newidx] = dist;
					pq.add(new Tree(to, dist, newidx));
				}
				
			}
			
		}
	}

}

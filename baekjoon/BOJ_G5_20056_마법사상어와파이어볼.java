package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - K번 명령 후 남아있는 파이어볼 질량의 합을 구하라
 * 
 * 문제 유형
 * - 구현
 * 
 * 제약 사항
 * 4 ≤ N ≤ 50
 * 0 ≤ M ≤ N2
 * 1 ≤ K ≤ 1,000
 * 1 ≤ ri, ci ≤ N
 * 1 ≤ mi ≤ 1,000
 * 1 ≤ si ≤ 1,000
 * 0 ≤ di ≤ 7
 * 
 * <풀이 요약>
 * 
 * 
 */

public class BOJ_G5_20056_마법사상어와파이어볼 {

	static int N, M, K;
	static ArrayList<Fireball>[][] map;
	
	static class Fireball{
		int m, d, s;
		public Fireball(int m, int d, int s) {
			super();
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Fireball(m, d, s));
		}
		
		for(int i =0 ; i < K; i++) {
			// 1. 이동
			move();
		}
		
		long ans = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				for(Fireball fireball : map[r][c]) {
					ans += fireball.m;
				}
			}
		}
		
		System.out.println(ans);

	}

	private static void move() {
		
		ArrayList<Fireball>[][] next = new ArrayList[N+1][N+1];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				next[i][j] = new ArrayList<>();
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				// 파이어볼이 있으면
				if(map[r][c].size() >= 1) {
					for(Fireball fireball : map[r][c]) {
						int dist = fireball.s%N;
						int nr = r + dr[fireball.d]*dist;
						int nc = c + dc[fireball.d]*dist;
						
						if(nr >= N) nr -= N;
						else if(nr < 0) nr += N;
						
						if(nc >= N) nc -= N;
						else if(nc < 0) nc += N;
						
						next[nr][nc].add(new Fireball(fireball.m, fireball.d, fireball.s));
					}
				}
			}
		}
		
		map = next;
		// 2.2 나누기
		split();
		
		
		
	}

	private static void split() {
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c].size() <= 1) continue;
				
				int sumOfm = 0;
				int sumOfs = 0;
				
				boolean isOdd = true;
				boolean isEven = true;
				
				for(Fireball fireball : map[r][c]) {
					sumOfm += fireball.m;
					sumOfs += fireball.s;
					if(fireball.d % 2 == 0) {
						isOdd = false;
					} else {
						isEven = false;
					}
				}
				int m = sumOfm / 5;
				int s = sumOfs / map[r][c].size();
				map[r][c].clear();
				
				// 질량이 0보다 크면
				if(m > 0) {
					for(int i = 0; i < 4; i++) {
						// 모두 홀수이거나 짝수이면
						if(isOdd || isEven) {
							map[r][c].add(new Fireball(m, 0 + 2*i, s));
						}else {
							map[r][c].add(new Fireball(m, 1 + 2*i, s));
						}
					}
				}
				
			}
		}
		
	}

}

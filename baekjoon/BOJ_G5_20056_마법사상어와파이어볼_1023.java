package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G5_20056_마법사상어와파이어볼_1023 {
	
	static class Fireball{
		int m, s, d;

		public Fireball(int m, int s, int d) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	static int N, M, K;
	
	static ArrayList<Fireball>[][] map;
	
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
			
			map[r][c].add(new Fireball(m, s, d));
		}
		
		// K번 반복
		for(int i = 0; i < K; i++) {
			// 1. 이동
			move();
		}
		int answer = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				for(Fireball fireball : map[r][c]) {
					answer += fireball.m;
				}
			}
		}
	
		System.out.println(answer);

	}

	private static void move() {
		
		ArrayList<Fireball>[][] next = new ArrayList[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				next[i][j] = new ArrayList<>();
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				// 비어있으면 넘어가기
				if(map[r][c].size() == 0) continue;
				
				for(Fireball fireball : map[r][c]) {
					int dist = fireball.s % N;
					
					int nr = r + dr[fireball.d] * dist;
					int nc = c + dc[fireball.d] * dist;
					
					if(nr >= N) nr -= N;
					else if(nr < 0) nr += N;
					
					if(nc >= N) nc -= N;
					else if(nc < 0) nc += N;
					
					next[nr][nc].add(new Fireball(fireball.m, fireball.s, fireball.d));
				}
			}
		}
		
		map = next;
		// 2. 나누기
		split();
		
	}

	private static void split() {
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				// 파피어볼이 2개이상이면
				if(map[r][c].size() >= 2) {
					
					int totM = 0;
					int totS = 0;
					
					boolean isOdd = true;
					boolean isEven = true;
					
					for(Fireball fireball : map[r][c]) {
						totM += fireball.m;
						totS += fireball.s;
						
						// 짝수이면
						if(fireball.d % 2 == 0) {
							isEven = false;
						}else {
							isOdd = false;
						}
						
					}
					
					int nM = totM / 5;
					int nS = totS / map[r][c].size();
					
					map[r][c].clear();
					
					if(nM > 0) {
						for(int i =0; i < 4; i++) {
							// 모두 짝수이거나 홀수이면
							if(isOdd || isEven) {
								map[r][c].add(new Fireball(nM, nS, 2*i));
							}
							else {
								map[r][c].add(new Fireball(nM, nS, 2*i + 1));
							}
						}
					}
					
				}
			}
		}
		
		
	}

	

}

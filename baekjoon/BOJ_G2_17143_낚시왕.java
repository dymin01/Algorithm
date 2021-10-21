package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G2_17143_낚시왕 {
	static class Shark {
		int r, c, s, d, z;
		
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
		void updatePosition() {
			int move = this.s;
			
			// 상하 0 1
			if(this.d < 2) {
				move %= ((R - 1) * 2);
				
				while(move > 0) {
					if(this.r == 0) {
                        d = 1;
                    }
                    if(this.r == R-1) {
                        d = 0;
                    }
                    this.r += dr[d];
                    move--;
				}
				
			}
			// 우좌 2 3
			else {
				move %= ((C - 1) * 2);
				
				while(move > 0) {
					if(this.c == 0) {
                        d = 2;
                    }
                    if(this.c == C-1) {
                        d = 3;
                    }
                    this.c += dc[d];
                    move--;
				}
			}
		}

	}
	
	static int R, C, M;
	
	static ArrayList<Shark> sharks;
	static Shark[][] map;

	static int answer;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		if(M == 0) {
			System.out.println(0);
			return;
		}
		
		map = new Shark[R][C];
		
		sharks = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			
			Shark shark = new Shark(r, c, s, d, z);
			map[r][c] = shark;
			sharks.add(shark);
		}
		
		answer = 0;
		for(int c = 0; c < C; c++) {
			// 상어 낚시
			chtchShark(c);
			// 상어 이동
			move();
			// 살아있는 상어
			survive();
		}
		
		System.out.println(answer);

	}

	private static void survive() {
		map = new Shark[R][C];
		int size = sharks.size();
		
		for(int i = size-1; i >= 0; i--){
			Shark s = sharks.get(i);
			// map이 비어있는경우
			if(map[s.r][s.c] == null) {
				map[s.r][s.c] = s;
			}
			// map에 이미 상어가 있으면
			else {
				// 지도에 있는 상어의 사이즈가 크면
				if(map[s.r][s.c].z > s.z ) {
					sharks.remove(s);
				}
				// 그렇지 않으면
				else {
					sharks.remove(map[s.r][s.c]);
					map[s.r][s.c] = s;
				}
			}
			
		}
	}

	private static void move() {
		
		for(Shark shark : sharks) {
			shark.updatePosition();
		}
		
	}

	private static void chtchShark(int c) {
		
		for(int r = 0; r < R; r++) {
			// 상어가 있으면
			if(map[r][c] != null) {
				Shark now = map[r][c];
				
				// 지도에서 상어 제거
				map[r][c] = null;
				
				// 잡은 상어 크기
				answer += now.z;
				sharks.remove(now);
				
				break;
			}
		}
		
	}
	

}

package sw_expert_academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설 {

	static int[][] map;
	static int N, X;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			ans = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				if(isOk(i, true)) {
					ans++;
				}else {
					if(canConstruct(i, true)) {
						ans++;
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				if(isOk(i, false)) {
					ans++;
				}else {
					if(canConstruct(i, false)) {
						ans++;
					}
				}
			}
			
			System.out.println("#" + t + " " + ans);
			
		}
		
	}

	private static boolean canConstruct(int idx, boolean isRow) {
		boolean[] visited = new boolean[N];
		int cnt = 0;
		int before = -1;
		
		if(isRow) {
			before = map[idx][0];
			
			for(int c = 0 ; c < N ; ++c) {
				int cur = map[idx][c];
				
				if(cur == before) {
					cnt++;
				} else {
					if(cur == before + 1) {
						// 지대가 높아지는 경우 
						if(cnt >= X) {
							// 경사로 설치
							for(int i = c - X ; i < c ; ++i) {
								if(visited[i]) return false;
								visited[i] = true;
							}
							cnt = 1;
						} else {
							// 길이가 부족하여 경사로 설치 불가 
							return false;
						}
					} else if(cur == before - 1) {
						// 지대가 낮아지는 경우
						if(c + X - 1 < N) {
							// 경사로 설치
							for(int i = c ; i < c + X ; ++i) {
								if(visited[i] || map[idx][i] != cur) return false;
								visited[i] = true;
							}
							cnt = 0;
							c = c + X - 1;
						} else {
							// 길이가 부족하여 경사로 설치 불가
							return false;
						}
					} else {
						return false;
					}
					before = cur;
				}
			}
		} else {
			before = map[0][idx];
			
			for(int r = 0 ; r < N ; ++r) {
				int cur = map[r][idx];
				
				if(cur == before) {
					cnt++;
				} else {
					if(cur == before + 1) {
						// 지대가 높아지는 경우 
						if(cnt >= X) {
							// 경사로 설치
							for(int i = r - X ; i < r ; ++i) {
								if(visited[i]) return false;
								visited[i] = true;
							}
							cnt = 1;
						} else {
							// 길이가 부족하여 경사로 설치 불가 
							return false;
						}
					} else if(cur == before - 1) {
						// 지대가 낮아지는 경우
						if(r + X - 1 < N) {
							// 경사로 설치
							for(int i = r ; i < r + X ; ++i) {
								if(visited[i] || map[i][idx] != cur) return false;
								visited[i] = true;
							}
							r = r + X - 1;
							cnt = 0;
						} else {
							// 길이가 부족하여 경사로 설치 불가
							return false;
						}
					} else {
						return false;
					}
					before = cur;
				}
			}
		}
		
		return true;
	}

	private static boolean isOk(int idx, boolean isRow) {
		int start = -1;
		
		if(isRow) {
			start = map[idx][0];
			for(int c = 1 ; c < N ; ++c) {
				if(map[idx][c] != start) return false;
			}
		} else {
			start = map[0][idx];
			for(int r = 1 ; r < N ; ++r) {
				if(map[r][idx] != start) return false;
			}
		}
		
		return true;
	}

}

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_S1_21608_상어초등학교 {

	static class Student{
		int num;
		ArrayList<Integer> list;
		public Student(int num, ArrayList<Integer> list) {
			super();
			this.num = num;
			this.list = list;
		}
		
	}
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int N;
	static int[][] map;
	
	static Student[] studentInfo;
	static int[] order;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		map = new int[N][N];
		order = new int[N*N];
		studentInfo = new Student[N*N + 1];
		
		for(int i = 0; i < N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int cur = Integer.parseInt(st.nextToken());
			order[i] = cur;
			ArrayList<Integer> temp = new ArrayList<>();
			for(int j = 0; j < 4; j++) {
				temp.add(Integer.parseInt(st.nextToken()));
			}
			studentInfo[cur] = new Student(cur, temp);
		}

		for(int i =0; i < N*N; i++) {
			
			Student student = studentInfo[order[i]];
			int num = student.num;
			ArrayList<Integer> friendlist = student.list;
			
			int likeFriend = 0;
			int emptyCnt = 0;
			
			// 놓을 장소
			int setR = 987654321;
			int setC = 987654321;
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					
					// 이미 값이 있으면 넘어감
					if(map[r][c] != 0) continue;
					
					int tempLikeFriend = 0;
					int tempEmptyCnt = 0;
					
					// 4방 탐색해서 좋아하는 친구가 몇명있는지 확인
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						// 범위를 넘어가면
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						// 옆자리가 빈자리면
						if(map[nr][nc] == 0) {
							tempEmptyCnt++;
							continue;
						}
						// 엽자리에 사람이 있으면 좋아하는 친구인지
						if(isLikeFriend(friendlist, map[nr][nc])) {
							tempLikeFriend++;
						}
					}
					
					// 1 : 비어있는 칸 중에 좋아하는 학생이 인접한 카에 가장 많은 칸으로 정한다.
					if(tempLikeFriend > likeFriend) {
						likeFriend = tempLikeFriend;
						emptyCnt = tempEmptyCnt;
						setR = r;
						setC = c;
						continue;
					}
					//2 : 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
					if(likeFriend == tempLikeFriend) {
						
						// 인접한 빈칸이 더 많은 칸
						if(tempEmptyCnt > emptyCnt) {
							emptyCnt = tempEmptyCnt;
							setR = r;
							setC = c;
							continue;
						}
						
						//3 : 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
						if(emptyCnt == tempEmptyCnt) {
							if(setR > r || (setR == r && setC > c)) {
								setR = r;
								setC = c;
							}
						}
						
					}
					
				}
				
			}
			map[setR][setC] = num;
			
		}
		
		System.out.println(calcSum());
			
		
	}

	private static int calcSum() {
		int ans = 0;

		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				int cnt = 0;
				
				int cur = map[r][c];
				Student student = studentInfo[cur];
				ArrayList<Integer> list = student.list;
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					if(isLikeFriend(list, map[nr][nc])) cnt++;
					
				}
				
				switch (cnt) {
				case 0:
					ans += 0;
					break;
				case 1:
					ans += 1;
					break;
				case 2:
					ans += 10;
					break;
				case 3:
					ans += 100;
					break;
				case 4:
					ans += 1000;
					break;
				}
			}
		}
		return ans;
	}

	private static boolean isLikeFriend(ArrayList<Integer> friendlist, int next) {
		
		if(friendlist.contains(next)) {
			return true;
		}
		
		return false;
	}

}

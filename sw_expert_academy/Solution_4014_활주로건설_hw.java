package sw_expert_academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설_hw {

	
	static int N, X;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#" + t + " " + process());
			
			
		}
		

	}

	private static int process() {
		
		int count = 0;
		for(int i = 0; i < N; i++) {
			if(makeRoadByRow(i)) count++;
			if(makeRoadByCol(i)) count++;
		}
		
		return count;
	}

	private static boolean makeRoadByRow(int i) {
		
		int beforeHeight = map[i][0];
		int size = 0;	// 연속된 높이의 크기
		int j = 0;		// 탐색하는 열의 위치
		
		while(j < N) {
			// 평지
			if(beforeHeight == map[i][j]) {
				size++;
				j++;
			}
			// 오르막
			else if(beforeHeight + 1 == map[i][j]) {
				if(size < X) return false; // 경사로 설치 불가
				beforeHeight++;
				size = 1;
				j++;
			}
			// 내리막
			else if(beforeHeight - 1 == map[i][j]) {
				int count = 0;
				for(int k = j; j < N; k++) {
					if(map[i][k] != beforeHeight-1) break;
					if(++count == X) break; 
				}
				if(count < X) return false;
				beforeHeight--;
				j += X;
				size = 0;
			} else {
				return false;
			}
		}
		
		return true;
		
	}

	private static boolean makeRoadByCol(int i) {
		int beforeHeight = map[0][i];
		int size = 0;	// 연속된 높이의 크기
		int j = 0;		// 탐색하는 열의 위치
		
		while(j < N) {
			// 평지
			if(beforeHeight == map[j][i]) {
				size++;
				j++;
			}
			// 오르막
			else if(beforeHeight + 1 == map[j][i]) {
				if(size < X) return false; // 경사로 설치 불가
				beforeHeight++;
				size = 1;
				j++;
			}
			// 내리막
			else if(beforeHeight - 1 == map[j][i]) {
				int count = 0;
				for(int k = j; j < N; k++) {
					if(map[k][i] != beforeHeight-1) break;
					if(++count == X) break; 
				}
				if(count < X) return false;
				beforeHeight--;
				j += X;
				size = 0;
			} else {
				return false;
			}
		}
		
		return true;
		
	}


}

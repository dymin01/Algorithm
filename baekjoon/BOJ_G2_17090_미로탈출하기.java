package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <문제 요약> 구해야 하는 것: 미로에서 탈출 가능한 칸의 개수
 * 유형 : DFS, 백트레킹
 * 요구 개념 : DFS
 * 
 * <풀이법 요약>
 * 1. 미로의 char에 따라 DFS다음 위치로 이동한다.
 * 2. 이동한 위치의 상태에 따라 반환한다.
 * 	2-1. 범위를 넘어가거나, 이미 빠저나갈 수 있는 위치인 경우 true를 반환
 *  2-2. 이미 방문한 위치이고, 빠져나갈 수 없는 위치인 경우 false를 반환
 * 3. DFS를 빠져나왔을때 결과가 true일 경우 개수를 ++한다.
 * 
 */

public class BOJ_G2_17090_미로탈출하기 {
	
	static char[][] map;
	static boolean[][] v;
	static boolean[][] check;
	
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		v = new boolean[N][M];
		check = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		boolean ans = false;
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!v[i][j]) {
					ans = DFS(i, j);
				}
				if(ans) cnt++;
			}
		}
		
		System.out.println(cnt);

	}

	private static boolean DFS(int r, int c) {
		
		if(r < 0 || r >= N || c < 0 || c >= M || check[r][c]) {
			return true;
		}
		
		if(v[r][c] && !check[r][c]) return false;
		
		switch (map[r][c]) {
		case 'U':
			v[r][c] = true;
			check[r][c] = DFS(r - 1, c);
			v[r][c] = false;
			return check[r][c];
		case 'R':
			v[r][c] = true;
			check[r][c] = DFS(r, c + 1);
			v[r][c] = false;
			return check[r][c];
		case 'D':
			v[r][c] = true;
			check[r][c] = DFS(r + 1, c);
			v[r][c] = false;
			return check[r][c];
		case 'L':
			v[r][c] = true;
			check[r][c] = DFS(r, c - 1);
			v[r][c] = false;
			return check[r][c];
		}
		
		return false;
		
	}

}

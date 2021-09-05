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
 * - 호랑이에게 잡아먹히지 않도록 호랑이에게 줄 떡을 골라 출력하라
 * 
 * 문제 유형
 * - DFS
 * 
 * 제약 사항
 * 1 <= N <= 1,000
 * 1 <= i <= N
 * 1 <= mi <= 9
 * 1 ≤ ai,1 < ai,2 < ... < ai,mi ≤ 9
 * 
 * <풀이 요약>
 * 
 * visited 방문 배열을 2차원으로 만들어서 방문을 줄여줘야 한다.
 * 
 * 1. 날짜별로 떡의 종류를 list로 입력 받는다.
 * 2. 떡을 고를때 그 떡이 전날 떡과 다른떡이고, 이전에 방문했던 떡이 아니라면 방문해서 다음날로 이동한다.
 * 2-1. 이전에 방문했던 떡인지 확인하는 이유는 이미 그날짜에 방문했지만 답을 찾지 못해서 돌아온거기 때문에
 *      그 날짜에 그 떡을 또 선택해도 답이 나오지 않기 때문에 방문하는 횟수를 줄이기 위해 2차원 방문배열을 만든다... 
 * 3. 마지막 날 까지 떡을 선택했다면 정답을 출력한다.
 * 
 */

public class BOJ_G5_16432_떡장수와호랑이 {

	static ArrayList<Integer>[] list;
	
	static boolean[][] visited;
	
	static int[] answer;
	
	static boolean isAns;
	
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		// 방문배열 --> i번째 날 선택한 떡 방문 체크
		visited = new boolean[N][10];
		
		// 정답 배열
		answer = new int[N];
		
		// 입력받는 날짜별 떡 리스트
		list = new ArrayList[N];
		
		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			for(int j = 0; j < M; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		isAns = false;
		
		DFS(0, 0);
		
		if(isAns) {
			for(int ans : answer) {
				System.out.println(ans);
			}
		}else {
			System.out.println(-1);
		}
		

	}

	private static void DFS(int idx, int pre) {
		// 마지막날 까지 떡을 선택했다면 정답이다.
		if(idx == N) {
			isAns = true;
			return;
		}
		// idx날짜에 있는 떡 중 하나를 고른다.
		for(int i : list[idx]) {
			// 전날 먹은 떡과 다르고, 이전에 선택했던 적이 없으면 떡을 선택한다.
			if(i != pre && !visited[idx][i]) {
				visited[idx][i] = true;
				answer[idx] = i;
				DFS(idx+1, i);
				// 정답을 찾았으면 더 찾지 않고 종료한다.
				if(isAns) break;
			}
		}
		
		
	}

}

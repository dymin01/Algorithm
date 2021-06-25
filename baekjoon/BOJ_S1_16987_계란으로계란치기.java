package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의 : 최대 몇 개의 계란을 깰 수 있는지 구하라
 * 문제 유형 : 백트레킹
 * 제약 사항 : 1 <= N <= 8
 * 			내구도 Si(1 ≤ Si ≤ 300)와 무게 Wi(1 ≤ Wi ≤ 300)
 * 
 * 계란을 치는 과정
 * 1. 가장 왼쪽의 계란을 든다.
 * 2. 손에 들고 있는 계란으로 깨지지 않은 다른 계란 중에서 하나를 친다. 단, 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다.
 *    이후 손에 든 계란을 원래 자리에 내려놓고 3번 과정을 진행한다.
 * 3. 가장 최근에 든 계란의 한 칸 오른쪽 계란을 손에 들고 2번 과정을 다시 진행한다. 단, 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 계란을 치는 과정을 종료한다.
 * 
 * <풀이 요약>
 * 1. 계란을 배열에 저장한다.
 * 2. 계란으로 남아있는 계란 중 하나와 충돌하고 다음 계란으로 넘어간다.
 * 3. 다음 계란이 이미 깨져있거나, 깰 수 있는 계란이 없으면 다음 계란으로 넘어가 마지막 계란으로 갈 수 있도록 한다.
 * 4. 마지막 계란까지 선택을 했다면 배열에서 깨진 계란의 갯수를 구한다.
 * 
 */


public class BOJ_S1_16987_계란으로계란치기 {

	static int N;
	
	static int ans;
	
	static int[][] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		
		// 계란의 개수
		N = Integer.parseInt(br.readLine());
		
		// 계란의 내구도와 무게
		// 0 : 내구도 1 : 무게
		arr = new int[N][2];
		
		ans = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// 내구도
			arr[i][0] = Integer.parseInt(st.nextToken());
			// 무게
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 가장 왼쪽에 있는 계란을 든다.
		DFS(0);
		
		System.out.println(ans);

	}

	// pos 는 손에 들고 있는 계란
	public static void DFS(int pos) {
		
		// 이미 최대의 계란을 깨면 구하지 않는다.
		if(ans == N) return;

		// 마지막 계란까지 모두 손에 들었다면
		if(pos == N) {
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				if(arr[i][0] <= 0) cnt++;
			}
			if(ans < cnt) {
				ans = cnt;
			}
			
			return;
		}
		
		// 이미 계란이 깨져있으면 다음 위치의 계란을 선택한다.
		if(arr[pos][0] <= 0) {
			DFS(pos+1);
			return;
		}
		
		
		boolean go = true;
		
		// 손에 들고 있는 계란이 깨져있으면(내구도가 0보다 작으면) return 한다.
		for(int i = 0; i < N; i++) {
			// 이미 손에 들고 있거나 이미 깨진것이면 넘어간다.
			if(i == pos || arr[i][0] <= 0) continue;
			
			// 계란을 친다.
			// 손에 들고있는 계란의 내구도 = 내구도 - 놓인 계란의 내구도
			arr[pos][0] -= arr[i][1];
			// 놓인 계란의 내구도 = 내구도 - 손에들고있는 계란의 내구도
			arr[i][0] -= arr[pos][1];
			
			go = false;
			DFS(pos+1);
			
			// 계란을 치기 전으로 돌린다.
			arr[pos][0] += arr[i][1];
			arr[i][0] += arr[pos][1];
			
		}
		
		if(go) DFS(pos+1);
		
		
	}
}

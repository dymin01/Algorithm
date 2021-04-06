package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 정해진 식재료를 이용하여 맛의 차이가 가장 적은 값을 찾는다.
 * 요구 개념/문제 유형 : 조합, 구현
 * 
 * <풀이법 요약>
 * 1. N개의 요리 중 2개를 A음식 재료의 조합을 구한다.
 * 2. A의 재료를 사용한 요리의 시너지와 B의 재료를 사용한 요리의 시너지를 빼서 최소값을 구한다.
 */

public class Solution_4012_요리사 {

	static int N;
	static int[][] map;
	static boolean[] A;
	static int ans;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			A = new boolean[N];
			ans = 987654321;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			nCr(0, 0);
			
			System.out.println("#" + t + " " + ans);
		}

	}
	
	public static void nCr(int start, int cnt) {
		if(cnt == N/2) {
			int sumA = 0;
			int sumB = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(A[i] && A[j]) {
						sumA += map[i][j];
					}else if(!A[i] && !A[j]) {
						sumB += map[i][j];
					}
				}
			}
			
			ans = Math.min(ans, Math.abs(sumA - sumB));
			
			return;
			
		}
		
		for(int i = start; i < N; i++) {
			A[i] = true;
			nCr(i+1, cnt+1);
			A[i] = false;
		}
		
	}

}

package programmers;

/***
 * 
 * <문제 요약> 문제 정의 : 집에서 학교까지 갈 수 있는 경우의 수를 구하라
 * 문제 유형 : DP
 * 제약 사항 : 1 <= m,n <= 100
 * 1인 경우는 없다.
 * 물이 잠긴 지역은 0 이상 10개 이하 이다.
 * <풀이 요약>
 * 1. DPmap을 만든다.
 * 2. x,y 좌표로 올 수 있는 방법은 왼쪽에서 오는 방법과, 위에서 오는 방법뿐이다.
 * 3. 시작을 1로, 웅덩이를 -1로 초기화 한다.
 * 4. 2중 for문을 돌면서 왼쪽과 위의 값을 더한다.
 * 5. 만약 웅덩이일 경우 그냥 계산하지 않는다.
 */

public class PM_L3_등굣길 {

	public static void main(String[] args) {

		int m = 4;
		int n = 3;
		int[][] puddles = { { 2, 2 } };

		PM_L3_등굣길 sol = new PM_L3_등굣길();

		System.out.println(sol.solution(m, n, puddles));

	}

	public static int solution(int m, int n, int[][] puddles) {
		int answer = 0;

		int[][] DPMap = new int[n + 1][m + 1];

		// 웅덩이는 -1
		for (int i = 0; i < puddles.length; i++) {
			DPMap[puddles[i][1]][puddles[i][0]] = -1;
		}

		DPMap[1][1] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {

				// 지금 위치가 웅덩이일 경우
				if (DPMap[i][j] == -1) {
					continue;
				}

				// 위칸이 웅덩이가 아니면
				if (DPMap[i - 1][j] >= 0) {
					DPMap[i][j] += (DPMap[i - 1][j] % 1000000007);
				}
				// 왼쪽칸이 웅덩이가 아니면
				if (DPMap[i][j - 1] >= 0) {
					DPMap[i][j] += (DPMap[i][j - 1] % 1000000007);
				}

			}
		}
		// % 연산 하는거 까먹지 말자.
		answer = DPMap[n][m] % 1000000007;

		return answer;
	}

}

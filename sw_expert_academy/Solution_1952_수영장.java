package sw_expert_academy;
import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 일년동안 가장 적은 비용으로 수영장을 이용하는 경우를 구하는 문제
 * 요구 개념/문제 유형 : DP
 * 
 * <풀이법 요약>
 * 1. 1개월을 이용할때 일일권을 사용하는것과 1달 이용권을 이용하는것 중 싼것을 고른다.
 * 2. 3개월 이상일경우 전달 + 1달권 또는 일일권을  사용하는것과, 3달 전 + 3달 이용권을 사용하는 것 중 싼것을 고른다.
 * 3. 같은 방법으로 12월 까지 구한 후 1년 이용권과 비교해 비용이 적게 드는것을 출력한다.
 */

public class Solution_1952_수영장 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {

			int oneDay = sc.nextInt();
			int oneMonth = sc.nextInt();
			int threeMonth = sc.nextInt();
			int oneYear = sc.nextInt();
			
			int[] list = new int[13];
			int[] cost = new int[13];
			for(int i = 1; i <= 12; i++) {
				list[i] = sc.nextInt();
			}

			for (int i = 1; i <= 12; i++) {
				int day = 0;

				day = oneDay * list[i];
				cost[i] = Math.min(day, oneMonth);
				if (i > 1) {
					cost[i] += cost[i-1];
					if (i >= 3) {
						cost[i] = Math.min(cost[i - 3] + threeMonth, cost[i]);
					}
				}
			}

			System.out.println("#" + t + " " + Math.min(cost[12], oneYear));
		}
	}

}

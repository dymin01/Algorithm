package programmers;

/***
 * <문제 요약>
 * 구해야 하는 것 : 카펫의 가로, 세로 길이
 * 제약 사항 : 
 * 문제 유형 : 구현
 * 요구 개념 : 완전탐색
 * <풀이법 요약>
 * 0. 노란색의 세로의 길이가 1부터 W까지 조건에 맞을때 까지 탐색
 * 1. 노란색의 세로의 길이 * 노란색의 가로의 길이가 노란색의 넓이이고, (노란색의 가로+2 + 세로+2) * 2 - 4 가 갈색의 크기이면 답
 */

public class 카펫 {

	public static void main(String[] args) {
		카펫 pl = new 카펫();
		int[] ans = pl.solution(24, 24);
		System.out.println(ans[0] + " " + ans[1]);

	}

	public int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		
		// 노란색 부분의 세로 길이
		int H = 1;
		// 노란색 부분의 가로 길이
		int W = yellow;
		// 세로의 길이가 1부터 조건에 맞는지 탐색한다.
		for (H = 1; H <= W; H++) {
			W = yellow/H;
			// 가로의 길이는 세로의 길이와 같거나, 세로 길이보다 길다.
			if (W >= H) {
				// 노란색의 세로의 길이 * 노란색의 가로의 길이 == 노란색의 넓이와 같고
				// 노란색의 테두리의 길이 가 갈색과 같으면 조건에 맞는 답
				//((2 * (H + 2) + 2 * (W + 2)) - 4)
				//  갈색의 세로 2개        갈색의 가로 2개     모서리 4개
				if ((H * W == yellow) && ((2 * (H + 2) + 2 * (W + 2)) - 4) == brown) {
					// 답은 카펫 전채의 크기이기 때문에 +2
					answer[0] = W + 2;
					answer[1] = H + 2;
					break;
				}
			}
		}
		return answer;
	}

}

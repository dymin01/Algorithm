package baekjoon;
import java.util.Scanner;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 철수의 방법으로 소거 후 알아낸 비밀번호
 * 문제 핵심 요약 : 같은숫자를 지우고 다시 같은 숫자가 나올 수 있다.
 * <풀이법 요약> 
 * 1. 인덱스의 숫자와 다음 인덱스의 숫자가 같으면 둘다 지운다.
 * 2. 인덱스를 다시 0부터 시작한다.
 * 3. 1, 2를 반복한다.
 */
public class Solution_D3_1234 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = 10;
		for(int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			StringBuilder str = new StringBuilder(sc.next());
			
			// 숫자의 갯수만큼 반복
			for(int i = 0; i < str.length()-1; i++) {
				// 다음숫자와 같다면 지우고 처음부터 다시 탐색
				if(str.charAt(i) == str.charAt(i+1)) {
					str.deleteCharAt(i+1);
					str.deleteCharAt(i);
					i = -1;
				}
			}
			System.out.println("#" + t + " " + str);
		}
	}
}

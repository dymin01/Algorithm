package baekjoon;
import java.util.Scanner;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 크로스워드 만들기
 * 문제 핵심 요약 : 단어에서 같이 등장하는 알파벳을 찾아 크로스워드를 만든다.
 * <풀이법 요약> 
 * 1. 
 * 2. 홀수일경우 시계방향으로 공을 넘긴다.
 * 3. 짝수 일경우 반시계 방향으로 공을 넘긴다.
 * 4. 공을 받은 횟수가 M과 같으면 종료
 */

public class BOJ_B2_2804 {

	static char[][] ans;
	static int Apos, Bpos;
	static int N, M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String A = sc.next();
		String B = sc.next();
		
		N = A.length();
		M = B.length();
		
		ans = new char[M][N];
		// 두개의 단어에서 같이 등장하는 단어의 위치 찾기
		findPos(A, B);
		
		// 배열을 채워준다.
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				// 가로로 글을 써야 하는 경우
				if(i == Bpos) {
					ans[i][j] = A.charAt(j);
					continue;
				}
				// 세로로 단어를 써야 할 경우
				if(j == Apos) {
					ans[i][j] = B.charAt(i);
				}else {
					ans[i][j] = '.';
				}
			}
		}
		print();
	}
	// 배열 출력
	public static void print() {
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(ans[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void findPos(String A, String B) {
		// 글자 하나씩 비교해서 같이 등장하는 위치 검색
		for(int i = 0; i < N; i++) {
			char a = A.charAt(i);
			for(int j = 0; j < M; j++) {
				if(a == B.charAt(j)) {
					Apos = i;
					Bpos = j;
					return;
				}
			}
		}
	}

}

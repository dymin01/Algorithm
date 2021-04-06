package baekjoon;
import java.util.Scanner;

/**
 * <문제 요약>
 * 구해야 하는 것: 인접 부분 수열이 중복되지 않는 가장 작은 수열을 만들어라
 * 제약 사항: 1 2 3 으로만 만든다 
 * 문제 유형: 백트레킹, DFS
 * 요구 개념: DFS
 * 
 * <풀이법 요약>
 * 1. 길이가 1로 시작하는것이 가장 작기 때문에 1로 시작한다.
 * 2. 만들어 지는 문자열 뒤에 1 2 3을 붙여보면서 가능하면 붙인다.
 * 3. 길이가 원하는 길이가 되면 종료한다.
 * 
 * 인접된 수가 중복이 되는지 검사하는것은 너무 안나와서 구선생의 도움을 받았다.
 * 
 * 중복 찾는 조건
 * 1. 글자의 자릿수를 1개에서 부터 문자열의 길이 / 2까지 반복문을 돌린다.
 * 2. if (s.substring(start - i, end - i).equals(s.substring(start, end))) 이 조건문 잘 만든것 같다.
 * 3. if문에 걸리면 false를 리턴하고, 그렇지 않으면 start를 -1 시킨다. 그럼 자릿수가 1 ~ len/2자릿수 까지 중복체크를 할 수 있다.
 * 
 * 
 */

public class BOJ_G4_2661_좋은수열 {

	static boolean find;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		DFS(1, "1");
	}

	private static void DFS(int len, String s) {
		
		// 수열을 찾았을 경우 종료한다.
		if(find) return;
		
		// 찾고자 하는 수열의 길이만큰 찾았으면 출력 후 종료한다.
		if(N == len) {
			find = true;
			System.out.println(s);
			return;
		}
		// 1 ~ 3 까지 문자열 뒤에 붙여보면서 조건에 맞으면 재귀를 호출한다.
		for(int i = 1; i <= 3; i++) {
			if(isOk(s + i)) {
				DFS(len + 1, s + i);
			}
		}
		
	}

	private static boolean isOk(String str) {
		// 문자열 길이
		int len = str.length();
		// 반복할 위치
		int loop = len/2;
		// 중복을 검색할 부분수열의 시작점
		int start = len-1;
		// 중복 검색할 부분수열의 종료점
		int end = len;
		
		for(int i = 1; i <= loop; i++) {
			// 부분 문자열이 바로 뒤에 부분문자열과 
			if(str.substring(start - i, end - i).equals(str.substring(start, end))) {
				return false;
			}
			// 자릿수를 늘리기 위해 -1 해준다.
			start -= 1;
		}
		
		return true;
	}

}

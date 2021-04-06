package programmers;

/***
 * <문제 요약>
 * 구해야 하는 것 : 숫자를 이용하여 만들 수 있는 소수의 갯수
 * 제약 사항 : 0~9까지 숫자가 주어짐, String으로 옴
 * 문제 유형 : 구현, 완탐
 * 요구 개념 : 순열, subset
 * <풀이법 요약>
 * 0. 입력받은 숫자를 char형 배열로 저장
 * 1. 숫자의 갯수별로 1~N 개의 숫자 순열을 만듬
 * 2. 만들어진 숫자가 소수인지 판단.
 */


public class 소수찾기 {

	public static void main(String[] args) {
		String numbers = "17";
		String numbers1 = "011";
		String numbers2 = "321";
		System.out.println(solution(numbers1));
	}
	
	static int R, N;
	static boolean[] v;
	// 입력받은 캐릭터형 숫자들
	static char[] CNumbers;
	// 새로만든 숫자
	static char[] CNum;
	static int answer;
	// 한번 확인한 숫자는 확인하지 않는다.
	static boolean[] check = new boolean[100000000];
	
	
	public static int solution(String numbers) {
        answer = 0;
        N = numbers.length();
        CNumbers = new char[N];
        v = new boolean[N];
        // 0. 입력을 캐릭터형 배열로 받는다.
        CNumbers = numbers.toCharArray();

        // 1.선택하는 숫자의 갯수별로 1~N번 순환
        for(int i = 1; i <= N; i++) {
        	R = i;
        	CNum = new char[i];
        	nPr(0);
        }

        return answer;
    }
	
	//숫자를 만듬
	public static void nPr(int cnt) {
		//길이가 R인 숫자가 만들어지면 소수인지 판단
		if(cnt == R) {
			int num = Integer.parseInt(String.valueOf(CNum));
			System.out.println(num);
			// 아직 확인하지 않은 숫자이고 소수인지 판단.
			if(!check[num] && isPrime(num)) {
				check[num] = true;
				answer++;
			}
			return;
		}
		// nPr과 같다.
		for(int i = 0; i < N; i++) {
			if(v[i]) continue;
			v[i] = true;
			CNum[cnt] = CNumbers[i];
			nPr(cnt+1);
			v[i] = false;
		}
	}
	
	// 소수인지 판단하는 함수
	public static boolean isPrime(int num) {
		if(num > 1) {
			// 2 ~ 제곱근 까지 로 나눴을때 나눠지지 않으면 소수로 판단.
			for(int i = 2; i <= Math.sqrt(num); i++) {
				if(num % i == 0) return false;
			}
			return true;
		}
		return false;
	}

}

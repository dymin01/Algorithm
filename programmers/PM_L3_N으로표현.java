package programmers;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * <문제 요약>
 * 구해야 하는 것: N과 사칙연산을 이용하여 number을 만들 때 N을 가장 적게 사용한 갯수를 구해라
 * 유형: DP
 * 
 * <풀이법 요약>
 * 구글선생님의 도움을  받았습니다.
 * 1. set배열을 이용하여 N을 1 ~ 8개 사용했을 경우 만들어 지는 수를 저장한다.
 * 2. n개의 N을 이용하여 만들 수 있는 숫자를 arr에 모두 저장한다.
 * 3. 원하는 숫자를 만들었을 경우 N의 갯수를 출력한다.
 * 
 */

public class PM_L3_N으로표현 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PM_L3_N으로표현 sol = new PM_L3_N으로표현();
		
		//int N = sc.nextInt();
		int N = 5;
		//int number = sc.nextInt();
		int number = 12;
		System.out.println(sol.solution(N, number));
	}
	
	static int tempN;
	// N을 i개 사용하여 만들 수 있는 수를 저장하는 배열
	static Set<Integer>[] arr;
	
	public int solution(int N, int number) {
        int answer = -1;
        tempN = N;
        arr = new TreeSet[10];
        arr[0] = new TreeSet<>();
        // N을 1~8를 사용하여 만들 수 있는 수를 모두 만들어 본다.
        for(int i = 1; i <= 8; i++) {
        	arr[i] = new TreeSet<>();
        	getNum(i);
        	// 원하는 수를 만들었다면 출력한다.
        	if(arr[i].contains(number)) {
        		return i;
        	}
        }
        
        return answer;
    }

	private void getNum(int n) {
		if(arr[n] != null && !arr[n].isEmpty()) {
			return;
		}
		
		int num = 0;
		// n개의 N을 사용할 경우 사칙연산을 사용하지 않고 NN NNN 같은 수를 만들 수 있다.
		for(int i = 0; i < n; i++) {
			num = 10 * num + tempN;
		}
		
		arr[n].add(num);
		
		for(int i = 1; i < n; i++) {
			int j = n-i;
			// 사칙연산의 왼쪽 
			for(int l : arr[i]) {
				// 사칙연산의 오른쪽
				for(int r : arr[j]) {
					arr[n].add(l + r);
					arr[n].add(l - r);
					arr[n].add(l * r);
					if(r != 0) arr[n].add(l / r);
				}
			}
		}
	}
}

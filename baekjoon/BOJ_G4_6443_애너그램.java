package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 입력받은 단어의 철자들로 만들 수 있는 모든 단어를 출력하라.
 * 
 * 문제 유형
 * - 조합
 * - 문자열
 * 
 * 제약 사항
 * 단어의 길이는 20보다 작거나 같다.
 * 
 * <풀이 요약>
 * 조합이랑 NP랑 둘다 가능할것 같다.
 * 
 * 1. 주어진 알파벳을 정렬한다.
 * 2. NP를 이용해 다음 단어를 찾아 SB에 저장한다.
 * 3. 출력한다.
 * 
 * NP 까먹지 말자...!!!
 * 
 */

public class BOJ_G4_6443_애너그램 {
	
	static char[] strArr;
	static char[] res;
	static boolean[] visited;
	static Set<String> check;
	
	static int len;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		while(N-- != 0) {
			StringBuilder sb = new StringBuilder();
			strArr = br.readLine().toCharArray();
			len = strArr.length;
			// 알파벳 순서로 정렬하기 때문에 처음부터 정렬하고 시작한다.
			Arrays.sort(strArr);
			
			do {
				
				sb.append(String.valueOf(strArr)).append("\n");
				
			}while(NP(len-1, strArr));			
			System.out.print(sb.toString());
		}
		
	}

	private static boolean NP(int size, char[] strArr) {
		
		// step1
		// i보다 값이 작은 i-1 인덱스를 찾는다.
		int i = size;
		while(i > 0 && strArr[i] <= strArr[i-1]) i--;
		
		// i가 0은 더이상 높은 값은 없다. 내림차순이다. 종료한다.
		if(i <= 0) return false;
		 
		// step2
		// i-1 인덱스 값보다 큰 j를 찾아 교환한다.
		int j = size;
		while(strArr[i-1] >= strArr[j]) j--;
		
		char temp = strArr[i-1];
		strArr[i-1] = strArr[j];
		strArr[j] = temp;
		
		// step3
		// i와 k사이에 배열의 값을 오름차순으로 바꿔준다.
		int k = size;
		while(i < k) {
			temp = strArr[i];
			strArr[i] = strArr[k];
			strArr[k] = temp;
			i++;
			k--;
		}
		
		return true;
	}


}

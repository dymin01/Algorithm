package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 단어 뒤집어서 출력하기
 * 문제 핵심 요약 : 구현
 * <풀이법 요약> 
 * 1. 입력받은 글자가 < 테그일 경우 > 테그가 끝날때까지 그대로 더해준다.
 * 2. 입력받은 글자가 공백일경우 공백을 더한다.
 * 3. 입력받은 글자가 일반 단어일 경우 뒤집어서 더한다.
 */

public class BOJ_S3_17413 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String substr = null;
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < str.length(); i++) {
			// 테크일경우
			if(str.charAt(i) == '<') {
				for(int j = i; j < str.length(); j++) {
					// 테그가 끝날때 까지 그대로 저장
					if(str.charAt(j) == '>') {
						sb.append(str.substring(i, j+1));
						i = j;
						break;
					}
				}
				// 공백일 경우 그대로 더하기
			}else if(str.charAt(i) == ' '){
				sb.append(" ");
			}
			// 단어일 경우 짤라서 뒤집어 준다.
			else {
				int j = i;
				for(; j < str.length(); j++) {
					if(str.charAt(j) == ' ' || str.charAt(j) == '<') {
						break;
					}
				}
				substr = str.substring(i, j);
				i = j-1;
				sb.append(word(substr));
			}
		}
		System.out.println(sb);
		
	}
	
	// 단어 뒤집기
	public static String word(String str) {
		StringBuilder sb = new StringBuilder();
		for(int i = str.length()-1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}

}

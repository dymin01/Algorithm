package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_17413_단어뒤집기2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String substr = null;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '<') {
				for(int j = i; j < str.length(); j++) {
					if(str.charAt(j) == '>') {
						sb.append(str.substring(i, j+1));
						i = j;
						break;
					}
				}
			}else if(str.charAt(i) == ' '){
				sb.append(" ");
			}else {
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
	
	public static String word(String str) {
		StringBuilder sb = new StringBuilder();
		for(int i = str.length()-1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}

}

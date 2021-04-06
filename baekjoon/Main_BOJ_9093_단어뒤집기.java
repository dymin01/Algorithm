package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_9093_단어뒤집기 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
        	StringBuilder st = new StringBuilder();
        	String str = br.readLine();
        	String[] token = str.split(" ");
        	for(int j = 0; j < token.length; j++) {
        		for(int k = token[j].length()-1; k >= 0; k--) {
        			st.append(token[j].charAt(k));
        		}
        		st.append(" ");
        	}
        	System.out.println(st);
        }
	}
}

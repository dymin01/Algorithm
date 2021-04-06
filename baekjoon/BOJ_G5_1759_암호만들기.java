package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_1759_암호만들기 {

	static int L, C;
	static char[] arr;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		str = str.replace(" ", "");
		arr = str.toCharArray();
		Arrays.sort(arr);
		DFS(0, 0, 0, 0, "");
	}
	
	public static void DFS(int index, int cnt, int mo, int ja, String str) {
		if(cnt == L) {
			if(mo >= 1 && ja >= 2) {
				System.out.println(str);
			}
			return;
		}
		if(index == C) return;
		if(arr[index] == 'a' || arr[index] == 'e' || arr[index] == 'i' || arr[index] == 'o' || arr[index] == 'u') {
			DFS(index+1, cnt+1, mo+1, ja, str + arr[index]);
		}else {
			DFS(index+1, cnt+1, mo, ja+1, str + arr[index]);
		}
		DFS(index+1, cnt, mo, ja, str);
	}

}

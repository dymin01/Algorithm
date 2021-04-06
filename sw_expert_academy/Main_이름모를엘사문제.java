package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_이름모를엘사문제 {
	
	static int N;
	static int[] arr;
	static int[] sub;
	static int ans;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = new int[N+1];
			sub = new int[N+1];
			ans = 0;
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 1; i <= N; i++) {
				if(arr[i] == sub[i]) continue;
				change(i);
				ans++;
			}
			
			System.out.println("#" + t + " " + ans);
			
		}
	}
	
	public static void change(int num) {
		for(int i = num; i <= N; i+= num) {
			sub[i] = (sub[i] == 0 ? 1 : 0);
		}
	}
	
	public static boolean isSame(int[] temp) {
		for(int i = 1; i <= N; i++) {
			if(arr[i] != temp[i]) return false;
		}
		return true;
	}

}

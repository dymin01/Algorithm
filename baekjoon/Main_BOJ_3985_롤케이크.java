package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_3985_롤케이크 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[L+1];
		StringTokenizer st = null;
		int[] real = new int[N+1];
		int[] want = new int[N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			want[i] = k-p+1;
			for(int j  = p; j <= k; j++) {
				if(arr[j] == 0) {
					real[i]++;
					arr[j] = i;
				}
			}
		}
		
		int max = 0;
		int maxId = 0;
		int realmax = 0;
		int realId = 0;
		for(int i = 1; i <= N; i++) {
			if(max < want[i]) {
				max = want[i];
				maxId = i;
			}
			if(realmax < real[i]) {
				realmax = real[i];
				realId = i;
			}
		}
		System.out.println(maxId);
		System.out.println(realId);
		
		
	}

}

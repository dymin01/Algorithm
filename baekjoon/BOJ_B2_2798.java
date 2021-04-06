package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/***
 * <문제 요약> 
 * 구해야 하는 것 : 주어진 카드를 이용하여 숫자에 가장 가까운 합을 만든다.
 * 문제 핵심 요약 : 주어진 카드의 조합을 구한다.
 * <풀이법 요약> 
 * 1. 조합을 통해 카드를 3장 뽑는다.
 * 2. 뽑은 카드의 합이 M을 넘지 않고 가장 가까운 합을 구한다.
 */
public class BOJ_B2_2798 {

	static int N, M;
	static int[] arr;
	static int ans;
	static int[] num;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		num = new int[3];
		ans = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		nCr(0,0,0);
		System.out.println(ans);

	}
	
	// 조합을 통해 카드 3장을 뽑는다
	public static void nCr(int cnt, int start, int sum) {
		// 3장을 뽑기전 M을 넘으면 안된다.
		if(sum > M) {
			return;
		}
		// 3장을 뽑았을경우
		if(cnt == 3) {
			// M에 가장 가까운 합을 찾는다.
			ans = Math.max(ans, sum);
			return;
		}
		for(int i = start; i < N; i++) {
			nCr(cnt+1, i+1, sum + arr[i]);
		}
		
	}

}

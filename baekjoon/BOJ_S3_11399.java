package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 각 사람이 돈을 인출하는데 필요한 시간의 합의 최솟값
 * 문제 핵심 요약 : 시간이 적게 걸리는 사람먼저 시작
 * <풀이법 요약> 
 * 1. 입력받을 배열을 정렬한다.
 * 2. 뒤에 더해줄 만큼 곱해준 후 더한다.
 */

public class BOJ_S3_11399 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] p = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		// 정렬
		Arrays.sort(p);
		int ans = 0;
		
		// i는 몇번 등장하는지
		// j는 인덱스
		for(int i = N, j = 1; i > 0; i--) {
			ans += (i*p[j++]);
		}
		System.out.println(ans);
	}

}

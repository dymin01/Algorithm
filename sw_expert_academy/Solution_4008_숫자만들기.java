package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 숫자들과 사칙연산을 통해 최댓값과 최솟값의 차이를 구하라
 * 요구 개념/문제 유형 : 순열
 * 
 * <풀이법 요약>
 * 1. 순열을 이용하여 사칙연산의 순서를 구한다.
 * 2. 사칙연산의 순서에 따라 왼쪽에서 오른쪽으로 계산 하여 최댓값, 최솟값을 구한다.
 * 3. 최댓값에서 최솟값을 빼 원하는 값을 구한다.
 */

public class Solution_4008_숫자만들기 {

	static int N, max, min;
	static int[] nums;
	static char[] exp, sub;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			nums = new int[N];
			exp = new char[N-1];
			sub = new char[N-1];
			v = new boolean[N-1];
			int index = 0;
			
			st = new StringTokenizer(br.readLine());
			int plus = Integer.parseInt(st.nextToken());
			for(int i = 0; i < plus; i++) {
				exp[index++] = '+';
			}
			
			int minus = Integer.parseInt(st.nextToken());
			for(int i = 0; i < minus; i++) {
				exp[index++] = '-';
			}
			
			int mult = Integer.parseInt(st.nextToken());
			for(int i = 0; i < mult; i++) {
				exp[index++] = '*';
			}
			
			int div = Integer.parseInt(st.nextToken());
			for(int i = 0; i < div; i++) {
				exp[index++] = '/';
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			nPr(0);
			
			System.out.println("#" + t + " " + (max - min));
		}
	}
	
	public static void nPr(int cnt) {
		if(cnt == N-1) {
			int res = nums[0];
			for(int i = 0; i < N-1; i++) {
				if(sub[i] == '+') {
					res += nums[i+1];
				}
				else if(sub[i] == '-') {
					res -= nums[i+1];
				}
				else if(sub[i] == '*') {
					res *= nums[i+1];
				}
				else if(sub[i] == '/') {
					res /= nums[i+1];
				}
			}
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}
		for(int i = 0; i < N-1; i++) {
			if(v[i]) continue;
			v[i] = true;
			sub[cnt] = exp[i];
			nPr(cnt+1);
			v[i] = false;
		}
	}
}

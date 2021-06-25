package sw_expert_academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약> 문제 정의 : 상자를 쌓을 수 있는 최대 높이를 구하라 문제 유형 : 제약 사항 : N은 2이상 20이하 정수, 상자 한
 * 변의 길이는 1이상 10000 이하 <풀이 요약> DFSf로 풀어볼라 했지만 시간초과 나오는거 같고....
 *
 *
 */

public class Solution_4335_무인도탈출 {

	static class box {
		int width, height, depth;

		public box(int width, int height, int depth) {
			this.width = width;
			this.height = height;
			this.depth = depth;

		}

	}

	static ArrayList<box> boxs;
	static boolean[] v;
	static int N;

	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int t = 1; t <= T; t++) {
			// 상자 갯수
			N = Integer.parseInt(br.readLine());

			boxs = new ArrayList<>();
			v = new boolean[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				// 가로
				int width = Integer.parseInt(st.nextToken());
				// 세로
				int height = Integer.parseInt(st.nextToken());
				// 높이
				int depth = Integer.parseInt(st.nextToken());
				boxs.add(new box(width, height, depth));
			}

			temp = new int[N];
			DFS(0, 10001, 10001, 0);

//			for(int i = 0; i < N; i++) {
//				v[i] = true;
//				DFS(1, boxs.get(i).width, boxs.get(i).height, boxs.get(i).depth);
//				DFS(1, boxs.get(i).depth, boxs.get(i).width, boxs.get(i).height);
//				DFS(1, boxs.get(i).height, boxs.get(i).depth, boxs.get(i).width);
//				v[i] = false;
//			}

			System.out.println(ans);

		}

	}

	static int[] temp;

	// 밑 박스의 가로 세로
	public static void DFS(int cnt, int W, int H, int sum) {
		if (cnt == N) {
			if (ans < sum) {
				ans = sum;
			}
			return;
		}

		// 상자 중 한개 고르기.
		for (int i = 0; i < N; i++) {
			if (v[i])
				continue;

			int width = boxs.get(i).width;
			int height = boxs.get(i).height;
			int depth = boxs.get(i).depth;

			v[i] = true;

			// 넓이가 작고
			if (Math.max(W, H) >= Math.max(width, height) && Math.min(W, H) >= Math.min(width, height)) {
				temp[cnt] = depth;
				DFS(cnt + 1, width, height, sum + depth);
			}
			if (Math.max(W, H) >= Math.max(depth, width) && Math.min(W, H) >= Math.min(depth, width)) {
				temp[cnt] = height;
				DFS(cnt + 1, depth, width, sum + height);
			}
			if (Math.max(W, H) >= Math.max(height, depth) && Math.min(W, H) >= Math.min(height, depth)) {
				temp[cnt] = width;
				DFS(cnt + 1, height, depth, sum + width);
			}

			DFS(cnt + 1, W, H, sum);

			v[i] = false;

		}

	}

}

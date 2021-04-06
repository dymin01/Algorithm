package sw_expert_academy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_D3_3307_최장증가부분수열_2 {

	public static void main(String[] args) {
		//뒤에부터 1로 초기화
		// 자기보가 뒤쪽에 자기보다 큰 값 중 MAX + 1 이 자신의 값.
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = null;
				
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			sc.nextLine();
			int[][] arr = new int[N][2];
			st = new StringTokenizer(sc.nextLine());
			for(int i = 0; i < N; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = 1;
			}
			int ans = 0;
			for(int i = N-2; i >= 0; i--) {
				//우선순위 큐>??>?????
				int[][] temp = Arrays.copyOfRange(arr, i+1, N);
				Arrays.sort(temp, new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						return (o2[1] - o1[1]);
					}
				});
				
				for(int j = 0; j < temp.length; j++) {
					if(arr[i][0] > temp[j][0]) continue;
					arr[i][1] = temp[j][1]+1;
					ans = Math.max(arr[i][1], ans);
					break;
				}
				
			}
			System.out.println("#" + t + " " + ans);
		}
	}
}

package sw_expert_academy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_D5_3308_최장증가부분수열_hard {

	public static void main(String[] args) {
		//뒤에부터 1로 초기화
		// 자기보가 뒤쪽에 자기보다 큰 값 중 MAX + 1 이 자신의 값.
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = null;
				
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			sc.nextLine();
			int[] arr = new int[N];
			int[] LIS = new int[N];
			for(int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			int size = 0;
			for(int i = 0; i < N; i++) {
				int temp = Arrays.binarySearch(LIS, 0, size, arr[i]);
				temp = Math.abs(temp)-1;
				LIS[temp] = arr[i];
				if(temp == size) ++size;
			}
			
			System.out.println("#" + t + " " + size);
		}
	}
}

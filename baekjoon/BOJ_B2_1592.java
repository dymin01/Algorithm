package baekjoon;
import java.util.Scanner;
/***
 * <문제 요약> 
 * 구해야 하는 것 : 공을 M번 받았을때 공을 몇번 던지는지
 * 문제 핵심 요약 : 배열의 크기가 넘어가거나 0보다 작아질때 인덱스 조정
 * <풀이법 요약> 
 * 1. 공을 받은 횟수에 따라 홀수 짝수로 나눈다
 * 2. 홀수일경우 시계방향으로 공을 넘긴다.
 * 3. 짝수 일경우 반시계 방향으로 공을 넘긴다.
 * 4. 공을 받은 횟수가 M과 같으면 종료
 */

public class BOJ_B2_1592 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int L = sc.nextInt();
		
		int[] arr = new int[N];
		int now = 0;
		int ans = 0;
		
		while(true) {
			arr[now]++;
			if(arr[now] == M) break;
			//홀수 : 시계방향
			if(arr[now] % 2 != 0) {
				now += L;
				//배열의 크기를 넘어가지 않도록
				now = now%N;
			}
			//짝수 : 반시계방향
			else {
				now -= L;
				// 음수가 나오지 않도록
				if(now < 0) now += N;
			}
			ans++;
		}
		System.out.println(ans);
	}
}
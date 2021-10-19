package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_B1_1193_분수찾기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int X = Integer.parseInt(br.readLine());
		
		
		int cnt_cross = 1;
		int prev_cnt_sum = 0;
		
		while(true) {
			
			// 직전 대각선 누적함 + 해당 대각선의 개수를 이용한 범위 확인
			if(X <= prev_cnt_sum + cnt_cross) {
				
				// 대각선의 갯수가 홀수이면
				if(cnt_cross % 2 == 1) {
					// 분모가 큰것부터 시작
					// 분모 == 대각선 개수 - (X번째 - 직전대각선까지의 누적합 - 1)
					// 분자 == X번째 - 직전대각선까지의 누적합
					System.out.println((cnt_cross - (X - prev_cnt_sum - 1)) + "/" + (X - prev_cnt_sum));
					break;
				}
				
				else {
					// 대각선의 개수가 짝수이면
					// 짝수 반대로
					System.out.println((X-prev_cnt_sum) + "/" + (cnt_cross - (X - prev_cnt_sum - 1)));
					break;
				}
				
			}else {
				prev_cnt_sum += cnt_cross;
				cnt_cross++;
			}
		}

	}

}

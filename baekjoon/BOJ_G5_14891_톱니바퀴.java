package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * <문제 요약>
 * 문제 정의
 * - 총 K번 회전시킨 이후에 네 톱니바퀴의 점수의 합을 출력해라.
 * 
 * 문제 유형
 * - 시뮬레이션
 * 
 * 제약 사항
 * 4 톱니바퀴는 톱니를 8개 가지고 있다.
 * 12시 방향부터 시계방향 순서대로 주어진다.
 * N극은 0, S극은 1이다.
 * 
 * <풀이 요약>
 * 톱니바퀴를 바꾸고 확인하는것이 아니라 바뀌는지 확인하고 -> 바꿔야 한다.
 * 
 * 1. 톱니바퀴가 돌아가야 하는지 확인한다.
 * 2. 왼쪽과 오른쪽 톱니바퀴를 확인하여 톱니바퀴의 극이 다르면 돌아가는 방향을 저장한다.
 * 3. 톱니바퀴가 회전했다면 그 톱니바퀴의 인접 톱니바퀴의 돌아가는 방향을 저장한다.
 * 4. 톱니바퀴를 돌린다.
 * 4-1. 돌아간 방향이 1이면 시계 방향 -> 배열에서 인덱스 + 방향
 * 4-2. 돌아간 방향이 -1이면 반시계 방향 -> 배열에서 인덱스 - 방향
 * 5. 톱니바퀴를 모두 돌렸다면 0에 있는 값이 1이면 1 2 4 8 순으로 더해준다.
 * 
 */

public class BOJ_G5_14891_톱니바퀴 {

	static int[][] gears;
	static int[] isRotate;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		gears = new int[4][8];
		
		// 톱니바퀴 초기화
		for(int i = 0; i < 4; i++) {
			String str = br.readLine();
			for(int j = 0; j < 8; j++) {
				gears[i][j] = str.charAt(j) - '0';
			}
		}
		
		// 회전 횟수
		int K = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			// 돌릴 톱니바퀴
			int gearIdx = Integer.parseInt(st.nextToken()) - 1;
			// 돌릴 방향
			int direction = Integer.parseInt(st.nextToken());
			
			// 회전을 했는지 했다면 어느 방향인지 저장하는 배열
			isRotate = new int[4];
			// 톱니바퀴가 회전 하는지 확인
			checkRotateGrear(gearIdx, direction);
			// 기어 돌리기
			ratateGear();
		}
		
		int ans = 0;
		for(int i = 0; i < 4; i++) {
			if(gears[i][0] == 1) ans += Math.pow(2, i);
		}
		
		System.out.println(ans);
		

	}

	// 톱니바퀴 돌리기
	private static void ratateGear() {
		for(int i = 0; i < 4; i++) {
			// 0이 아니면 돌았다는 뜻
			if(isRotate[i] != 0) {
				int[] temp = new int[8];
				
				for(int j = 0; j < 8; j++) {
					// -1이면 반시계, 1이면 시계
					// 배열에서 보면 인덱스를 -1, +1 해준것과 같다
					int idx = j + isRotate[i];
					if(idx == -1) idx = 7;
					else if(idx == 8) idx = 0;
					
					temp[idx] = gears[i][j];
				}
				
				gears[i] = temp;
			}
		}
		
	}

	// 톱니바퀴를 돌려야 하는지 확인
	private static void checkRotateGrear(int gearIdx, int direction) {
		
		// 톱니바퀴를 회전
		isRotate[gearIdx] = direction;
		
		int left = gearIdx - 1;
		int right = gearIdx + 1;
		
		// 왼쪽 톱니바퀴 확인
		if(left >= 0 && isRotate[left] == 0) {
			// 마주보는 극이 다르면
			if(gears[left][2] != gears[gearIdx][6]) {
				checkRotateGrear(left, direction * -1);
			}
		}
		
		// 오른쪽 톱니바퀴 확인
		if(right <= 3 && isRotate[right] == 0) {
			// 마주보는 극이 다르면
			if(gears[right][6] != gears[gearIdx][2]) {
				checkRotateGrear(right, direction * -1);
			}
		}
		
	}

}

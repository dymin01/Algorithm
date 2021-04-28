package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 야구 게임의 최대 점수를 얻어라
 * 제약 사항: 4번 타자는 1번이 고정
 * 문제 유형: 브루트포스, 구현
 * 요구 개념: 순열? NP
 * 
 * <풀이법 요약>
 * 1. NP를 사용하여 선수들의 순서를 정한다.
 * 2. 선수들의 순서에 맞춰 이닝별 경기를 시작한다.
 * 3-1. out카운트가 3개가 되면 다음 이닝으로 넘어간다.
 * 3-2. 공을 쳤을경우 각각 베이스에 루타 만큼 더하고 3을 넘어가면 score에 증가시킨다.
 * 4. 모든 이닝이 끝났을때 ans와 score과 비교해서 큰 값을 저장한다.
 */



public class BOJ_G4_17281_야구공 {

	static int[] target;
	static int N;
	static int[][] map;
	
	static int ans;
	
	static boolean[] base;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());

		map = new int[N+1][10];
		
		ans = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		
		int[] player = new int[9];
		target = new int[8];
		for(int i = 0; i < 8; i++) {
			target[i] = i+2;
		}
		
		do {
			// 타순을 정할 때 4번타자는 1번이 고정한다.
			for(int i = 0; i < 3; i++) {
				player[i] = target[i];
			}
			player[3] = 1;
			for(int i = 4; i < 9; i++) {
				player[i] = target[i-1];
			}

			// 정해진 타순으로 경기를 진행한다.
			play(player);
			
		}while(np(target.length-1));
		
		System.out.println(ans);
		
	}

	private static void play(int[] player) {
		int score = 0;
		// 타순
  		int idx = 0;
		for(int i = 0; i < N; i++) {
 			int out = 0;
    		base = new boolean[3];
			while(true) {
				if(idx == 9) idx = 0;
				// 아웃되는경우
				if(map[i][player[idx]-1] == 0) {
					if(++out == 3) {
						idx++;
						break;
					}
				}
				// 공격하는경우
				else {
					score += hit(map[i][player[idx]-1]);
				}
				idx++;
			}
		}
		ans = Math.max(ans, score);
		
	}

	// 안타인 경우
	private static int hit(int num) {
		int score = 0;
		for(int i = 2; i >= 0; i--) {
			// 3루 2루 1루에 선수가 있는경우
			if(base[i]) {
				
				if(i+num >= 3) score++;
				
				else base[i+num] = true;
				
				base[i] = false;
			}
		}
		if(num == 4) score++;
		else base[num-1] = true;
		
		return score;
	}

	// np를 이용하여 타순을 정한다.
	private static boolean np(int size) {
		int i = size;
		while(i > 0 && target[i-1] > target[i]) i--;
		
		if(i == 0) {
			return false;
		}
		
		int j = size;
		while(target[i-1] > target[j]) j--;
		
		int temp = target[i-1];
		target[i-1] = target[j];
		target[j] = temp;
		
		int k = size;
		while(i < k) {
			temp = target[i];
			target[i] = target[k];
			target[k] = temp;
			i++;
			k--;
		}
		
		return true;
	}

}

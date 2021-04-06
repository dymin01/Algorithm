package baekjoon;
import java.util.Scanner;

public class Main_BOJ_9663_NQueen {

	static int N;
	static int ans;
	static int[] col;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		ans = 0;
        //1행의 i열에 놓는다.
		for(int i = 1; i <= N; i++) {
			col = new int[15+1];
			col[1] = i;
			DFS(1);
		}
		System.out.println(ans);
	}
	
	public static void DFS(int row) {
		//행 끝까지 탐색 끝나면 ++;
		if(row == N) {
			ans++;
		}
		else {
			// 2열부터 놓을 수 있는 열을 찾는다.
			for(int i = 1; i <= N; i++) {
				col[row+1] = i;
				// 퀸을 놓을 수 있으면 다음 행을 찾는다.
				if(isPossible(row+1)) {
					DFS(row+1);
				}else {
					col[row+1] = 0;
				}
			}
		}
		col[row] = 0;
	}

	public static boolean isPossible(int c) {
		for(int i = 1; i < c; i++) {
			// 같은 열 확인
			if(col[i] == col[c]) {
				return false;
			}
			//대각선
			if(Math.abs(col[i] - col[c]) == Math.abs(i - c)) {
				return false;
			}
		}
		return true;
	}

}

package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G2_2629_양팔저울 {
	
	static int[] chus;
	static int numOfchu;
	static boolean[][] isPossible;
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		numOfchu = Integer.parseInt(br.readLine());
		chus = new int[numOfchu + 1];
		
		st = new StringTokenizer(br.readLine());
		// 추를 배열에 저장
		for(int i = 0; i < numOfchu; i++) {
			chus[i] = Integer.parseInt(st.nextToken());
		}
		
		isPossible = new boolean[31][50000];
		
		int numOfBoll = Integer.parseInt(br.readLine());
		int[] bolls = new int[numOfBoll];
		st = new StringTokenizer(br.readLine());
		// 구슬을 배열에 저장
		for(int i = 0; i < numOfBoll; i++) {
			bolls[i] = Integer.parseInt(st.nextToken());
		}
		DFS(0, 0);
		for(int i = 0; i < numOfBoll; i++) {
			if(isPossible[numOfchu][bolls[i]]) {
				System.out.print("Y ");
			}else {
				System.out.print("N ");
			}
		}

	}

	private static void DFS(int cnt, int weight) {
		if(isPossible[cnt][weight]) return;
		isPossible[cnt][weight] = true;
		if(cnt >= numOfchu) return;
		
		DFS(cnt+1, weight + chus[cnt]);
		DFS(cnt+1, weight);
		DFS(cnt+1, Math.abs(weight - chus[cnt]));
		
	}

}

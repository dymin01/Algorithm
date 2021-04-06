package sw_expert_academy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5648_원자소멸시뮬레이션 {

	
	static int[][] map;
	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		for(int t = 1; t <= T; t++) {
			
			int N = Integer.parseInt(br.readLine());
			ArrayList<int[]> atoms = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				int D = Integer.parseInt(st.nextToken());
				int K = Integer.parseInt(st.nextToken());
				atoms.add(new int[] {X,Y,D,K});
			}
			int ans = 0;
			
			
			System.out.println("#" + t + " " + ans);
			
		}
		
		

	}

}

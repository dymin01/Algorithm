package baekjoon;
import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_S1_2564_경비원 {
	
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int W, H, S;
	static int[][] map;
	static ArrayList<int[]> points = new ArrayList<>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		W = sc.nextInt();
		H = sc.nextInt();
		S = sc.nextInt();
		map = new int[H+1][W+1];
		points.clear();
		for(int i = 0; i < S; i++) {
			int first = sc.nextInt();
			int second = sc.nextInt();
			
			int[] cur = direction(first, second);
			points.add(cur);
		}
		int er = sc.nextInt();
		int ec = sc.nextInt();
		int[] ecur = direction(er, ec);
		
		int cnt = 0;
		for(int[] point : points) {
			int result = goClock(point, ecur);
			cnt += Math.min(result, 2*(W + H) - result);
			
		}
		
		System.out.println(cnt);
		
	}

	public static int goClock(int[] point, int[] ecur) {
		int count = 0;
		int r = point[0];
		int c = point[1];
		int er = ecur[0];
		int ec = ecur[1];
		int d = ecur[2];
		
		for(int i = 0; i < 2*(H+W); i++) {
			if(r == er && c == ec) return count;
			int nr = er + dr[d];
			int nc = ec + dc[d];
			if(!(nr >= 0 && nr <= H && nc >= 0 && nc <= W)) {
				d = (d+1)%4;
			}
			er += dr[d];
			ec += dc[d];
			count++;
			
		}
		
		
		return count;
	}

	public static int[] direction(int first, int second) {
		
		int r = 0;
		int c = 0;
		int dir = 0;
		
		if(first == 1) {
			r = 0; 
			c = second;
			dir = 0;
		}else if(first == 2) {
			r = H;
			c = second;
			dir = 2;
		}else if(first == 3) {
			r = second;
			c = 0;
			dir = 3;
		}else if(first == 4) {
			r = second;
			c = W;
			dir = 1;
		}
		
		int[] loc = new int[] {r, c, dir};
		return loc;
	}

}

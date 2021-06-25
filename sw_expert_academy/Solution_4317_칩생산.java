package sw_expert_academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4317_칩생산 {

	static int[][] map;
	static boolean[][] v;
	
	static int H, W;
	
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			v = new boolean[H][W];
			
			ans = 0;
			
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						v[i][j] = true;
					}
				}
			}
			
			DFS(0, 0, 0);
			
			System.out.println(ans);
		}

	}
	
	public static void DFS(int r, int c, int cnt) {
		
		for(int i = r; i < H-1; i++) {
			for(int j = 0; j < W-1; j++) {
				
				// 다 4칸이 다 0이다.
				if(v[i][j] || v[i][j+1] || v[i+1][j] || v[i+1][j+1] ) continue;
				
				v[i][j] = true;
				v[i][j+1] = true;
				v[i+1][j] = true;
				v[i+1][j+1] = true;
				
//				for(int a = 0; a < H; a++) {
//					for(int b = 0; b < W; b++) {
//						if(map[a][b] == 1) System.out.print("1 ");
//						else if(v[a][b]) System.out.print("2 ");
//						else System.out.print("0 ");
//					}
//					System.out.println();
//				}
//				System.out.println();
				
				DFS(i, j, cnt+1);
				
				v[i][j] = false;
				v[i][j+1] = false;
				v[i+1][j] = false;
				v[i+1][j+1] = false;
				
			}
		}
		
		if(ans < cnt) {
			ans = cnt;
		}
		
	}

}


/*

#include <iostream>
using namespace std;
int h, w;
int map[25][10];
int used[25][10];
int memo[25][(1 << 10) + 1];
int isClean(int y, int x)
{
	if (x >= w - 1) return 0;
	if (map[y][x] + map[y + 1][x] + map[y][x + 1] + map[y + 1][x + 1] > 0) return 0;
	if (used[y][x] + used[y + 1][x] + used[y][x + 1] + used[y + 1][x + 1] > 0) return 0;
	return 1;
}
void setNemo(int y, int x)
{
	used[y][x] = 2;
	used[y][x + 1] = 1;
	used[y + 1][x] = 1;
	used[y + 1][x + 1] = 1;
}
void clearNemo(int y, int x)
{
	used[y][x] = 0;
	used[y][x + 1] = 0;
	used[y + 1][x] = 0;
	used[y + 1][x + 1] = 0;
}
int getState(int y, int x) {
	int state = 0;
	for (int i = 0; i < w - 1; i++) {
		state <<= 1;
		if (used[y][i] == 2) state |= 0x1;
	}
	return state;
}
int getMaxChipCnt(int stage)
{
	int dy = stage / w;
	int dx = stage % w;
	int state = 0;
	if (dy == h - 2 && dx == w - 1) return 0;
	if (dx == w - 1) {
		state = getState(dy, dx);
		if (memo[dy][state] != 0) return memo[dy][state];
	}
	int maxi = 0;
	if (isClean(dy, dx)) {
		setNemo(dy, dx);
		maxi = getMaxChipCnt(stage + 1) + 1;
		clearNemo(dy, dx);
	}
	int ret = getMaxChipCnt(stage + 1);
	if (maxi < ret)  maxi = ret;
	if (dx == w - 1) {
		memo[dy][state] = maxi;
	}
	return maxi;
}
int main()
{
	//freopen("text.txt", "r", stdin);
	int tcCnt, ret;
	cin >> tcCnt;
	for (int tc = 1; tc <= tcCnt; tc++) {
		cin >> h >> w;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				cin >> map[x][y];
			}
		}
		//가로 세로 변경
		int temp = h;
		h = w;
		w = temp;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < (1 << w); x++) {
				memo[y][x] = 0;
			}
		}
		ret = getMaxChipCnt(0);
		cout << "#" << tc << " " << ret << "\n";
	}
	return 0;
}
  
  
 */

package sw_expert_academy;

public class Solution_4340_파이프연결 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
00

/*

#include <stdio.h>
int n;
int map[60][60];
const int PipeCnt = 7;
const int BangCnt = 4;
struct Node {
	int y, x, input, type;
};
Node path[3000];
int minTouch[60][60][10];
int used[60][60];
int direct[4][2] = { -1, 0, 1, 0, 0, 1, 0, -1 };
enum { U, D, R, L };
int minLev;
int revBang[BangCnt];
void init()
{
	minLev = 21e8;
	for (int y = 0; y < 60; y++) {
		for (int x = 0; x < 60; x++) {
			map[y][x] = 0;
			used[y][x] = 0;
		}
	}
	for (int a = 0; a < 60; a++) {
		for (int b = 0; b < 60; b++) {
			for (int c = 0; c < 10; c++) {
				minTouch[a][b][c] = 21e8;
			}
		}
	}
}
void savePath(int lev)
{
	for (int i = 0; i < lev; i++) {
		int dy = path[i].y;
		int dx = path[i].x;
		int input = path[i].input;
		minTouch[dy][dx][input] = i + 1;
	}
}
void dfs(int lev, int dy, int dx, int inputBang)
{
	int left = n * 2 - dy - dx + 1;
	if (lev + left >= minLev) return;
	if (minTouch[dy][dx][inputBang] < lev) return;
	if (dy == n && dx == n + 1) {
		minLev = lev;
		savePath(lev);
		return;
	}
	if (map[dy][dx] == 0) return;
	if (map[dy][dx] <= 2) { //길쭉이 파이프
		int nextBang = revBang[inputBang];
		int ny = dy + direct[nextBang][0];
		int nx = dx + direct[nextBang][1];
		if (used[ny][nx] == 0) {
			used[ny][nx] = 1;
			path[lev] = { ny, nx, inputBang };
			dfs(lev + 1, ny, nx, inputBang);
			used[ny][nx] = 0;
		}
	}
	else { //ㄴ 파이프
		int nextBangA = L, nextBangB = R;
		int ny, nx;
		if (inputBang == L || inputBang == R) {
			nextBangA = U;
			nextBangB = D;
		}
		ny = dy + direct[nextBangA][0];
		nx = dx + direct[nextBangA][1];
		if (used[ny][nx] == 0) {
			used[ny][nx] = 1;
			path[lev] = { ny, nx, revBang[nextBangA] };
			dfs(lev + 1, ny, nx, revBang[nextBangA]);
			used[ny][nx] = 0;
		}
		ny = dy + direct[nextBangB][0];
		nx = dx + direct[nextBangB][1];
		if (used[ny][nx] == 0) {
			used[ny][nx] = 1;
			path[lev] = { ny, nx, revBang[nextBangB] };
			dfs(lev + 1, ny, nx, revBang[nextBangB]);
			used[ny][nx] = 0;
		}
	}
	path[lev] = { 0 };
}
void setting()
{
	//리버스, 누군가 오른쪽으로 다음 노드로 들어갔다면
	//들어온 후에는 왼쪽으로 부터 들어온것임
	revBang[L] = R;
	revBang[R] = L;
	revBang[U] = D;
	revBang[D] = U;
}
int main()
{
	//freopen("text.txt", "r", stdin);
	setting();
	int tc;
	scanf("%d", &tc);
	for (int i = 1; i <= tc; i++) {
		init();
		scanf("%d", &n);
		for (int y = 1; y <= n; y++) {
			for (int x = 1; x <= n; x++) {
				scanf("%d", &map[y][x]);
			}
		}
		map[n][n + 1] = 1;
		used[1][1] = 1;
		dfs(0, 1, 1, L);
		printf("#%d %d\n", i, minLev);
	}
	return 0;
}

*/
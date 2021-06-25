package sw_expert_academy;

public class 양탄자문제 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


/*

#include <iostream>
using namespace std;
char memo[1 << 21][4][5];
char map[4][5];
int h = 4;
int w = 5;
int tarY, tarX;
int direct[4][2] = { -1, 0, 1, 0, 0, 1, 0, -1 };
int setBit(int state, int y, int x)
{
	int index = y * w + x;
	state |= (1 << index);
	return state;
}
int clearBit(int state, int y, int x)
{
	int index = y * w + x;
	state &= ~(1 << index);
	return state;
}
int isSet(int state, int y, int x)
{
	int index = y * w + x;
	return (state >> index) & 0x1;
}
int getMini(int state, int dy, int dx)
{
	if (isSet(state, dy, dx)) return 99;
	state = setBit(state, dy, dx);
	if (memo[state][dy][dx] != 0) return memo[state][dy][dx];
	if (dy == tarY && dx == tarX) return 0;
	int min = 99;
	for (int t = 0; t < 4; t++) {
		int ny = dy + direct[t][0];
		int nx = dx + direct[t][1];
		if (ny < 0 || nx < 0 || ny >= h || nx >= w) continue;
		if (map[ny][nx] == '#') continue;
		int ret = getMini(state, ny, nx);
		if (ret < min) min = ret;
	}
	memo[state][dy][dx] = min + 1;
	return memo[state][dy][dx];
}
int main()
{
	//freopen("text.txt", "r", stdin);
	int startY, startX;
	for (int y = 0; y < h; y++) {
		for (int x = 0; x < w; x++) {
			cin >> map[y][x];
			if (map[y][x] == '#') continue;
			if (map[y][x] == 'A') startY = y, startX = x;
			if (map[y][x] == 'B') tarY = y, tarX = x;
			map[y][x] = '_';
		}
	}
	int ret = getMini(0, startY, startX);
	if (ret >= 99) {
		cout << "impossible";
		return 0;
	}
	cout << ret << "회";
	return 0;
}

*/
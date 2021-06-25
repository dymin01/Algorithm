package baekjoon;

public class 양팔저울 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


/*

#include <iostream>
using namespace std;
int n;
int chu[30];
int memo[30][300000];
void dfs(int lev, int weightSum)
{
	if (memo[lev][weightSum] == 1) return;
	memo[lev][weightSum] = 1;
	if (lev == n) return;
	dfs(lev + 1, weightSum + chu[lev]);
	dfs(lev + 1, weightSum);
	dfs(lev + 1, weightSum - chu[lev]);
}
int main()
{
	//freopen("text.txt", "r", stdin);
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> chu[i];
	}
	dfs(0, 0);
	int cnt = 0;
	for (int i = 1; i < 300000; i++) {
		if (memo[n][i] == 1) cnt++;
	}
	cout << cnt << "\n";
	for (int i = 1; i < 300000; i++) {
		if (memo[n][i] == 0) continue;
		cout << i << " ";
	}
	return 0;
}

*/
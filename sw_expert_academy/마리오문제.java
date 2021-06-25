package sw_expert_academy;

public class 마리오문제 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}



/*

#include <iostream>
using namespace std;
int memo[1000];
int map[1000];
int getPoint(int n)
{
	if (n == 0) return 0;
	if (n < 0) return -999999;
	if (memo[n] != 0) return memo[n];
	int a = getPoint(n - 2);
	int b = getPoint(n - 7);
	int ret = a;
	if (ret < b) ret = b;
	memo[n] = ret + map[n];
	return memo[n];
}
int main()
{
	int n;
	cin >> n;
	for (int i = 1; i <= n; i++) cin >> map[i];
	int max = -21e8;
	//(n + 1) ~ (n + 5)
	for (int i = 1; i <= 5; i++) {
		int ret = getPoint(n + i);
		if (ret > max) max = ret;
	}
	cout << max;
	return 0;
}

*/
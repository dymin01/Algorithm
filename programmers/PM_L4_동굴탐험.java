package programmers;

import java.util.ArrayList;
import java.util.Arrays;

public class PM_L4_동굴탐험 {

	public static void main(String[] args) {
		PM_L4_동굴탐험 sol = new PM_L4_동굴탐험();

		int n = 9;
		//int[][] path = { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } };
		int[][] path = {{8,1},{0,1},{1,2},{0,7},{4,7},{0,3},{7,5},{3,6}};
		//int[][] order = { { 8, 5 }, { 6, 7 }, { 4, 1 } };
		int[][] order = { { 4, 1 }, { 5, 2 }};
		//int[][] order = {{4,1},{8,7},{6,5}};

		System.out.println(sol.solution(n, path, order));

	}

	static boolean[] canMove;
	static boolean[] v;
	static int[][] sorder;
	static ArrayList<Integer>[] list;

	public static boolean solution(int n, int[][] path, int[][] order) {

		// 인덱스 : 수, 값 : 인덱스보다 먼저 와야 하는 수
		// != 0 이면 아직 앞에 수를 방문하지 않음.
		v = new boolean[n];
		canMove = new boolean[n];
		sorder = order;

		list = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}

		Arrays.fill(canMove, true);
		for (int i = 0; i < order.length; i++) {
			canMove[order[i][1]] = false;
		}

		for (int i = 0; i < path.length; i++) {
			int a = path[i][0];
			int b = path[i][1];
			list[a].add(b);
			list[b].add(a);
		}

		ArrayList<Integer> nextNode = new ArrayList<>();

		int cur = 0;

		for (int a : list[cur]) {
			nextNode.add(a);
		}
		v[0] = true;

		// go
		return process(nextNode);

	}

	public static boolean process(ArrayList<Integer> canmove) {
		for (int i = 0; i < canmove.size(); i++) {
			int a = canmove.get(i);
			if (canMove[a] && !v[a]) {
				canmove.remove(i);
				i = -1;
				v[a] = true;
				checkOrder(a);
				for (int j = 0; j < list[a].size(); j++) {
					if (v[list[a].get(j)])
						continue;
					canmove.add(list[a].get(j));
				}
			}
		}

		if (canmove.size() != 0) {
			return false;
		}

		return true;
	}

	public static void checkOrder(int a) {
		for (int i = 0; i < sorder.length; i++) {
			if (sorder[i][0] == a) {
				canMove[sorder[i][1]] = true;
			}
		}
	}
}

package baekjoon;
import java.util.Scanner;

public class Main_BOJ_2804_크로스워드만들기 {

	static char[][] ans;
	static int Apos, Bpos;
	static int N, M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String A = sc.next();
		String B = sc.next();
		
		N = A.length();
		M = B.length();
		
		ans = new char[M][N];
		findPos(A, B);
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(i == Bpos) {
					ans[i][j] = A.charAt(j);
					continue;
				}
				if(j == Apos) {
					ans[i][j] = B.charAt(i);
				}else {
					ans[i][j] = '.';
				}
			}
		}
		print();
	}
	
	public static void print() {
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(ans[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void findPos(String A, String B) {
		for(int i = 0; i < N; i++) {
			char a = A.charAt(i);
			for(int j = 0; j < M; j++) {
				if(a == B.charAt(j)) {
					Apos = i;
					Bpos = j;
					return;
				}
			}
		}
	}

}

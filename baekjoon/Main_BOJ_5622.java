package baekjoon;
import java.util.Scanner;

public class Main_BOJ_5622 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.next();
		
		int ans = 0;
		int[] num = new int[26];
		//A B C = 3
		//D E F = 4
		//G H I = 5
		//J K L = 6
		//M N O = 7
		//P Q R S = 8
		//T U V = 9
		//W X Y Z = 10
		for(int i = 0; i < str.length(); i++) {
			
		}
/*
		// 1 = 2, 2 = 3, 3 = 4 ...
		int ans = 0;
		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			case 'A':
			case 'B':
			case 'C':
				ans += 3;
				break;
			case 'D':
			case 'E':
			case 'F':
				ans += 4;
				break;
			case 'G':
			case 'H':
			case 'I':
				ans += 5;
				break;
			case 'J':
			case 'K':
			case 'L':
				ans += 6;
				break;
			case 'M':
			case 'N':
			case 'O':
				ans += 7;
				break;
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
				ans += 8;
				break;
			case 'T':
			case 'U':
			case 'V':
				ans += 9;
				break;
			case 'W':
			case 'X':
			case 'Y':
			case 'Z':
				ans += 10;
				break;
			}
		}
		System.out.println(ans);
*/
		

	}

}

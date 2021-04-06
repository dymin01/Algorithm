package baekjoon;
import java.util.Scanner;

public class Main_BOJ_1157 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		str = str.toUpperCase();
		//System.out.println(str);
		
		int[] num = new int[26];
		
		for(int i = 0; i < str.length(); i++) {
			num[(int)str.charAt(i)-(int)'A']++;
		}
		int max = -1;
		int idx = 0;
		boolean check = false;
		for(int i = 0; i < num.length; i++) {
			if(max < num[i]) {
				max = num[i];
				idx = i;
				check = true;
			} else if(max == num[i]) {
				check = false;
			}
		}
		
		if(check) {
			System.out.println((char)('A'+idx));
		} else {
			System.out.println('?');
		}
	}
}

package baekjoon;
import java.util.Arrays;
import java.util.Scanner;

public class Main_BOJ_10809 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		int[] num = new int[26];
		Arrays.fill(num, -1);
		for(int i = 0; i < str.length(); i++) {
			//System.out.println((int)str.charAt(i)-97);
			if(num[((int)str.charAt(i))-97] == -1) {
				num[((int)str.charAt(i))-97] = i;
			}
		}
		
		for(int i = 0; i < num.length; i++) {
			System.out.printf("%d ", num[i]);
		}

	}

}

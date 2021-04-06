package baekjoon;
import java.util.Scanner;

public class Main_BOJ_1152 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine().trim();
		//System.out.println(str);
		if(str.isEmpty())
			System.out.println(0);
		else
			System.out.println(str.split(" ").length);
	}

}

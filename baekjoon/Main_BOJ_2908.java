package baekjoon;
import java.util.Scanner;

public class Main_BOJ_2908 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String numA = sc.next();
		String numB = sc.next();
		
		String newA = reversNum(numA);
		String newB = reversNum(numB);
		
		if(newA.compareTo(newB) > 0) {
			System.out.println(newA);
		}else {
			System.out.println(newB);
		}

	}
	
	public static String reversNum(String str) {
		
		String temp = "";
		
		for(int i = str.length()-1; i >= 0; i--) {
			temp+=str.charAt(i);
		}
		return temp;
	}

}

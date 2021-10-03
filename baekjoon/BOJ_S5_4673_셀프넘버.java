package baekjoon;

public class BOJ_S5_4673_셀프넘버 {
	
	static boolean[] V;

	public static void main(String[] args) {
		
		V = new boolean[10001];
		
		for(int i = 1; i <= 10000; i++) {
			
			if(!V[i]) System.out.println(i);
			
			d(i);
			
		}
		

	}

	private static void d(int num) {
		
		int dn = num;
		
		while(num > 0) {
			dn += num % 10;
			num /= 10;
		}
		
		if(dn > 10000 || V[dn]) return;
		V[dn] = true;
		d(dn);
		
	}

}

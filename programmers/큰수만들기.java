package programmers;

public class 큰수만들기 {

	public static void main(String[] args) {
		큰수만들기 sol = new 큰수만들기();
		String number = "1924";
		int k = 2;
		System.out.println(sol.solution(number, k));
	}
	
	
	public String solution(String number, int k) {
		String answer = "";
		
		StringBuilder num = new StringBuilder(number);
		
		int cntErase = 0;
		
		int start = 0;
		int next = 1;
		
		while(true) {
			if(cntErase == k) break;
			
			if(num.charAt(start) < num.charAt(next)) {
				num.deleteCharAt(start);
				start = 0;
				next = 1;
				cntErase++;
			}else {
				start++;
				next++;
			}
			if(next == num.length()) {
				int temp = k-cntErase;
				num.delete(next-temp, next);
				break;
			}
		}
		answer = num.toString();
        return answer;
    }

}

package programmers;

public class 큰수만들기2 {

	public static void main(String[] args) {
		큰수만들기2 sol = new 큰수만들기2();
		String number = "12398";
		int k = 3;
		System.out.println(sol.solution(number, k));
	}
	
	
	public String solution(String number, int k) {
		//String answer = "";
		
		StringBuilder answer = new StringBuilder();

		int idx = 0;
		int comp = 0;
		
		for(int i = 0; i < number.length()-k; i++) {
			comp = 0;
			for(int j = idx; j <= i+k; j++) {
				if(comp < number.charAt(j)-'0') {
					comp = number.charAt(j)- '0';
					
					idx = j+1;
				}
			}
			answer.append(comp);
		}
        return answer.toString();
    }

}

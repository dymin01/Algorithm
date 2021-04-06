package programmers;

public class 주식가격 {

	public static void main(String[] args) {
		
		int[] prices = {1, 2, 3, 2, 3};
		int[] answer = Solution(prices);
		for(int a : answer) {
			System.out.print(a + " ");
		}
		
	}
	
	public static int[] Solution(int[] prices) {
		int[] answer = {};
		answer = new int[prices.length];
		
		for(int i = 0; i < prices.length-1; i++) {
			int cnt = 0;
			for(int j = i+1; j < prices.length; j++) {
				cnt++;
				if( prices[i] > prices[j]) {
					break;
				}
			}
			
			answer[i] = cnt;
			
		}
		
        return answer;
	}

}

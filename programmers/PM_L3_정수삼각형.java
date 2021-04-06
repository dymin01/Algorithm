package programmers;

public class PM_L3_정수삼각형 {

	public static void main(String[] args) {
		
		PM_L3_정수삼각형 sol = new PM_L3_정수삼각형();
		int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		
		System.out.println(sol.solution(triangle));
		
	}
	
	public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[501][501];
        dp[0][0] = triangle[0][0];
        for(int i = 1; i < triangle.length; i++) {
        	for(int j = 0; j <= i; j++) {
        		if(j == 0) {
        			dp[i][j] = dp[i-1][j] + triangle[i][j];
        		}else if(j == i) {
        			dp[i][j] = dp[i-1][j-1] + triangle[i][j];
        		}else {
        			dp[i][j] = Math.max(dp[i-1][j] + triangle[i][j], dp[i-1][j-1] + triangle[i][j]);
        		}
        	}
        }
        
        for(int i = 0; i < triangle.length; i++) {
        	answer = Math.max(dp[triangle.length-1][i], answer);
        }
        
        return answer;
    }

}

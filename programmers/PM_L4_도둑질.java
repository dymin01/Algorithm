package programmers;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * 도둑이 훔칠 수 있는 돈의 최대값을 구해라
 * 
 * 문제 유형
 * DP
 * 
 * 제약 사항
 * 집은 3개 이상 1,000,000개 이하이다.
 * money배열의 각 원소는 0이상 1,000 이하인 정수이다.
 *  
 * <풀이 요약>
 * 처음에 잘못 생각해서 홀수일경우 짝수일경우라고 생각함...
 * 연속해서 집을 안터는 경우도 있다.
 * 첫집을 털경우 마지막집을 털면 안된다.
 * 첫집을 털지 않을경우 마지막집을 털어도 된다.
 * 결국 범위값이 달라져 DP를 두개 만들었다.
 * 
 * 1. 첫번째 집을 터는 경우와 첫번째 집을 털지 않는 경우가 있다. 둘을 나눠서 저장한다.
 * 2. 현재 집까지의 최대 가치는
 * 		- 2개 전 집을 털고 현재 집을 터는경우
 * 		- 지금 집을 털지않고 전 집을 턴 경우
 *    의 최대 값이다.
 * 
 * 
 * 
 */

public class PM_L4_도둑질 {

	public static void main(String[] args) {
		PM_L4_도둑질 sol = new PM_L4_도둑질();
		
		int[] money = {1, 2, 3, 1};
		System.out.println(sol.solution(money));
	}
	
	public int solution(int[] money) {
        int answer = 0;
        
        int N = money.length;
        // 첫번째 집을 턴 경우
        int[] DP1 = new int[N];
        // 첫번째 집을 털지 않은경우
        int[] DP2 = new int[N];
        
        DP1[0] = money[0];
        DP1[1] = money[0];
        DP2[0] = 0;
        DP2[1] = money[1];
        
        // 첫번째 집을 턴 경우 
        for(int i = 2; i < N-1; i++) {
        	DP1[i] = Math.max(DP1[i-2] + money[i], DP1[i-1]);
        }
        
        // 첫번째 집을 털지 않은경우
        for(int i = 2; i < N; i++) {
        	DP2[i] = Math.max(DP2[i-2] + money[i], DP2[i-1]);
        }
        
        answer = Math.max(DP1[N-2], DP2[N-1]);
        
        
        return answer;
    }

}

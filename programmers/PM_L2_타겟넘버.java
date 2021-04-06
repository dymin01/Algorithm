package programmers;
/**
 * <문제 요약>
 * 구해야 하는 것: n개의 정수를 더하거나 빼서 target을 만드는 방법의 수
 * 제약 사항: n은 20개 이하
 * 문제 유형: DFS
 * 요구 개념: DFS
 * <풀이법 요약>
 * 1. DFS(재귀)를 이용하여 더하거나 빼준다.
 * 2. n개를 모두 진행했을경우 값이 target과 같으면 answer에 1을 더해준다.
 */


public class PM_L2_타겟넘버 {

	public static void main(String[] args) {
		PM_L2_타겟넘버 sol = new PM_L2_타겟넘버();
		int[] numbers = {1,1,1,1,1};
		int target = 3;
		System.out.println(sol.solution(numbers, target));
	}
	
	static int ans;
	
	public int solution(int[] numbers, int target) {
        int answer = 0;
        
        ans = 0;
        int sum = 0;
        int depth = 0;
        dfs(numbers, target, sum, depth);
        answer = ans;
        return answer;
    }
	
	//DFS로 구현
	public void dfs(int[] numbers, int target, int sum, int depth ) {
		// 더하거나 뺸 숫자가 n개이면 끝낸다.
		if(depth == numbers.length) {
			// 만약 더하거나 뺀 결과값이 target과 같으면 answer을 하나 올려준다.
			if(sum == target) {
				ans++;
				
			}
			return;
		}
		// 정수를 더해본다.
		dfs(numbers, target, sum + numbers[depth], depth+1);
		// 정수를 빼본다.
		dfs(numbers, target, sum - numbers[depth], depth+1);
		
	}

}

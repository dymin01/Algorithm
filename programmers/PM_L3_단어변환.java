package programmers;
/**
 * <문제 요약>
 * 구해야 하는 것: 몇번의 변환 후 target과 같아 지는지 최솟값
 * 제약 사항: 한 번에 한 개의 알파벳만 바꿀 수 있다.
 * 문제 유형: DFS/BFS
 * 요구 개념: DFS/BFS
 * <풀이법 요약>
 * 1. 시작 단어에서 words 단어와 비교해서 바꿀 수 있는 단어인지 확인한다(다른 알파벳의 갯수가 1개인지 확인)
 * 2. 바꿀 수 있는 단어라면 단어를 바꾼 후 다른 단어와 비교를 반복한다.
 * 3. 단어를 다 확인했으나 같은 값이 나오지 않으면 0을 출력한다.
 * 4. 단어가 같으면 바꾼 횟수를 출력한다. 
 */


public class PM_L3_단어변환 {

	public static void main(String[] args) {
		
		PM_L3_단어변환 sol = new PM_L3_단어변환();
		
		String begin = "hit";
		String target= "cog";
		//String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		String[] words = {"hot", "dot", "dog", "lot", "log"};
		
		System.out.println(sol.solution(begin, target, words));

	}
	
	static int ans;
	static boolean[] v;
	
	public int solution(String begin, String target, String[] words) {
        int answer = 0;
        v = new boolean[words.length];
        ans = Integer.MAX_VALUE;
        
        DFS(begin, target, words, 0);
        
        answer = ans > 100 ? 0:ans;
        
        return answer;
    }
	
	public void DFS(String begin,String target, String[] words, int cnt) {
		
		// 단어를 다 확인해도 정답이 없을경우
		if(cnt > words.length) {
			ans = 0;
			return;
		}
		// 단어가 target과 같을경우
		if(begin.equals(target)) {
			ans = Math.min(ans, cnt);
			return;
		}
		// 모든 단어를 비교한다.
		for(int i = 0; i < words.length; i++) {
			// 이미 확인하지 않은 단어이고, 1글자만 다를경우
			if(count(begin, words[i]) <= 1 && !v[i]) {
				v[i] = true;
				DFS(words[i], target, words, cnt+1);
				v[i] = false;
			}
		}
	}
	
	// 단어의 알파벳이 몇개 다른지 확인
	public int count(String str1, String str2) {
		int cnt = 0;
		
		for(int i = 0; i < str1.length(); i++) {
			if(str1.charAt(i) != str2.charAt(i)) {
				cnt++;
			}
		}
		
		return cnt;
		
	}

}

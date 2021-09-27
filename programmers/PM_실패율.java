package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class PM_실패율 {

	public static void main(String[] args) {
		
		PM_실패율 sol = new PM_실패율();
		
		int N = 5;
//		int N = 4;
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
//		int[] stages = {4,4,4,4,4};
		
		int[] answer = sol.solution(N, stages);
		
		for(int i = 0; i < answer.length; i++) {
			System.out.print(answer[i] + " ");
		}

	}
	
	static class Stage{
		int stage;
		double failRate;
		public Stage(int stage, double failRate) {
			super();
			this.stage = stage;
			this.failRate = failRate;
		}
	}
	
	public int[] solution(int N, int[] stages) {
        int[] answer = {};
        
        Stage[] stage = new Stage[N];
        
        for(int i = 1; i <= N; i++) {
        	double tryStage = 0;
        	double curStage = 0;
        	
        	for(int j = 0; j < stages.length; j++) {
        		if(stages[j] > i) {
        			tryStage++;
        		} else if(stages[j] == i) {
        			tryStage++;
        			curStage++;
        		}
        	}
        	
        	if(tryStage == 0) {
        		stage[i-1] = new Stage(i, 0);
        	} else {
        		stage[i-1] = new Stage(i, curStage / tryStage);
        	}
        }
        
        int test = 0;
        
        Arrays.sort(stage, new Comparator<Stage>() {
			@Override
			public int compare(Stage o1, Stage o2) {
				if(o1.failRate == o2.failRate) {
					return  o1.stage - o2.stage;
				}
				return (int)((o2.failRate - o1.failRate)*100);
			}
		});
        
        answer = new int[stage.length];
        
        for(int i = 0; i < stage.length; i++) {
        	answer[i] = stage[i].stage;
        }
        
        return answer;
    }

}

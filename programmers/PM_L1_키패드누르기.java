package programmers;

public class PM_L1_키패드누르기 {

	public static void main(String[] args) {

		PM_L1_키패드누르기 sol = new PM_L1_키패드누르기();

		int[] numbers = { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 };
		String hand = "right";
		
		System.out.println(sol.solution(numbers, hand));
	}

	// 0 1 2 3 4 5 6 7 8 9
	static int[][] pos = { { 3, 1 }, { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 }, { 2, 1 }, { 2, 2 } };

	static public String solution(int[] numbers, String hand) {
		
		char H = Character.toUpperCase(hand.charAt(0));
		
		String answer = "";
		StringBuilder sb = new StringBuilder();
		int[] left = { 3, 0 };
		int[] right = { 3, 2 };
		for (int i = 0; i < numbers.length; i++) {
			int num = numbers[i];
			switch (num) {
			// 왼손 
			case 1:
			case 4:
			case 7:
				sb.append("L");
				left = pos[num];
				break;
			// 오른손
			case 3:
			case 6:
			case 9:
				sb.append("R");
				right = pos[num];
				break;
			// 거리비교
			case 2:
			case 5:
			case 8:
			case 0:
				int LD = dis(left, pos[num]);
				int RD = dis(right, pos[num]);
				if(LD < RD) {
					sb.append("L");
					left = pos[num];
				}else if (LD > RD) {
					sb.append("R");
					right = pos[num];
				}else {
					sb.append(H);
					if(H == 'R') {
						right = pos[num];
					}else {
						left = pos[num];
					}
				}
				break;
			}
		}

		return sb.toString();
	}
	
	static public int dis(int[] A, int[] B) {
		int dis = 0;
		
		dis = Math.abs(A[0] - B[0]) + Math.abs(A[1] - B[1]);
		
		return dis;
		
	}
	

}

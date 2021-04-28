package sw_expert_academy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 은서파
 * @since 2021. 4. 22.
 * @see
 * @mem
 * @time
 * @caution [고려사항] [입력사항] [출력사항]
 */
public class SWEA_모의_5653_줄기세포배양_PQ_교수님 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int T, N, M, K;
	static boolean [][] visited;
	static PriorityQueue<Cell> liveCells;
	
	static int [][] deltas = {{-1, 0},{1, 0},{0, -1},{0, 1}};
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		T = Integer.parseInt(input.readLine());
		for(int t=1; t<=T; t++) {
			tokens = new StringTokenizer(input.readLine(), " ");
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			// 세포는 번식하니까 번식 시간인 K 가 개입
			visited = new boolean[N+K][M+K];
			liveCells = new PriorityQueue<>();
			
			for(int r=0; r<N; r++) {
				tokens = new StringTokenizer(input.readLine(), " ");
				for(int c=0; c<M; c++) {
					int life = Integer.parseInt(tokens.nextToken());
					if(life >0) {
						Cell cell = new Cell(r+K/2, c+K/2, life, life, 0);
						// 선점되어있으면 다른 녀석들은 못와요~~~
						visited[r][c]=true;
						liveCells.offer(cell);
					}
				}
			}// 입력 완료
			//System.out.println(liveCells);
			incubate();
			output.append('#').append(t).append(' ').append(liveCells.size()).append('\n');
		}
		System.out.println(output);
	}
	static void incubate() {
		// K 시간동안의 변화를 살펴보자.
		while(!liveCells.isEmpty()) {
			// 1. 맨 처음 세포 가져오기
			Cell head = liveCells.poll();
			
			// 2. 사용하기. 가져온 세포의 시간이 K와 같다면 실험 끝 --> 만약 이녀석이 정답의 일부였다면????
			if(head.time==K) {
				liveCells.offer(head);
				break;
			}
			
			// 아직 세포가 비 활성화 된 상태라면?? 대기시간을 줄인다. 그러다 0이되면 활성화 된다. 그리고 다시 관리, 시간은 +1
			if(!head.isActivated) {
				if(--head.wait ==0) {
					head.isActivated = true;
				}
				head.time++;
				liveCells.offer(head);
			}
			// 세포가 이미 활성화 된 상태라면??: 사방탐색으로 번식, head의 생명력 -1, 생명이 0 되면 사망(더이상  PQ에서 관리 X)
			else {
				// 세포가 확장하는것 처리
				for(int d=0; d<deltas.length ; d++) {
					int nr = head.r + deltas[d][0];
					int nc = head.c + deltas[d][1];
					
					// 범위를 벗어날 일은 없어요... 그럼 nr , nc에 갈 수 있나???? 선점하고 있으면 못가요...
					if(!visited[nr][nc]) {
						visited[nr][nc]=true;
						// 세포 확장.
						liveCells.offer(new Cell(nr,  nc, head.life, head.life, head.time+ 1));
					}
				}
				// 그럼 원래 있던 세포의 운명은? life가 줄어든다... --> 여전히 생명력이 있으면 다시 관리, 아니면 사망
				if(--head.life >0) {
					head.time++;
					liveCells.offer(head);
				}		
			}
		}
	}
	

	static class Cell implements Comparable<Cell> {
		int r, c;
		int life, wait;// 생명력, 대기시간
		int time;// 현재가 몇 시간째??
		boolean isActivated;// 활성화 여부(wait==0이면 활성화 된다.)

		public Cell(int r, int c, int life, int wait, int time) {
			this.r = r;
			this.c = c;
			this.life = life;
			this.wait = wait;
			this.time = time;
			isActivated = wait == 0;
		}

		// 턴을 중심으로 동작하며 그중에서 생명력 높은놈이 먼저..
		@Override
		public int compareTo(Cell o) {
			if (this.time == o.time) {
				return Integer.compare(o.life, this.life);
			} else {
				return Integer.compare(this.time, o.time);
			}
		}

		@Override
		public String toString() {
			return "Cell [r=" + r + ", c=" + c + ", life=" + life + ", time=" + time + "]";
		}
	}

	private static String src = "1\r\n" +
								"2 2 10\r\n" +
								"1 1\r\n" +
								"0 2";
}

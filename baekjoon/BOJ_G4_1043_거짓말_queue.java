package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 과장된 이야기를 할 수 있는 파티 개수의 최댓값을 구하라
 * 
 * 문제 유형
 * 
 * 
 * 제약 사항
 * N, M은 50이하의 자연수
 * 0 <= 진실을 아는 사람의 수 <= 50
 * 1 <= 각 파티에 오는 사람의 수 <= 50
 * 
 * <풀이 요약>
 * 
 * 
 */

public class BOJ_G4_1043_거짓말_queue {

	static int N, M;
	
	static ArrayList<Integer>[] partyPeopleList;
	
	static boolean[] cantLie;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 파티에 오는 인원
		N = Integer.parseInt(st.nextToken());
		// 파티 개수
		M = Integer.parseInt(st.nextToken());
		
		cantLie = new boolean[M];
		
		partyPeopleList = new ArrayList[M];
		for(int i = 0; i < M; i++) {
			partyPeopleList[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		
		int cntOfKnow = Integer.parseInt(st.nextToken());
		Queue<Integer> knowTrue = new LinkedList<Integer>();
		for(int i = 0; i < cntOfKnow; i++) {
			knowTrue.add(Integer.parseInt(st.nextToken()));
		}
		
		// 진실을 아는 사람이 없으면 모든 파티에 참석 가능
		if(cntOfKnow == 0) {
			System.out.println(M);
		}
		else {
			// 파티에 참석하는 사람 입력
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				for(int j = 0; j < n; j++) {
					partyPeopleList[i].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			while(!knowTrue.isEmpty()) {
				
				// 진실을 아는 사람
				int knowPeople = knowTrue.poll();
				
				for(int i = 0; i < M; i++) {
					
					// 거짓말을 할 수 없으면 넘어간다.
					if(cantLie[i]) continue;
					// 진실을 알고 있는 사람이 파티에 있으면
					if(partyPeopleList[i].contains(knowTrue)) {
						cantLie[i] = true;
						for(int people : partyPeopleList[i]) {
							// 진실을 모르는 사람이고
							if(knowPeople != people) {
								// 진실을 알고 있는 사람 queue에 없으면 queue에 넣는다.
								if(!knowTrue.contains(people)) {
									knowTrue.add(people);
								}
							}
						}
					}
				}
				
			}
			
			int answer = 0;
			for(int i = 0; i < M; i++) {
				if(!cantLie[i]) {
					answer++;
				}
			}
			
			System.out.println(answer);
			
		}
		
		
		

	}



}

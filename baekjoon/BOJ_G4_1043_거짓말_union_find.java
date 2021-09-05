package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/***
 * 
 * <문제 요약>
 * 문제 정의
 * - 과장된 이야기를 할 수 있는 파티 개수의 최댓값을 구하라
 * 
 * 문제 유형
 * - union find
 * - 부분집합
 * 
 * 제약 사항
 * N, M은 50이하의 자연수
 * 0 <= 진실을 아는 사람의 수 <= 50
 * 1 <= 각 파티에 오는 사람의 수 <= 50
 * 
 * <풀이 요약>
 * 
 * 구선생님의 도움을 받아 풀었습니다.
 * 
 * 
 */

public class BOJ_G4_1043_거짓말_union_find {

	static int N, M;
	
	static ArrayList<Integer>[] partyPeopleList;
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 파티에 오는 인원
		N = Integer.parseInt(st.nextToken());
		// 파티 개수
		M = Integer.parseInt(st.nextToken());
		
		// 부모노드 초기화
		parent = new int[N+1];
		for(int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		
		partyPeopleList = new ArrayList[M];
		for(int i = 0; i < M; i++) {
			partyPeopleList[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		
		int cntOfKnow = Integer.parseInt(st.nextToken());
		boolean[] knowTrue = new boolean[N + 1];
		for(int i = 0; i < cntOfKnow; i++) {
			knowTrue[Integer.parseInt(st.nextToken())] = true;
		}
		
		// 진실을 아는 사람이 없으면 모든 파티에 참석 가능
		if(cntOfKnow == 0) {
			System.out.println(M);
		}
		else {
			int pre = 0;
			// 파티에 사람 입력
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				
				if(n > 0) {
					pre = Integer.parseInt(st.nextToken());
					partyPeopleList[i].add(pre);
				}
				for(int j = 1; j < n; j++) {
					int people = Integer.parseInt(st.nextToken());
					partyPeopleList[i].add(people);
					union(pre, people);
					pre = people;
				}
				
			}
			
			for(int i = 1; i <= N; i++) {
				// 진실을 알고있다면
				if(knowTrue[i]) {
					// 부모노드도 진실을 알고 있다.
					knowTrue[find(i)] = true;
				}
				
			}
			
			int answer = 0;
			
			for(int i = 0; i < M; i++) {
				if(partyPeopleList[i].size() > 0) {
					int temp = find(partyPeopleList[i].get(0));
					if(!knowTrue[temp]) answer++; 
				}
				
			}
			
			System.out.println(answer);
			
		}
		
		
		

	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			if(a > b) {
				parent[a] = b;
			} else {
				parent[b] = a;
			}
		}
		
	}

	private static int find(int a) {
		
		if(parent[a] == a)
			return parent[a] = a;
		else return find(parent[a]);
		
	}

}

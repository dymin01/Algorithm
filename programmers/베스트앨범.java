package programmers;

/***
 * <문제 요약>
 * 구해야 하는 것 : 장르 별로 가장 많이 재생된 노래를 두개씩 모아 베스트 앨범을 만든다.
 * 제약 사항 : 가장많이 재생된 장르 순서로, 가장많이 재생된 노래순서로 정렬
 * 문제 유형 : 구현, 정렬
 * 요구 개념 : 해시맵, comparator
 * <풀이법 요약>
 * 0. 인덱스, 재생횟수, 장르를 저장하는 song class를 만듬
 * 1. 장르를 key로 hashmap에 저장하고, song list에도 저장한다.
 * 2. song list 장르 이름순으로 정렬, 장르가 같으면 song의 재생횟수 , 횟수가 같으면 인덱스 순으로 정렬한다.
 * 3. song list에 있는 노래들을 bestAlbum HashMap에 저장한다.
     - 이때 장르의 갯수가 2개가 넘지 않도록 한다.
 * 4. answer list에 저장한다.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class 베스트앨범 {

	public static void main(String[] args) {
	
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		int[] ans = solution(genres, plays);
		for(int a : ans) {
			System.out.print(a + " ");
		}
	}
	
	
	
	public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        // 장르별 총 플레이 수
        HashMap<String, Integer> genresMap = new HashMap<>();
        // 노래병 인덱스, 플래이, 장르를 저장하는 Song 리스트
        ArrayList<Song> songList = new ArrayList<>();
        
        for(int i = 0; i < genres.length; i++) {
        	String gen = genres[i];
        	int play = plays[i];
        	songList.add(new Song(i, play, gen));
        	if(genresMap.containsKey(gen)) {
        		genresMap.put(gen, genresMap.get(gen) + play);
        	}else {
        		genresMap.put(gen, play);
        	}
        }
        //장르별 플레이수
    	//장르 이름이 같으면 플레이
    	//플레이가 같으면 인데스로 정렬
        Collections.sort(songList, new Comparator<Song>() {
			@Override
			public int compare(Song s1, Song s2) {
				// 장르가 같으면
				if(s1.genre.equals(s2.genre)) {
					//song class 내의 compareto함수를 이용 재생횟수, 인덱스 순으로 정렬한다.
					return s1.compareTo(s2);
				}else {
					// 장르가 다르면 장르별 총 플레이 수 내림차순
					return (genresMap.get(s2.genre) - genresMap.get(s1.genre));
				}
			}
		});
        //장르별 2개의 노래를 저장할 변수
        HashMap<String, Integer> bestAlbum = new HashMap<>();
        // 정답을 저장한 변수
        ArrayList<Integer> answerList = new ArrayList<>();
        
        for(Song s : songList) {
        	//bestAlbum HashMap에 이미 장르가 저장되어 있다면
        	if(bestAlbum.containsKey(s.genre)) {
        		// 2개 이하만 확인
        		if(bestAlbum.get(s.genre) < 2) {
        			// bestAlbum에 장르 갯수를 2개로 변경
        			bestAlbum.put(s.genre, 2);
        			//정답에 추가
        			answerList.add(s.id);
        		}
        	}
        	// 장르가 저장되어있지 않다면.
        	else {
        		// 장르를 하나 넣고 정답 lost에 저장
        		bestAlbum.put(s.genre, 1);
        		answerList.add(s.id);
        	}
        }
        // answer배열에 저장
        answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
        	answer[i] = answerList.get(i);
        }

        return answer;
    }

}

// 노래 class
class Song implements Comparable<Song>{
	
	// 인덱스, 재생횟수, 장르
	int id, play;
	String genre;
	
	public Song(int id, int play, String genre) {
		super();
		this.id = id;
		this.play = play;
		this.genre = genre;
	}

	@Override
	public int compareTo(Song s) {
		// 재생횟수가 같으면 인덱스로 정렬(
		if(this.play == s.play) {
			return this.id - s.id;
		}else {
			// 재생횟수별 내림차순
			return s.play - this.play;
		}
	}
	
}

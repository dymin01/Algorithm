package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_3055_탈출 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];

        int[] end = new int[2];
        int[] start = new int[2];

        Queue<int[]> queueW = new LinkedList<>();
        Queue<int[]> queueH = new LinkedList<>();
        // 맵 초기화, 시작점, 끝점
        // 물의 위치 큐에 저장.
        for(int i = 0; i < R; i++){
            String str = br.readLine();
            for(int j = 0; j < C; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'D'){
                    end[0] = i;
                    end[1] = j;
                }
                if(map[i][j] == 'S'){
                    start[0] = i;
                    start[1] = j;
                }
                if(map[i][j] == '*'){
                    queueW.add(new int[] {i, j});
                }
            }
        }
        // r, c, cnt
        queueH.add(new int[] {start[0], start[1], 0});
        // 물 먼저 이동 -> 고슴도치 이동
        int ans = Integer.MAX_VALUE;

        while(true){
            // 물 사이즈
            int wSize = queueW.size();
            // 고슴도치 큐 사이즈
            int hSize = queueH.size();

            if(hSize == 0 || ans < Integer.MAX_VALUE){
                break;
            }

            while(hSize-- > 0){
                int[] cur = queueH.poll();
                int r = cur[0];
                int c = cur[1];
                int cnt = cur[2];

                if(map[r][c] == '*') continue;

                for(int d = 0; d < 4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if(map[nr][nc] == 'D'){
                        ans = Math.min(ans, cnt+1);
                        hSize = 0;
                        break;
                    }
                    if(map[nr][nc] == 'X' || map[nr][nc] == '*' || map[nr][nc] == 'S') continue;
                    map[nr][nc] = 'S';
                    queueH.add(new int[] {nr, nc, cnt+1});
                }
            }

            while(wSize-- > 0){
                int[] cur = queueW.poll();
                int r = cur[0];
                int c = cur[1];

                for(int d = 0; d < 4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if(map[nr][nc] == 'X' || map[nr][nc] == '*' || map[nr][nc] == 'D') continue;
                    map[nr][nc] = '*';
                    queueW.add(new int[] {nr, nc});
                }
            }

        }

        if(ans == Integer.MAX_VALUE){
            System.out.println("KAKTUS");
        }else{
            System.out.println(ans);
        }

    }

}
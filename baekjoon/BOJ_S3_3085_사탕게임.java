package algo01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S3_3085_사탕게임 {

    static char[][] map;
    static int N, max;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        max = 0;

        for(int i = 0; i < N; i++){
            String str = br.readLine();

            for(int j = 0; j < N; j++){
                map[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N-1; j++){

                // 가로 교환
                swap(i, j, i, j+1);
                arrCheck();
                swap(i, j, i, j+1);

                // 세로 교환
                swap(j, i, j+1, i);
                arrCheck();
                swap(j, i, j+1, i);

            }
        }


        System.out.println(max);

    }

    public static void swap(int r1, int c1, int r2, int c2){

        char temp = map[r1][c1];
        map[r1][c1] = map[r2][c2];
        map[r2][c2] = temp;

    }

    private static void arrCheck() {

        int num = 0;

        for(int i = 0; i < N; i++){
            int Rcount = 1;
            int Ccount = 1;
            for(int j = 0; j < N-1; j++){
                if(map[i][j] == map[i][j+1]){
                    Ccount++;
                }else{
                    Ccount = 1;
                }

                if(map[j][i] == map[j+1][i]){
                    Rcount++;
                }else{
                    Rcount = 1;
                }

                num = Math.max(Rcount, Ccount);
                max = Math.max(max, num);
            }


        }


    }
}

package algo02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_11060_점프점프 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;

        for(int i = 0; i < N; i++){
            if(dp[i] == Integer.MAX_VALUE) continue;

            int jump = arr[i];
            for(int j = 1; j <= jump; j++){
                if(i + j < N ){
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }

            }

        }
        if(dp[N-1] == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(dp[N-1]);

    }

}

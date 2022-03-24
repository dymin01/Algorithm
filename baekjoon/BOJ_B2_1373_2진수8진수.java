package algo02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_B2_1373_2진수8진수 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder(br.readLine());
        StringBuilder ans = new StringBuilder();


        if(sb.length() % 3 == 1){
            sb = new StringBuilder("00").append(sb);
        }else if(sb.length() % 3 == 2){
            sb = new StringBuilder("0").append(sb);
        }

        int len = sb.length() / 3;

        for(int i = 0; i < len; i++){
            int idx = (i * 3);

            int num = (sb.charAt(idx)-'0') * 4 + (sb.charAt(idx+1)-'0') * 2 + (sb.charAt(idx+2)-'0');
            ans.append(num);
        }

        System.out.println(ans);

    }
}

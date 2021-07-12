
import java.util.Arrays;
import java.util.Scanner;
class times implements Comparable<times>{
   int start, end;

   public times(int start, int end) {
      super();
      this.start = start;
      this.end = end;
   }

   @Override
   public int compareTo(times o) {
      if(this.end==o.end)return Integer.compare(this.start,o.start);
      else return Integer.compare(this.end, o.end);
   }
   
}
public class test {

   public static void main(java.lang.String[] args) {
      Scanner sc = new Scanner(System.in);
      int N=sc.nextInt();
      times[] time = new times[N];
      for (int i = 0; i < N; i++) {
         int t = sc.nextInt();
         int tt = sc.nextInt();
         time[i]=new times(t,tt);
      }
      Arrays.sort(time);
      
      int count = 0;
      int end=-1;
      for(int i=0;i<N;i++) {
         if(time[i].start>=end) {
            end=time[i].end;
            count++;
         }
      }
      System.out.println(N-count+1);
   }

}
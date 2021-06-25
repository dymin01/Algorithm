
package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PM_L4_동굴탐험2 {

   public static void main(String[] args) {
      int n=9;
      int path[][]= {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
      int order[][]= {{8,5},{6,7},{4,1}};
      System.out.println(solution(n,path,order));
   }
   
   static List<Integer> []list;
   static boolean[]V;
   static int clist[];
   static int relist[];
   static boolean check;
   static int parent[];
   static Queue<Integer> que = new LinkedList<Integer>();
   
    public static boolean solution(int n, int[][] path, int[][] order) {
       V = new boolean[n];
       list=new ArrayList[n];
       clist=new int[n];
       relist=new int[n];
       parent=new int[n];
       
       for(int i=0;i<n;i++) {
          list[i]=new ArrayList<>();
       }
       
       int pa = path.length;
       int or= order.length;
       for(int i=0;i<pa;i++) {
          int x=path[i][0];
          int y=path[i][1];
          list[x].add(y);
          list[y].add(x);
       }
       
       Arrays.fill(relist, -1);
       for(int i=0;i<or;i++) {
          int x=order[i][0];
          int y=order[i][1];
          clist[y]=x;
          relist[x]=y;
       }
       if(clist[0]!=0) return false;
       V[0]=true;
       for(int i=0;i<list[0].size();i++) {
          que.add(list[0].get(i));
       }
       bfs();
       for(int i=0;i<n;i++) 
          if(!V[i])return false;
       return true;
       }
   private static void bfs() {
      while(!que.isEmpty()) {
         int cur=que.poll();
         if(V[cur])continue;
         if(!V[clist[cur]]) {
            continue;
         }
         System.out.println(cur);
         V[cur]=true;
         for(int i=0;i<list[cur].size();i++) {
            que.add(list[cur].get(i));
         }
         if(relist[cur]!=-1)
            que.add(relist[cur]);
      }
   }

}
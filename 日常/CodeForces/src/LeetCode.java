import java.util.*;


public class LeetCode {
    static int[][] int2d(String x){
        x = x.substring(2, x.length()-2);
        String[] d2 = x.split("\\],\\[");
        int n = d2.length, m = d2[0].split(",").length;
        int[][] res = new int[n][m];
        for (int i = 0;i < n;i++){
            String[] cur = d2[i].split(",");
            for (int j = 0;j < m;j++) res[i][j] = Integer.parseInt(cur[j]);
        }
        return res;
        
    }
    static int[] int1d(String x){
        x = x.substring(1, x.length()-1);
        String[] tp = x.split(",");
        int n = tp.length;
        int[] res = new int[n];
        for (int i = 0;i < n;i++){
            res[i] = Integer.parseInt(tp[i]);
        }
        return res;
    }
    public static void main(String[] args) {
        String[] x = {"[[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]",
                        "[[10,5,0],[15,2,1],[25,1,1],[30,4,0]]"};
        System.out.println(new Solution().getNumberOfBacklogOrders(
                int2d(x[0])
        ));
    }
}

class Solution {
    public int getNumberOfBacklogOrders(int[][] o) {
        PriorityQueue<int[]> s = new PriorityQueue<>((x,y)->x[0]-y[0]);
        PriorityQueue<int[]> b = new PriorityQueue<>((x,y)->y[0]-x[0]);
        for (int[] ord : o){
            int p = ord[0], num = ord[1], f = ord[2];
            if (f == 0){
                while (!s.isEmpty() && num > 0){
                    int[] cur = s.peek();
                    if (cur[0] <= p){
                        int mins = Math.min(cur[1], num);
                        cur[1] -= mins;
                        num -= mins;
                        s.poll();
                        if (cur[1] != 0) s.add(cur);
                    }else break;
                }if (num != 0) b.add(new int[]{p, num});
            }else{
                while (!b.isEmpty() && num > 0){
                    int[] cur = b.peek();
                    if (cur[0] >= p){
                        int mins = Math.min(cur[1], num);
                        cur[1] -= mins;
                        num -= mins;
                        b.poll();
                        if (cur[1] != 0) b.add(cur);
                    }else break;
                }if (num != 0) s.add(new int[]{p, num});
            }
        }
        long ans = 0, M = (int)1e9+7;
        while (!s.isEmpty()) ans = (ans+s.poll()[1])%M;
        while (!b.isEmpty()) ans = (ans+b.poll()[1])%M;
        return (int)ans;
    }
}
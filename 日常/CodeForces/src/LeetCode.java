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
        String[] x = {"[[1,2,3],[2,5,7],[3,5,1]]","[5,6,2]"};
        System.out.println(new Solution().maxPoints(
                int2d(x[0]), int1d(x[1])
        ));
    }
}

class Solution {
    public int[] maxPoints(int[][] g, int[] qr) {
        int n = g.length, m = g[0].length, k = qr.length, res = 0;
        int[][] q = new int[k][2];
        for (int i = 0;i < k;i++){
            q[i][0] = qr[i];
            q[i][1] = i;
        }
        Arrays.sort(q, (s, t)->s[0]-t[0]);
        boolean[][] v = new boolean[n][m];
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        Deque<int[]> pre = new LinkedList<>();
        pre.add(new int[]{0, 0});
        v[0][0] = true;
        for (int i = 0;i < k;i++){
            Deque<int[]> d = new LinkedList<>(pre);
            pre.clear();
            int cur = q[i][0];
            while (!d.isEmpty()){
                int[] loc = d.pollFirst();
                int x = loc[0], y = loc[1];
                if (g[x][y] >= cur) pre.offerLast(loc);
                else{
                    res++;
                    for (int j = 0;j < 4;j++){
                        int nx = x+dx[j], ny = y+dy[j];
                        if (nx<0||ny<0||nx>=n||ny>=m||v[nx][ny]) continue;
                        v[nx][ny] = true;
                        d.offerLast(new int[]{nx, ny});
                    }
                }
            }
            qr[q[i][1]] = res;
            System.out.print(res+" ");
        }
        return qr;
    }
}
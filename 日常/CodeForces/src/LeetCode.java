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
        String[] x = {"[[0,1,10],[1,2,5],[2,3,9],[3,4,13]]",
                        "[[0,4,14],[1,4,13]]"};
        System.out.println(new Solution().distanceLimitedPathsExist(
                5, int2d(x[0]), int2d(x[1])
        ));
    }
}

class Solution {
    int[] fa;
    int find(int t){
        if (fa[t] == t) return t;
        return fa[t] = find(fa[t]);
    }
    public boolean[] distanceLimitedPathsExist(int n, int[][] e, int[][] q) {
        fa = new int[n];
        for (int i = 0;i < n;i++) fa[i] = i;
        int m = q.length;
        int[][] tp = new int[m][4];
        boolean[] ans = new boolean[m];
        for (int i = 0;i < m;i++){
            for (int j = 0;j < 3;j++) tp[i][j] = q[i][j];
            tp[i][3] = i;
        }
        Arrays.sort(tp, (x, y)->x[2]-y[2]);
        Arrays.sort(e, (x, y)->x[2]-y[2]);
        int dx = 0;
        for (int[] t : tp){
            int x = t[0], y = t[1], d = t[2];
            while (dx < e.length && e[dx][2] < d){
                int xx = find(e[dx][0]),yy = find(e[dx][1]);
                if (xx != yy) fa[xx] = yy;
                ++dx;
            }if (find(x) == find(y)) ans[t[3]] = true;
        }
        //for (boolean i : ans) System.out.print(i+" ");
        return ans;
    }
}
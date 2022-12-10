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
        System.out.println(new Solution().maxHeight(
                int2d("[[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]")
        ));
    }
}

class Solution {
    public int maxHeight(int[][] c) {
        int n = c.length;
        for(int i=0;i<n;i++){Arrays.sort(c[i]);}
        Arrays.sort(c, (a, b)->{
            if (a[0] == b[0]){
                if (a[1] == b[1]) return b[2]-a[2];
                return b[1]-a[1];
            }return b[0]-a[0];
        });
        int[] d = new int[n+1];
        for (int i = 1;i <= n;i++){
            int x = c[i-1][0], y = c[i-1][1], h = c[i-1][2];
            d[i] = h;
            for (int j = 1;j < i;j++){
                int x1 = c[j-1][1], y1 = c[j-1][2];
                if (y<=x1&&h<=y1){
                    d[i] = Math.max(d[i], d[j]+h);
                }
            }
        }
        int ans = 0;
        for (int i = 1;i <= n;i++) ans = Math.max(ans, d[i]);
        return ans;
    }
}
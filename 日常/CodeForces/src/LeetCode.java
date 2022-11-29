import java.util.*;

import javax.crypto.Mac;


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
        System.out.println(new Solution().activitySelect(
            int2d("[[-52,31],[-73,-26],[82,97],[-65,-11],[-62,-49],[95,99],[58,95],[-31,49],[66,98],[-63,2],[30,47],[-40,-26]]")
        ));
    }
}

class Solution {
    public int activitySelect(int[][] act) {
        int n = act.length;
        Arrays.sort(act, (x, y)->x[1]-y[1]);
        int[] dp = new int[n+1], maxNum = new int[n+1];
        for (int i = 1;i <= n;i++){
            int s = act[i-1][0];
            int l = 0, r = i-1;
            while (l <= r){
                int m = l+r >> 1;
                int cur = m==0?-100000:act[m-1][1];
                if (cur <= s) l = m+1;
                else r = m-1;
            }
            if (r == -1)  dp[i] = 1;
            else dp[i] = maxNum[r]+1;
            maxNum[i] = Math.max(maxNum[i-1], dp[i]);
        }
        return n-maxNum[n];
    }
}
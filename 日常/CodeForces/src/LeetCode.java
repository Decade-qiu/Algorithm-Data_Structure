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
        System.out.println(new Solution().findGoodStrings(
            2,
            "aa",
            "da",
            "b"
        ));
    }
}

class Solution {
    char[] c1, c2;
    long M = (long)1e9+7;
    int n, m;
    int[] fail;
    long[][] memo;
    long dfs(int len, int pre, boolean f){
        if (len == n) return 1;
        if (!f && memo[len][pre] != -1) return memo[len][pre];
        char max = f?c1[len]:'z';
        long ans = 0;
        for (char i = 'a';i <= max;i++){
            int ppre = pre;
            while (ppre != 0 && c2[ppre] != i) {
                ppre = fail[ppre-1];
            }
            if (c2[ppre] == i) ppre++;
            if (ppre != m) ans=(ans+dfs(len+1,ppre,f&&i==max))%M;
        }
        memo[len][pre] = ans;
        return ans;
    }
    int get(String x){
        c1 = x.toCharArray();
        for (int i = 0;i <= n;i++) Arrays.fill(memo[i], -1);
        return (int)dfs(0, 0, true);
    }
    public int findGoodStrings(int N, String s1, String s2, String e) {
        if (s1.compareTo(s2) > 0) return 0;
        c2 = e.toCharArray();
        n = s1.length(); m = e.length();
        memo = new long[n+1][m+1];
        fail = new int[m];
        for (int i = 1, j = 0; i < m; ++i) {
            while (j != 0 && e.charAt(j) != e.charAt(i)) j = fail[j - 1];
            if (e.charAt(j) == e.charAt(i)) ++j;
            fail[i] = j;
        }
        long x = get(s2), y = get(s1);
        //System.out.println(x+" "+y);
        return (int)((x-y+M)%M+(s1.contains(e)?0:1));
    }   
}
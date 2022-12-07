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
        System.out.println(new Solution().magnificentSets(
            6,
                int2d("[[1,2],[1,4],[1,5],[2,6],[2,3]]")
        ));
    }
}

class Solution {
    List<Integer>[] g;
    HashMap<Integer, Integer> mp = new HashMap<>();
    int[] fa;
    int n;
    int find(int x){
        if (x == fa[x]) return x;
        return fa[x] = find(fa[x]);
    }
    public int magnificentSets(int n_, int[][] edges) {
        n = n_;
        g = new List[n+1];
        fa = new int[n+1];
        for (int i = 1;i <= n;i++) {
            g[i] = new ArrayList<>();
            fa[i] = i;
        }
        for (int[] ed : edges){
            int u = ed[0], v = ed[1];
            g[u].add(v); g[v].add(u);
            int x = find(u), y = find(v);
            if (x != y){
                if (x > y){
                    int tp = x;
                    x = y;
                    y = tp;
                }
                fa[y] = x;
            }
        }
        int ans = 0;
        for (int i = 1;i <= n;i++) a(i);
        for (int i = 1;i <= n;i++){
            if (fa[i] == i){
                int cur = mp.getOrDefault(i, -1);
                if (cur == -1) return -1;
                ans += cur;
            }
        }
        return ans;
    }
    void a(int u){
        Deque<Integer> d = new LinkedList<>();
        int[] dis = new int[n+1], vis = new int[n+1];
        d.offerLast(u); dis[u] = 1;
        int mi = n+2;
        while (!d.isEmpty()){
            int x = d.pollLast();
            if (vis[x] == 1) continue;
            vis[x] = 1;
            mi = Math.min(mi, x);
            for (int v : g[x]){
                if (dis[v] == 0) dis[v] = dis[x]+1;
                d.offerLast(v);
            }
        }
        int f = 0, res = 0;
        for (int i = 1;i <= n && f == 0;i++){
            if (dis[i] != 0){
                res = Math.max(res, dis[i]);
                for (int j : g[i]){
                    if (Math.abs(dis[i]-dis[j]) != 1){
                        f = 1;
                        break;
                    }
                }
            }
        }
        if (f != 0){
            mp.put(mi, res);
        }
    }
}
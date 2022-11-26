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
    public static void main(String[] args) {
        int[][] a = int2d("[[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]]");
        // for (int[] i : a){
        //     for (int ii : i) System.out.print(ii);
        //     System.out.println();
        // }
        long ans = new Solution().minimumWeight(
                6, int2d("[[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]]"), 0, 1, 5
        );
        System.out.println(ans);
    }
}

class Solution {
    long[] dj(int v){
        int n = g.length;
        long[] dis = new long[n];
        boolean[] vis = new boolean[n];
        Arrays.fill(dis, Integer.MAX_VALUE); dis[v] = 0;
        PriorityQueue<long[]> q = new PriorityQueue<>(
            (x, y)->Long.compare(x[1], y[1])
        );
        q.add(new long[]{v, 0});
        while (!q.isEmpty()){
            int u = (int)q.poll()[0];
            if (vis[u]) continue;
            vis[u] = true;
            for (int[] ne : g[u]){
                int t = ne[0], d = ne[1];
                if (dis[t] > dis[u]+d){
                    dis[t] = dis[u]+d;
                    q.add(new long[]{t, dis[t]});
                }
            }
        }
        return dis;
    }
    List<int[]>[] g;
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        g = new List[n];
        for (int i = 0;i < n;i++) g[i] = new ArrayList<>();
        for (int[] e : edges) g[e[0]].add(new int[]{e[1], e[2]});
        long[] d1 = dj(src1);
        long[] d2 = dj(src2);
        for (int i = 0;i < n;i++) g[i].clear();
        for (int[] e : edges) g[e[1]].add(new int[]{e[0], e[2]});
        long[] d3 = dj(dest);
        long ans = Long.MAX_VALUE;
        for (int i = 0;i < n;i++){
            ans = Math.min(ans, d1[i]+d2[i]+d3[i]);
        }
        return ans;
    }
}

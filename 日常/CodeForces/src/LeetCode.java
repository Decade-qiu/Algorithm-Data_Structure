import java.util.*;


public class LeetCode {
    public static void main(String[] args) {
        int[][] t = {{0,1,10},{0,2,1},{1,2,2}};
        int[] ttt = {1,4,6,7,8,20};
        int[] xxx = {7,2,15};
        String[] sss = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        int ans = new Solution().reachableNodes(
                t, 6, 3
        );
        System.out.println(ans);
    }
}

class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<int[]>[] g = new List[n];
        for (int i = 0;i < n;i++) g[i] = new ArrayList<>();
        for (int[] e : edges){
            g[e[0]].add(new int[]{e[1], e[2]+1});
            g[e[1]].add(new int[]{e[0], e[2]+1});
        }
        int[] dis = new int[n], vis = new int[n];
        Arrays.fill(dis, 1, n, 0x3f3f3f3f);
        PriorityQueue<int[]> q = new PriorityQueue<>((x, y)->x[1]-y[1]);
        q.add(new int[]{0, 0});
        while (!q.isEmpty()){
            int u = q.poll()[0];
            if (vis[u] == 1) continue;
            vis[u] = 1;
            for (int[] ne : g[u]){
                int v = ne[0], d = ne[1];
                if (dis[v] > dis[u]+d){
                    dis[v] = dis[u]+d;
                    q.add(new int[]{v, dis[v]});
                }
            }
        }
        int ans = 0;
        for (int i = 0;i < n;i++){
            System.out.println(dis[i]);
            if (dis[i] <= maxMoves) ans++;
        }
        for (int[] e : edges){
            int u = e[0], v = e[1], d = e[2];
            int le = Math.min(d, maxMoves-dis[u]);
            int ri = Math.min(d, maxMoves-dis[v]);
            ans += Math.min(le+ri, d);
        }
        return ans;
    }
}

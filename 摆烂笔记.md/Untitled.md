### 活动选择问题
> act[i][0] 第i个活动开始时间
> act[i][1] 第i个活动结束时间
#### 贪心
```java
public int activitySelect(int[][] act) {
    int n = act.length;
    int maxAct = 0, preActFinish = 0;
    Arrays.sort(act, (x, y)->x[1]-y[1]);
    for (int i = 0;i < n;i++){
        if (act[i][0] >= preActFinish){
            maxAct++;
            preActFinish = act[i][1];
        }
    }
    return maxAct;
}
```
时间复杂度 $O(n \log n + n) = O(n \log n)$

---

#### 动态规划
- A
```java
public int activitySelect(int[][] act) {
    int n = act.length;
    Arrays.sort(act, (x, y)->x[1]-y[1]);
    // 给act集合添加一个活动0和一个活动n+1
    int[][] a = new int[n+2][n+2];
    for (int i = 0;i < n;i++){
        a[i+1][0] = act[i][0];
        a[i+1][1] = act[i][1];
    }
    // 活动0结束时间在所有其他活动之前 活动n+1开始时间在所有其他活动之后
    a[0][0] = a[0][1] = 0;
    a[n+1][0] = a[n+1][1] = INF;
    // 按书上的dp解定义 bottom-up求解 最后答案是dp[0][n+1]
    int[][] dp = new int[n+2][n+2];
    for (int len = 2;len <= n+1;len++){
        for (int i = 0;i+len <= n+1;i++){
            int j = i+len;
            for (int k = i+1;k <= j-1;k++){
                if (a[k][0]>=a[i][1] && a[k][1]<=a[j][0]){
                    dp[i][j] = Math.max(dp[i][k]+dp[k][j]+1, dp[i][j]);
                }
            }
        }
    }
    int maxAct = dp[0][n+1];
    return maxAct;
}
```
时间复杂度 $O(n + n^3) = O(n^3)$
- B
> **dp[i] 表示从前i个活动中可以选出的最多不重叠的活动数目**
> 对第i个活动来说， 只有两种情况，选 和 不选， 即
> $\quad$ 1. 不考虑第i个活动 dp[i]=dp[i-1] 
> $\quad$ 2. 考虑第i个活动 需要找到小于等于第i个活动开始时间前最多可选活动数量，再加1。 （二分优化）
> dp[i] = $\max{\{dp[i-1], dp[t]+1\}}, 其中 t = \max_{t=1}^{i-1}{\{t\space|\space act[t][1]<=act[i][0]\}}$
> 但仔细一想，这也不是 
```java
public int activitySelect(int[][] act) {
    int n = act.length;
    Arrays.sort(act, (x, y)->x[1]-y[1]);
    int[] dp = new int[n+1];
    for (int i = 1;i <= n;i++){
        int s = act[i-1][0];
        int l = 0, r = i-1;
        while (l <= r){
            int m = l+r >> 1;
            int cur = m==0?-INF:act[m-1][1];
            if (cur <= s) l = m+1;
            else r = m-1;
        }
        if (r == -1)  dp[i] = 1;
        else dp[i] = dp[r]+1;
        dp[i] = Math.max(dp[i], dp[i-1]);
    }
    return dp[n];
}
```
时间复杂度 $O(n \log n+n \log n) = O(n \log n)$
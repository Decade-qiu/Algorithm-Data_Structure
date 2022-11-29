## 活动选择问题
### 贪心
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
时间复杂度 O(nlgn+n) = O(nlgn)
```
### 动态规划
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
    a[n+1][0] = a[n+1][1] = 100000;
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
时间复杂度 O(n+n^3) = O(n^3)
```
```java
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
时间复杂度 O()
```
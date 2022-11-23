### 二分

> [New Year's Problem](https://codeforces.com/contest/1619/problem/D)
>
> [J - Segment Tree](https://atcoder.jp/contests/practice2/tasks/practice2_j)(线段树上二分)
>
> ```java
> static void update(int k, int l, int r, int p, int v){
>     if (l == r){
>         f[k] = v; return;
>     }
>     int mid = l+r >> 1;
>     if (p <= mid) update(k<<1, l, mid, p, v);
>     else update(k<<1|1, mid+1, r, p, v);
>     f[k] = max(f[k<<1], f[k<<1|1]);
> }
> static int q1(int k, int l ,int r, int s, int t){
>     if (l == s && r == t) return f[k];
>     int mid = l+r >> 1;
>     int res = 0;
>     if (t <= mid) res = q1(k<<1, l, mid, s, t);
>     else if (s > mid) res = q1(k<<1|1, mid+1, r, s, t);
>     else res = max(q1(k<<1, l, mid, s, mid), q1(k<<1|1, mid+1, r, mid+1, t));
>     return res;
> }
> static int bs(int s, int t, int v){
>     if (s > t) return n+1;
>     int mid = s+t >> 1;
>     if (q1(1, 1, n, s, mid) >= v) return min(mid, bs(s, mid-1, v));
>     return bs(mid+1, t, v);
> }
> ```
>
> 

### 贪心

> [MEX and Increments](https://codeforces.com/contest/1619/problem/E)
>

### DP思想

> [Cooking](https://atcoder.jp/contests/abc204/tasks/abc204_d)

### 数论

> [Shifting String](https://codeforces.com/contest/1690/problem/F)
>
> 


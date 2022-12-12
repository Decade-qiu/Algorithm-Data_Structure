from typing import *

class Solution:
    def ck(self, g, m):
        n, p1 = len(g), 0
        v = [0 for i in range(n)]
        t = 0
        while t < n:
            i = t
            while i < n and g[i]-p1 <= m:
                i += 1
            if i >= n or i == t+1:
                break
            t = i-1
            p1 = g[t]
            v[t] = 1
        if i < n:
            return False
        f = [g[i] for i in range(n) if v[i] == 0]
        t, p2 = len(f)-1, f[-1]
        while t >= 0:
            j = t
            while j >= 0 and p2-f[j] <= m:
                j -= 1
            if j == -1 or j == t-1:
                break
            t = j+1
            p2 = f[t]
        return j < 0
    def maxJump(self, st: List[int]) -> int:
        n = len(st)
        l, r = 0, st[-1]-st[0]
        c, s = nums[i], int(pow(nums[i]))
        while l <= r:
            m = l+r >> 1
            if self.ck(st, m):
                r = m-1
            else:
                l = m+1
        return l

s = Solution()
print(s.maxJump([0,3,9]))
    

lst = map(int, input().split(" "))
ans = 0
pre = 0
for i in lst:
    if i == 1:
        ans += 1
        pre = 1
    elif i == 2:
        if pre <= 1:
            ans += 2
            pre = 2
        else:
            ans += pre+2
            pre += 2
print(ans)

print(math.pow(math.E))
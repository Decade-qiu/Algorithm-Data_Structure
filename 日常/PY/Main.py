import itertools
x = [1,2,2,3,4,4]
res = list(itertools.permutations(x))
print(len(res))
ans = list(set(res))
print(len(ans))
# print(ans)
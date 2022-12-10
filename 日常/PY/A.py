n = int(input())
for i in range(n):
    op = []
    num = []
    exp = input()
    for j in range(len(exp)):
        c = exp[j]
        if '0' <= c <= '9':
            num.append(int(c))
            if len(op) == 0:
                continue
            pre = op[-1]
            if pre == 'x' or pre == '/':
                op.pop()
                n1, n2 = int(num.pop()), int(num.pop())
                num.append(n1*n2 if pre == 'x' else n2/n1)
        else:
            op.append(c)
    for j in op:
        n1, n2 = int(num.pop(0)), int(num.pop(0))
        num.insert(0, n1+n2 if j=='+' else n1-n2)
    print('Yes' if num.pop()==24 else 'No')
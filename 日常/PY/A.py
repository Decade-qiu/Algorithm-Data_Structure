import re

# em和a标签的正则表达式对象
em_obj = re.compile(r'_[^_]*_')
a_obj = re.compile(r'\[[^\[\]\(\)]*\]\([^\[\]\(\)]*\)')


# 替换em标签
def em_repl(match):
    return '<em>{}</em>'.format(match.group()[1:-1].strip())


# 替换a标签
def a_repl(match):
    s = match.group()
    idx1, idx2 = s.index(']'), s.index('(')
    cont, link = s[1:idx1], s[idx2 + 1:-1]
    return '<a href="{}">{}</a>'.format(link, cont)


codes = []  # 所有区块
part = []  # 一个区块

# 读取数据
try:
    while True:
        ip = input()
        if ip:
            part.append(ip)
        else:
            codes.append(part)
            part = []
except EOFError:
    pass
if part:
    codes.append(part)

# 确定行数的测试代码
# for i in range(19):
#     ip = input()
#     if ip:
#         part.append(ip)
#     elif part:
#         codes.append(part)
#         part = []
# if part:
#     codes.append(part)

# 读取每个区块
for code in codes:
    # 标题
    if len(code) == 1 and code[0][0] == '#':
        h = code[0]
        i = h.count('#')
        content = h[i + 1:].strip()
        content = em_obj.sub(em_repl, content)
        content = a_obj.sub(a_repl, content)
        print('<h{}>{}</h{}>'.format(i, content, i))
    # 无序列表
    elif code[0][0] == '*':
        print('<ul>')
        for e in code:
            content = e[2:].strip()
            content = em_obj.sub(em_repl, content)
            content = a_obj.sub(a_repl, content)
            print('<li>{}</li>'.format(content))
        print('</ul>')
    # 段落
    else:
        print('<p>', end='')
        for j, e in enumerate(code):
            content = e.strip()
            content = em_obj.sub(em_repl, content)
            content = a_obj.sub(a_repl, content)
            print(content, end='')
            if j < len(code) - 1:
                print()
        print('</p>')






###   Hello


Hello, world!

_   Hello, world!   _


a [hello](https://www.zhihu.com) .
a _[hello](https://www.zhihu.com)_ .
a [_hello_](https://www.zhihu.com) .


*   132
* ewv
*  888ev

_ 1181 _  _ erverver _  _ rerrrg _
a [_hello_](https://www.zhihu.com) . --- [_hello_](https://www.zhihu.com)



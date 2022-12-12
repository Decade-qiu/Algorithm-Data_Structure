_month = ["","jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"]
_week = ["sun","mon","tue","wed","thu","fri","sat"]
month_of_day = [0,31,28,31,30,31,30,31,31,30,31,30,31]
month = {}
week = {}
for i in range(1, 13):
    month[_month[i]] = i
for i in range(0, 7):
    month[_week[i]] = i
def long_year(x):
    return (x%4==0 and x%100!=0) or (x%400==0);
def get_week(y, m, d):
    sy, sm, sd = 1970, 1, 1
    days = 0
    for i in range(sy, y):
        if (long_year(i)):
            days += 1
        days += 365
    for i in range(1, m):
        days += month_of_day[i]
        if i == 2 and long_year(y):
            days += 1
    days += d
    days %= 7
    return (days+3)%7
def ck_month(month, m):
    if m == '*':
        return True
    if '-' in m:
        sm, em = list(map(int, m.split('-')))
        if sm <= month <= em:
            return True
        return False
    if ',' in m:
        lst = list(map(int, m.split(',')))
        if month in lst:
            return True
        return False
    return int(m) == month
def ck_day(day, d):
    if d == '*':
        return True
    if '-' in d:
        sd, ed = list(map(int, d.split('-')))
        if sd <= day <= ed:
            return True
        return False
    if ',' in d:
        lst = list(map(int, d.split(',')))
        if day in lst:
            return True
        return False
    return int(d) == day
def ck_week(week, w):
    if w == '*':
        return True
    if '-' in w:
        sd, ed = list(map(int, w.split('-')))
        if sd <= week <= ed:
            return True
        return False
    if ',' in w:
        lst = list(map(int, w.split(',')))
        if week in lst:
            return True
        return False
    return int(w) == week
def aya_op(minu, hour, command, cy, cm, cd, dx):
    xmin, ymin, xhour, yhour = 0, 59, 0, 23
    if cy == st_y and cm == st_m and cd == st_d:
        xmin, xhour = st_mm, st_hh
    if cy == ed_y and cm == ed_m and cd == ed_d:
        ymin, yhour = ed_mm, ed_hh
    minutes = []
    hours = []
    if minu == '*':
        minutes = [i for i in range(0, 60)]
    elif '-' in minu:
        sd, ed = list(map(int, minu.split('-')))
        minutes = [i for i in range(sd, ed+1)]
    elif ',' in minu:
        minutes = list(map(int, minu.split(',')))
    else:
        minutes = [int(minu)]
    if hour == '*':
        hours = [i for i in range(0, 60)]
    elif '-' in hour:
        sd, ed = list(map(int, hour.split('-')))
        hours = [i for i in range(sd, ed+1)]
    elif ',' in hour:
        hours = list(map(int, hour.split(',')))
    else:
        hours = [int(hour)]
    ans = []
    for i in range(hours):
        if not (xhour <= i <= yhour):
            continue
        for j in range(minutes):
            if not (xmin <= i <= ymin):
                continue
            ans.append([i, j, dx, command])
    return ans;

inp = input().split()
n, st, ed = int(inp[0]), inp[1], inp[2]
st_y, st_m, st_d = list(map(int, [st[0:4], st[4:6], st[6:8]]))
ed_y, ed_m, ed_d = list(map(int, [ed[0:4], ed[4:6], ed[6:8]]))
year, month, day = st_y, st_m, st_d
ed_year, ed_month, ed_day = ed_y, ed_m, ed_d
week = get_week(st_y, st_m, st_d)
st_hh, st_mm = list(map(int, [st[8:10], st[10:12]]))
ed_hh, ed_mm = list(map(int, [ed[8:10], ed[10:12]]))
cmd = []
for i in range(n):
    lst = input().split()
    mo, we = lst[3], lst[4]
    if mo != '*' and not ('0'<=mo[0]<='9'):
        if '-' in mo:
            mos = mo.split('-')
            lst[3] = '-'.join([month[i.lower()] for i in mos])
        elif ',' in mo:
            mos = mo.split(',')
            lst[3] = ','.join([month[i.lower()] for i in mos])
    if we != '*' and not ('0'<=we[0]<='9'):
        if '-' in we:
            wes = we.split('-')
            lst[4] = '-'.join([week[i.lower()] for i in wes])
        elif ',' in mo:
            wes = we.split(',')
            lst[4] = ','.join([week[i.lower()] for i in wes])
    cmd.append(' '.join(lst))
while year <= ed_year and month <= ed_month and day <= ed_day:
    cur_ans = []
    for i in range(n):
        op = cmd[i].split()
        if ck_month(month, op[3]) and ck_day(day, op[2]) and ck_week(week, op[4]):
            cur_ans.extend(aya_op(op[0], op[1], op[5], year, month, day, i))
    cur_ans.sort(key=lambda x : (x[0], x[1], x[2]))
    for t in cur_ans:
        time = str(year)+str(month).rjust(2,'0')+str(day).rjust(2,'0')
        time += str(t[0]).rjust(2,'0')+str(t[1]).rjust(2,'0')
        print(time, t[3])
    week = (week+1)%7
    day += 1
    maxday = month_of_day[month]
    if month == 2 and long_year(year):
        maxday += 1
    if day > maxday:
        day = 0
        month += 1
        if month > 12:
            month = 0
            year += 1
    
    

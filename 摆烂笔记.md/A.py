import numpy as np
import math


x = [
 "78.45 0 0	6.08 1.86	7.23	2.15	2.11	0	0	1.06	0.03	0	0.51",
 "37.75	0	0	7.63	0	2.33	0	0	34.3	0	14.27	0	0	0",
 "31.95	0	1.36	7.19	0.81	2.93	7.06	0.21	39.58	4.69	2.68	0.52	0	0",
 "35.47	0	0.79	2.89	1.05	7.07	6.45	0.96	24.28	8.31	8.45	0.28	0	0",
 "64.29	1.2	0.37	1.64	2.34	12.75	0.81	0.94	12.23	2.16	0.19	0.21	0.49	0",
 "93.17	0	1.35	0.64	0.21	1.52	0.27	1.73	0	0	0.21	0	0	0",
 "90.83	0	0.98	1.12	0	5.06	0.24	1.17	0	0	0.13	0	0	0.11",
 "51.12 	0.00 	0.23 	0.89 	0.00 	2.12 	0.00 	9.01 	21.24 	11.34 	1.46 	0.31 	0.00 	2.26 "
]

data = []
for i in x:
    tp = i.split()
    data.append(tp)

n = len(data)
m = len(data[0])

wait = [0.1, 0.12, 0.14, 0.16, 0.18, 0.20, 0.25, 0.3]
for err in wait:
    error = np.random.uniform(low=1-err,high=1+err,size=(n, m))
    for i in range(n):
        for j in range(m):
            ori = float(data[i][j])
            data[i][j] = ori*error[i][j]
            print(round(data[i][j], 2), end = " ")
        print()

# print(data)

def fun(l, h, max, min):
    tp = math.sqrt(0.25*math.pow(h-l, 2)/math.pow(max-min, 2))
    return round(1-2*tp, 2)

gk = ["二氧化硅(SiO2)", "氧化钾(K2O)", "氧化铝(Al2O3)"]
val0 = [[76.68, 69.33, 96.77,59.01], 
        [9.42, 7.37, 14.52, 0], 
        [6.19, 5.5, 0.81, 11.15]]

for i in range(len(gk)):
    print(gk[i], end=" ")
    print(fun(val0[i][0], val0[i][1], val0[i][2], val0[i][3]))

qb = ["二氧化硅(SiO2)", "氧化铅(PbO)", "氧化钡(BaO)"]
val = [[[37.36,49.01,75.51,24.61],[26.25,24.61,49.01,17.11],[16.71,20.14, 3.72,62.25]],
        [[44.12,49.31,29.14,70.21],[32.45,25.39,25.39,49.31],[31.9,28.68,31.9,9.3]],
        [[30.62,23.55,35.45,2.03],[14.2,2.03,23.55,2.03],[26.23,0,0,26.23]]]

for i in range(3):
    mm = 0
    print(qb[i], end=" ")
    for j in range(3):
        tp = fun(val[i][j][0], val[i][j][1], val[i][j][2], val[i][j][3])
        print(tp, end=" ")
        mm = max(tp, mm)
    print(mm)


# 二氧化硅(SiO2)  0.11
# 氧化铅(PbO)     0.09
# 氧化钡(BaO)     0.13
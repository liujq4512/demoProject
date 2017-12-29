#!/usr/bin/python3

# if,elif,else 有一个成立，下面的便不会校验
num=20
if num%2==0:
    print ("condition 1")
    print("condition 1")

if num==30:
    print ("new condition")

elif num>10:
    print ("condition 2")
    print ("condition 2")

else :
    print ("condition 3")
    print ("condition 3")



while(num > 5):
    print ("num=====",end=',')
    print(num)
    num-=1
    if(num==10):
        break


setA = set('sldlfsdsssss') #会自动去除重复元素
print (setA)
for a in setA:
    print ("a = ",end=',')
    print(a)

for i in range(3,20,3): #在范围内按步长增加
    print (">>>>> : ",i)

listA = list(range(6))
print (listA)
it = iter(listA)
for x in it:
    print ("x in iterater is : " ,x)
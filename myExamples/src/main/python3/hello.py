#!/usr/bin/python3

x="hello world"
a, b, c = 1, 3, "I'm c"
print(x)
print(a + b )
a="toChar"
print(c)
print(a)
print(4/2)
print (6//3) #会返回整型结果

print(x[0:-1])
print(x[2:4])
print(x * 2)
print (x + a)

print (r"hello \n world")
print ("hello \n world")

list0=["first","second",4,5,["zero",1,2]]
list1=[a,b,c]
print (list0[4][1])
print (list0[0:2])
print (list0+list1)

tuple=(0,1,2,list0,list1)
print (tuple)

tuple[3][0]="first changed"
print (tuple)

set0={"tom","tom",1,1,2,3}
print (set0)
if(1 in set0):
    print ("1 in set")

setA = set('alsdfja;skfj;asd')
setB = set('ssllfsdjfd')
print (setA)
print (setA-setB)
print (setA^setB)


myDictionary = {1:1,"name":"liujq","list":list0,"tuple":tuple,"setA":setA}
print (myDictionary)
print (myDictionary["name"])
print (myDictionary.keys())
print (myDictionary.values())


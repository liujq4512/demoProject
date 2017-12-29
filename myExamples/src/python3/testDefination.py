#!/usr/bin/python3

def change(a):
    a = "hello world"
    return a


m = 2
m = change(m)
print (m)

def showArgs(*args):
    for i in args:
        print (i," in args")

showArgs("hello",2,False)
#!/bin/bash

find src -iname "*.java" > classes.txt
javac -d bin @classes.txt
rm classes.txt


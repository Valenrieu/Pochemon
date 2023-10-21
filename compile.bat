dir /s /b "*.java" > classes.txt
javac -d bin @classes.txt
del /f classes.txt

#!/bin/bash
# This file contains all of the commands required to build all of the sub projects within this repository.

# Building the project under "JavaProject"
echo 'Building java project "JavaProject"...'
javac $(find JavaProject -name "*.java") 

# Building the project under "test"
echo 'Building java project "test"...'
javac $(find test -name "*.java")

# Build the project "webapp"
echo 'Building python project "webapp"...'
python -m py_compile $(find . -name "*.py")

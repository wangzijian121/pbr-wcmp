#!/bin/bash

PID=$(ps -ef | grep 'com.zlht.pbr.algorithm.wcmp.AlgorithmWcmpApi' | grep -v grep | awk '{print $2}')

if [ -z "$PID" ]; then
    echo "No AlgorithmwcmpApi process found to stop"
    exit 1
fi

echo "Stopping AlgorithmwcmpApi process $PID"
kill $PID

exit 0

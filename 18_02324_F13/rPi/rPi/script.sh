#!/bin/bash

# Script for use in java program, in order to circumvent need for sudo since 
# rights to script can be set
# 2013 Niclas Falck

echo startin shellscript

#tcpdump -s 1024 -l -A 'port 80'

#tcpdump -i en0 -v 'port 80'

ngrep -q &

#/opt/local/bin/ngrep -q '^(GET|POST|HEAD|CONNECT)' 'tcp' &

#2>&1 | grep --line-buffered .

echo $! > ngrep.pid

echo goodbye

#!/bin/sh

pid=bash cat process1.pid
echo $pid
kill $(cat process1.pid)


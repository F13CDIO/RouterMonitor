#!/bin/sh

sudo kill `ps aux | grep tshark | awk '{print $2}'`


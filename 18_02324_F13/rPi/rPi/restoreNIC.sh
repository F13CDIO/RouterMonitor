#!/bin/bash
#restores the normal state of the NID's

sudo iw dev en0 del
sudo ifconfig wlan0 up

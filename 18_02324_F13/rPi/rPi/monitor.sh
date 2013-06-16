#!/bin/bash
#This script creates a new interface for monitoring "mon0" and activates it
#Deactivate the old interface "wlan0"
#
#To make this script work without sudo add the following lines to the sudoers file:
#<username>    ALL=(ALL) NOPASSWD: /sbin/ifconfig*
#<username>    ALL=(ALL) NOPASSWD: /sbin/iw*
#<username>    ALL=(ALL) NOPASSWD: /sbin/iwlist*
#<username>    ALL=(ALL) NOPASSWD: /sbin/iwconfig*

sudo iw phy phy0 interface add mon0 type monitor
sudo ifconfig wlan0 down
sudo ifconfig mon0 up

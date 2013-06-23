#!/bin/bash
#This script creates a new interface for monitoring "en0" and activates it
#Deactivate the old interface "wlan0"
#
#To make this script work without sudo add the following lines to the sudoers file:
#<username>    ALL=(ALL) NOPASSWD: /sbin/ifconfig*
#<username>    ALL=(ALL) NOPASSWD: /sbin/iw*
#<username>    ALL=(ALL) NOPASSWD: /sbin/iwlist*
#<username>    ALL=(ALL) NOPASSWD: /sbin/iwconfig*

sudo iw phy phy0 interface add en0 type monitor
#sudo iw dev wlan0 del -> denne linje skal bruges paa pien
sudo ifconfig wlan0 down # -> denne linje bruges til at teste paa computer
sudo ifconfig en0 up

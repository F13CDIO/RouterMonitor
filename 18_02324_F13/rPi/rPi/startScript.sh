#!/bin/sh

# REQUIRES THAT TSHARK IS INSTALLED!

# get absolute path to tshark no matter where its installed
tsharkPrefix=$(which tshark)
echo $tsharkPrefix

# check if var tsharkPrefix is zero length string, then try mac path, otherwise it's not installed
if [[ -z "$tsharkPrefix" ]]; then
	/usr/local/bin/tshark -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -I -R http.request tcp port 80 and ip
else
	$tsharkPrefix  -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -I -R http.request tcp port 80 and ip
fi
# save process id to file
echo $! > process1.pid

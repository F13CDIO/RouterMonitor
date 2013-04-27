#!/bin/sh

# get absolute path to tshark no matter where its installed
tsharkPrefix=$(which tshark)
echo $tsharkPrefix

# check if var tsharkPrefix is zero length string
if [[ -z "$tsharkPrefix" ]]; then 
	echo could not get tshark path, might not be installed 
else
	bash $tsharkPrefix -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -i en0 -I -R http.request tcp port 80 and ip
fi
# save process id to file
$! > process1.pid

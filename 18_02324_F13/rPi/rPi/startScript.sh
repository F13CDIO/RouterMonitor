#!/bin/sh

#der er problemer med at dette scripts skriver data samlings lortet paa linux. da den udskriver dataen i en ny "session"

# REQUIRES THAT TSHARK IS INSTALLED!
2>&1
# get absolute path to tshark no matter where its installed
tsharkPrefix=$(which tshark)
#echo $tsharkPrefix

# check if var tsharkPrefix is zero length string, then try mac path, otherwise it's not installed
if [[ -z "$tsharkPrefix" ]]; then
	/usr/local/bin/tshark -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -I -R http.request tcp port 80 and ip
else
	sudo tshark -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -i en0 -I -R http.request tcp port 80 and ip
fi

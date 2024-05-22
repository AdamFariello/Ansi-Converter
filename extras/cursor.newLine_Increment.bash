#!/bin/bash
# based on a script from http://invisible-island.net/xterm/xterm.faq.html
# based on an answer from: https://unix.stackexchange.com/a/88304
exec < /dev/tty

oldstty=$(stty -g)
stty raw -echo min 0 #Disables echo command, 
                     #Also sets minimum 0 characters for a complete read

# Either of these two commands work
echo -en "\033[6n" > /dev/tty
#tput u7 > /dev/tty    # when TERM=xterm (and relatives)

oldIFS=' '
IFS=';' #Decide what character to split the string by to make the array

#read -r -d R -a pos        #(Original)
#   "-r": Makes read not interpret '\' as an escape character
#   "-d": Read given input (the output of "\033[6n") until "R"
#   "-a pos": Put output into array "pos"
read -r -d "R" -a arr       #(New)

stty $oldstty #Reset original arguments for tty/terminal (re-enable echo)


# change from one-based to zero based so they work with: tput cup $row $col
# These values also are based on the next lines values specifically
row=$((${arr[0]:2} - 1))    #strip off the esc-[; also subtract 1 for current line
col=$((${arr[1]} - 1))      #Subtracted by one since it's above if for some reason

# Output the file
#echo "$row,$col"
#echo "$row,$col" >> temp.txt   #">>": Appends. Use one arrow to rewrite
#echo "$row,$col" > outputFile.txt
if [ "$#" -ne 1 ] ; then
    echo "$row $col" > outputFile.txt
else
    echo "$row $col" > $1
fi
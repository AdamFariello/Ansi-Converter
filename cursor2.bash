#IFS=';' read -sdR -p $'\E[6n' ROW COL;echo "${ROW#*[}"
#IFS=';' read -sdR -p $'\E[6n' ROW COL;echo "${ROW#*[}" "${COL#*[}"

IFS=';' 
read -sdR -p $'\E[6n' ROW COL
#   "-s": Hides input given to the command, perfect to hiding password input
#   "-d": Read given input (the output of "\033[6n") until "R" (space does not matter...)
#   "-p": Custom promp, used here to feed read the ansi string
#echo "${ROW#*[} ${COL#*[}"

echo "${ROW#*[} ${COL#*[}" > temp.txt
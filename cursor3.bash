#!/bin/bash

# Get current settings.
if ! termios="$(stty -g 2>/dev/null)" ; then
    echo "Not running in a terminal." >&2
    exit 1
fi

# Restore terminal settings when the script exits.
trap "stty '$termios'" EXIT

# Disable ICANON ECHO. Should probably also disable CREAD.
stty -icanon -echo

# Request cursor coordinates
printf '\033[6n'

# Read response from standard input; note, it ends at R, not at newline
read -d "R" rowscols

# Clean up the rowscols (from \033[rows;cols -- the R at end was eaten)
rowscols="${rowscols//[^0-9;]/}"
rowscols=("${rowscols//;/ }")
#printf '(row %d, column %d) ' ${rowscols[0]} ${rowscols[1]}
#printf '%d %d' ${rowscols[0]} ${rowscols[1]}
printf '%d %d' ${rowscols[0]} ${rowscols[1]} > outputFile.txt

# Reset original terminal settings.
stty "$termios"
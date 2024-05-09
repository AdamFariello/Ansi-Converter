import os, re, sys, termios, tty

def getpos():
    buf = ""
    stdin = sys.stdin.fileno()
    tattr = termios.tcgetattr(stdin)

    try:
        #Turn off terminal characters being printed
        tty.setcbreak(stdin, termios.TCSANOW)

        #Print the character
        sys.stdout.write("\x1b[6n")
        sys.stdout.flush()

        while True:
            buf += sys.stdin.read(1)
            if buf[-1] == "R":
                break

    finally:
        #Turn on printing terminal characters
        termios.tcsetattr(stdin, termios.TCSANOW, tattr)

    # reading the actual values, but what if a keystroke appears while reading
    # from stdin? As dirty work around, getpos() returns if this fails: None
    try:
        matches = re.match(r"^\x1b\[(\d*);(\d*)R", buf)
        groups = matches.groups()
    except AttributeError:
        return None

    #return (int(groups[0]), int(groups[1]))
    return  groups[0] + " " + groups[1]


if __name__ == "__main__":
    print(getpos())
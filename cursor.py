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


def cursorPos():
    OldStdinMode = termios.tcgetattr(sys.stdin)
    _ = termios.tcgetattr(sys.stdin)
    _[3] = _[3] & ~(termios.ECHO | termios.ICANON)

    #Turns off standard output by giving a turned off standard output option
    #And setting with it.
    #Just use "tty.setcbreak(stdin)" ...
    termios.tcsetattr(sys.stdin, termios.TCSAFLUSH, _)

    try:
        _ = ""
        sys.stdout.write("\x1b[6n")
        #sleep(0.1)
        sys.stdout.flush()
        while not (_ := _ + sys.stdin.read(1)).endswith('R'):
            True
            
        #res = re.match(r".*\[(?P<y>\d*);(?P<x>\d*)R", _)
        #res = re.match(r".*\[(?P<x>\d*);(?P<y>\d*)R", _)
        res = re.match(r".*\[(?P<row>\d*);(?P<col>\d*)R", _)


    finally:
        termios.tcsetattr(sys.stdin, termios.TCSAFLUSH, OldStdinMode)

    '''
    if(res):
        return (res.group("x"), res.group("y"))
    else:
        return (-1, -1)
    '''

    if(res):
        return res.group("row") + " " + res.group("col")
    else:
        return -1 + " " -1

if __name__ == "__main__":
    with open("output.txt", "w") as f:
        #print(getpos())
        #print(cursorPos(), file = f)

        s = cursorPos()
        print(s)
        f.write(s)

        #f.write(cursorPos())
package java_oop;

import java.util.ArrayList;

class AnsiCursor extends Ansi {
    ArrayList<String> storeS = new ArrayList<String>();

    public AnsiCursor () { }

    //Reset
    public AnsiCursor toHome() { storeS.add(ESCAPE + "[" + "H"); return this; }

    public AnsiCursor up   (int i) { storeS.add(ESCAPE + "[" + i + "A"); return this; }
    public AnsiCursor down (int i) { storeS.add(ESCAPE + "[" + i + "B"); return this; }
    public AnsiCursor right(int i) { storeS.add(ESCAPE + "[" + i + "C"); return this; }
    public AnsiCursor left (int i) { storeS.add(ESCAPE + "[" + i + "D"); return this; }

    public AnsiCursor downAndStart(int i) { storeS.add(ESCAPE + "[" + i + "E"); return this; }
    public AnsiCursor upAndStart (int i) { storeS.add(ESCAPE + "[" + i + "F"); return this; }
    public AnsiCursor toColumn(int i) { storeS.add(ESCAPE + "[" + i + "G"); return this; }
    public AnsiCursor toLineToColumn(int i, int j) { 
        storeS.add(ESCAPE + "[" + i + ";" + j + "f"); return this;
    }

    //TODO: test seperately, unsure if they work
    public AnsiCursor getCursorPosition() { storeS.add(ESCAPE + "6N"); return this; }
    public AnsiCursor saveCursorPosition_dec() { storeS.add(ESCAPE + "7"); return this; }
    public AnsiCursor restoreCursorPosition_dec() { storeS.add(ESCAPE + "8"); return this; }
    public AnsiCursor saveCursorPosition_sco() { storeS.add(ESCAPE + "[" + "s"); return this; }
    public AnsiCursor restoreCursorPosition_sco() { storeS.add(ESCAPE + "[" + "u"); return this; }

    //Clear Screen
    public AnsiCursor clearScreen() { storeS.add(ESCAPE + "[" + "J"); return this; }
    public AnsiCursor clearScreen_endOfScreen()  { storeS.add(ESCAPE + "[" + "0J"); return this; }
    public AnsiCursor clearScreen_begOfScreen()  { storeS.add(ESCAPE + "[" + "1J"); return this; }
    public AnsiCursor clearScreen_entireScreen() { storeS.add(ESCAPE + "[" + "2J"); return this; }

    //Clear Line
    public AnsiCursor clearLine ()           { storeS.add(ESCAPE + "[" + "K"); return this; }
    public AnsiCursor clearLine_endOfLine()  { storeS.add(ESCAPE + "[" + "0K"); return this; }
    public AnsiCursor clearLine_begOfLine()  { storeS.add(ESCAPE + "[" + "1K"); return this; }
    public AnsiCursor clearLine_entireLine() { storeS.add(ESCAPE + "[" + "2K"); return this; }


    /*
    //TODO: Figure out the correct arguments for these
    //Visuals
    public AnsiCursor slow_blink () { args += "5;"; return this; }
    public AnsiCursor blink_off () { args += "25;"; return this; }
    public AnsiCursor rapid_blink () { args += "6;"; return this; }
    */

    public AnsiCursor reset() { storeS.clear(); return this; }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (String s : storeS) {
            buffer.append(s);
        }
        return buffer.toString();
    }
    public String toStringln() {
        StringBuffer buffer = new StringBuffer();
        for (String s : storeS) {
            buffer.append(s + "\n");
        }
        return buffer.toString();
    }  

    @Override
    public void println() { 
        System.out.print(toString());
    }
}


final class AnsiCursorDemo extends AnsiCursor {
    //TODO
}  
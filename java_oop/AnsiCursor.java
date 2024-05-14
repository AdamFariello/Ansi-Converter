package java_oop;

import java.util.ArrayList;

class AnsiCursor extends Ansi {
    ArrayList<String> storeS = new ArrayList<String>();

    public AnsiCursor () { }

    private AnsiCursor add (String s) { 
        storeS.add(ESCAPE + s); 
        return this; 
    }
    public AnsiCursor reset() { storeS.clear(); return this; }

    //Reset
    public AnsiCursor toHome() { storeS.add("[" + "H"); return this; }

    public AnsiCursor up   (int i) { return add("[" + i + "A"); }
    public AnsiCursor down (int i) { return add("[" + i + "B"); }
    public AnsiCursor right(int i) { return add("[" + i + "C"); }
    public AnsiCursor left (int i) { return add("[" + i + "D"); }

    public AnsiCursor downAndStart(int i) { return add("[" + i + "E"); }
    public AnsiCursor upAndStart (int i) { return add("[" + i + "F"); }
    public AnsiCursor toColumn(int i) { return add("[" + i + "G"); }
    public AnsiCursor toLineToColumn(int i, int j) { return add("[" + i + ";" + j + "f"); }

    //TODO: Add file input implementation
    //public AnsiCursor getCursorPosition() { return add("6N"); } 

    public AnsiCursor saveCursorPosition_dec() { return add("7"); }
    public AnsiCursor restoreCursorPosition_dec() { return add("8"); }
    public AnsiCursor saveCursorPosition_sco() { return add("[" + "s"); }
    public AnsiCursor restoreCursorPosition_sco() { return add("[" + "u"); }

    //Clear Screen
    public AnsiCursor clearScreen() { return add("[" + "J"); }
    public AnsiCursor clearScreen_endOfScreen()  { return add("[" + "0J"); }
    public AnsiCursor clearScreen_begOfScreen()  { return add("[" + "1J"); }
    public AnsiCursor clearScreen_entireScreen() { return add("[" + "2J"); }

    //Clear Line
    public AnsiCursor clearLine ()           { return add("[" + "K"); }
    public AnsiCursor clearLine_endOfLine()  { return add("[" + "0K"); }
    public AnsiCursor clearLine_begOfLine()  { return add("[" + "1K"); }
    public AnsiCursor clearLine_entireLine() { return add("[" + "2K"); }


    /*
    //TODO: Figure out the correct arguments for these
    //Visuals
    public AnsiCursor slow_blink () { args += "5;"; return this; }
    public AnsiCursor blink_off () { args += "25;"; return this; }
    public AnsiCursor rapid_blink () { args += "6;"; return this; }
    */

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (String s : storeS) {
            System.out.print(s);
            buffer.append(s);
        }
        return buffer.toString();
    }
    public String toStringln() {
        StringBuffer buffer = new StringBuffer();
        for (String s : storeS) {
            System.out.println(s);
            buffer.append(s + "\n");
        }
        return buffer.toString();
    }  
    @Override
    public void print() {
        for (String s : storeS) {
            System.out.print(s);
        }
    }
    @Override
    public void println() { 
        for (String s : storeS) {
            System.out.println(s);
        }
    }
}


final class AnsiCursorDemo extends AnsiCursor {
    //TODO
    private AnsiCursor ansi; 
    public AnsiCursorDemo () {
        ansi = new AnsiCursor();
    }

    public void clearRow (int i) {
        ansi.up(i).clearLine().print();
        ansi.reset().down(i).print();
    }


    public void saveAndRestore() {
        ansi.saveCursorPosition_dec().print();
        ansi.reset().up(10).print();
        System.out.print("FIRST OVERWRITE");
        
        ansi.restoreCursorPosition_dec().print();
        System.out.print("SECOND OVERWRITE");
    }
}  
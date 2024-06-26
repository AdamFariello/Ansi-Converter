package java_oop;

import java.util.*;

public class AnsiCursor extends Ansi {
    HashMap<String, int[]> cursorPositions; 
    
    public AnsiCursor () {
        cursorPositions = new HashMap<String, int[]>();
    }
   
    //Inhereneted functions
    public AnsiCursor write(String s) {
        System.out.print(CSI + s); 
        return this; 
    }
    public AnsiCursor writeRaw(String s) {
        String tempCSI = "\\u001B" + "[";
        System.out.print(tempCSI + s); 
        return this;
    }
    public AnsiCursor reset() {
        toHome();
        System.out.println("\n");
        return this;
    }
    public AnsiCursor print(String s) { System.out.print(s); return this; }
    public AnsiCursor println(String s) { System.out.println(s); return this; }


    //Takes you to the terminal line where you inputted the command
    public AnsiCursor toHome() { return write("H"); }

    //Cursor Controller
    public AnsiCursor up   (int i) { return write(i + "A"); }
    public AnsiCursor down (int i) { return write(i + "B"); }
    public AnsiCursor right(int i) { return write(i + "C"); }
    public AnsiCursor left (int i) { return write(i + "D"); }

    public AnsiCursor downAndStart(int i) { return write(i + "E"); }
    public AnsiCursor upAndStart (int i) { return write(i + "F"); }
    public AnsiCursor toColumn(int i) { return write(i + "G"); }

    //ESC[{line};{column}H
    //ESC[{line};{column}f
    public AnsiCursor toLineToColumn(int line, int col) { 
        return write(line + ";" + col + "H"); 
    }
    public AnsiCursor toLineToColumn_startOfLine(int line, int col) { 
        return write(line + ";" + col + "f"); 
    }


    //Scroll up adds words to the bottom of the screen
    //Scroll down adds words to the top of the screen
    public AnsiCursor scrollUp()   { return write("S"); }
    public AnsiCursor scrollUp(int i)   { return write(i + "S"); }
    public AnsiCursor scrollDown() { return write("T"); }
    public AnsiCursor scrollDown(int i) { return write(i + "T"); }


    //You cannot interchange to have two saved cursor positions
    //Dec is used more often, so it'll be used instead
    //      Save        Restore
    //Dec   ESC + 7     ESC + 8
    //SCO   ESC + [s    ESC + [u
    public AnsiCursor saveCursorPosition() { return write("7"); }
    public AnsiCursor restoreCursorPosition() { return write("8"); }

    
    //Clear Screen
    public AnsiCursor clearScreen_entire() { return write("J"); }
    public AnsiCursor clearScreen_cursorToEnd()  { return write("0J"); }
    public AnsiCursor clearScreen_cursorToBeg()  { return write("1J"); }
    public AnsiCursor clearScreen_cursorToEntireScreen() { return write("2J"); }

    //Clear Line
    public AnsiCursor clearLine_current ()           { return write("K"); }
    public AnsiCursor clearLine_cursorToEnd()  { return write("0K"); }
    public AnsiCursor clearLine_cursorToStart()  { return write("1K"); }
    public AnsiCursor clearLine_entire() { return write("2K"); }


    public AnsiCursor blink_on () { return write("5m"); }
    public AnsiCursor blink_off () { return write("25m"); }
    public AnsiCursor rapid_blink () { return write("6m"); }


    public AnsiCursor storeCurrentCursorPosition(String key) { 
        ansiUtil.ScriptHandler.runScript();
        cursorPositions.put(key, ansiUtil.ScriptHandler.readOutputFile());
        return this;
    }
    public AnsiCursor getCursorPosition(String key) {
        int[] cursorPositon = cursorPositions.get(key);

        if (cursorPositon != null) {
            return toLineToColumn(cursorPositon[0], cursorPositon[1]);
        } else {
            return this;
        }
    }
    public AnsiCursor removeCurrentCursorPosition(String key) {
        cursorPositions.remove(key);
        return this;
    } 
}
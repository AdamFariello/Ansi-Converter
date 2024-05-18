package java_oop;

import java.io.*;
import java.util.*;


class AnsiCursor extends Ansi {
    
    static HashMap<String, int[]> cursorPositions; 
    public AnsiCursor () {
        cursorPositions = new HashMap<String, int[]>();


    }

    private AnsiCursor write(String s) {
        System.out.print(ESCAPE + s); 
        return this; 
    }

    //Takes you to the terminal line where you inputted the command
    public AnsiCursor toHome() { return write("[" + "H"); }

    //Cursor Controller
    public AnsiCursor up   (int i) { return write("[" + i + "A"); }
    public AnsiCursor down (int i) { return write("[" + i + "B"); }
    public AnsiCursor right(int i) { return write("[" + i + "C"); }
    public AnsiCursor left (int i) { return write("[" + i + "D"); }

    public AnsiCursor downAndStart(int i) { return write("[" + i + "E"); }
    public AnsiCursor upAndStart (int i) { return write("[" + i + "F"); }
    public AnsiCursor toColumn(int i) { return write("[" + i + "G"); }

    //ESC[{line};{column}H
    //ESC[{line};{column}f
    protected AnsiCursor toLineToColumn(int line, int col) { 
        return write("[" + line + ";" + col + "H"); 
    }
    protected AnsiCursor toLineToColumn_startOfLine(int line, int col) { 
        return write("[" + line + ";" + col + "f"); 
    }


    //TODO: Test these functions
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
    public AnsiCursor clearScreen() { return write("[" + "J"); }
    public AnsiCursor clearScreen_endOfScreen()  { return write("[" + "0J"); }
    public AnsiCursor clearScreen_begOfScreen()  { return write("[" + "1J"); }
    public AnsiCursor clearScreen_entireScreen() { return write("[" + "2J"); }

    //Clear Line
    public AnsiCursor clearLine ()           { return write("[" + "K"); }
    public AnsiCursor clearLine_endOfLine()  { return write("[" + "0K"); }
    public AnsiCursor clearLine_begOfLine()  { return write("[" + "1K"); }
    public AnsiCursor clearLine_entireLine() { return write("[" + "2K"); }


    //TODO: Figure out the correct arguments for these
    //Visuals
    public AnsiCursor slow_blink () { return write("5;"); }
    public AnsiCursor blink_off () { return write("25;"); }
    public AnsiCursor rapid_blink () { return write("6;"); }

    public String toString() {
        return "";
    }
    public AnsiCursor print(String s) { System.out.print(s); return this; }
    public AnsiCursor println(String s) { System.out.println(s); return this; }
}


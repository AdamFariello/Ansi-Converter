package java_functional;

import java.util.*;

//TODO: Figure out if I want to refactor or not
//      (Don't want to, like interfaces being as private as possible)
interface Interface_Cursor { 
    final static String ESC = "\u001B";
    final static String CSI = ESC + "[";
    final static String END = CSI + "0m";

    final static String ESC_raw = "\\u001B";
    final static String CSI_raw = ESC_raw + "[";
    final static String END_raw = CSI_raw + "0m";

    default void write(String s) {
        System.out.print(CSI + s);
    }
    
    default void writeRaw(String s){
        System.out.print(CSI_raw + s);
    }
}

public class AnsiWithLocks extends Ansi implements Interface_Cursor {
    HashMap<String, int[]> cursorPositions;
    ScriptHandler scriptHandler;
    Boolean isScreenScrollingDisabled;

    public AnsiWithLocks() {
        isScreenScrollingDisabled = false;
        cursorPositions = new HashMap<String, int[]>();
        scriptHandler = new ScriptHandler();
    }

    public void setScreenScrolling(Boolean b) {
        isScreenScrollingDisabled = b;
        //return this;
    }


    public void toLineToColumn(int line, int col) {
        if (isScreenScrollingDisabled) {
            Cursor.To.LINETOCOLUMN.reg(line, col);
        }
    }


    // TODO: Test these functions
    // This solutions seems to only get the bottom of the terminal screen.
    public void storeCurrentCursorPosition(String key) {
        if (isScreenScrollingDisabled) {
            // Output the cursor position to a file, and the read it back
            scriptHandler.runScript();
            cursorPositions.put(key, scriptHandler.readOutputFile());
        }
    }

    public void getCursorPosition(String key) {
        if (isScreenScrollingDisabled) {
            int[] cursorPositon = cursorPositions.get(key);
            toLineToColumn(cursorPositon[0], cursorPositon[1]);
        } 
    }

    public Boolean removeCurrentCursorPosition(String key) {
        if (cursorPositions.remove(key) != null) {
            return true;
        } else {
            return false;
        }
    }
}
package java_functional;

import java.util.*;


class AnsiGame extends Ansi implements Interface_Cursor {
    HashMap<String, int[]> cursorPositions;
    ScriptHandler scriptHandler;
    Boolean isScreenScrollingDisabled;

    public AnsiGame() {
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
            String id = String.format("%d;%dH", line, col);
            write(id);
        }
    }

    public void toLineToColumn_startOfLine(int line, int col) {
        if (isScreenScrollingDisabled) {
            String id = String.format("%d;%df", line, col);
            write(id);
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
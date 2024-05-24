package java_oop;

import java.io.*;
import java.util.*;

class ScriptHandler {
    final String command = "bash";
    final String outputFileName = "temp.txt";
    final String currDir = "java_oop";
    String scriptName, script, outputFile;

    ScriptHandler () {
        //"cursor.bash",
        //"cursor.newLine_noIncrement.bash", or
        //"cursor.newLine_Increment.bash"
        scriptName = "cursor.sameLine_noIncrement.bash";
        init();
    }
    ScriptHandler(String scriptName) {
        this.scriptName = scriptName;
        init();
    }
    private void init () {
        char fileSymbol = OSFileSymbol();        
        String pwd = System.getProperty("user.dir") + fileSymbol + currDir + fileSymbol;
        script = pwd + scriptName;
        outputFile = pwd + outputFileName;
    }


    private static char OSFileSymbol () {
        String OS = System.getProperty("os.name");
        if (OS.startsWith("Windows")) {
            return '\\';
        } else {
            return '/';
        }
    }
    protected void runScript() {
        //Run the process first; send the screen cords to a file
        try { 
            List<String> args = new ArrayList<String>();
            args.add(command); 
            args.add(script);
            args.add(outputFile);

            ProcessBuilder pb = new ProcessBuilder(args);
            pb.inheritIO();
            pb.start();
        } catch (Exception e) {
            System.out.println("Process could not be run");
            e.getStackTrace();
        } 
    }
    protected int[] readOutputFile () {
        String line = null;
        FileReader fr = null;
        try {
            File file = new File(outputFile);
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            line = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    // This is unrecoverable. Just report it and move on
                    e.printStackTrace();
                }
            }
        }

        //Returning the values
        if (line != null) {
            String [] arr = line.split(" ");
            int row = Integer.parseInt(arr[0]);
            int col = Integer.parseInt(arr[1]);
            return new int [] {row, col};
        } else {
            return null;
        }
    }
}

public class AnsiCursor extends Ansi {
    HashMap<String, int[]> cursorPositions; 
    ScriptHandler scriptHandler;
    Boolean isScreenResizable;
    
    public AnsiCursor () { init(false); }
    public AnsiCursor (Boolean isScreenResizable) { init(isScreenResizable); }
    private void init (Boolean isScreenResizable) {
        cursorPositions = new HashMap<String, int[]>();
        
        if( (this.isScreenResizable = isScreenResizable) == true) {
            scriptHandler = new ScriptHandler();
        } else {
            scriptHandler = null;
        }
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


    //TODO: Put with seperare AnsiClass for handiling settings
    //      Perhaps the settings class is created at objects implementation...
    public AnsiCursor blink_on () { return write("5m"); }
    public AnsiCursor blink_off () { return write("25m"); }
    public AnsiCursor rapid_blink () { return write("6m"); }


    //TODO: Test these functions
    //This solutions seems to only get the bottom of the terminal screen.
    public AnsiCursor storeCurrentCursorPosition(String key) { 
        if (isScreenResizable) {
            //Output the cursor position to a file, and the read it back
            scriptHandler.runScript(); 
            cursorPositions.put(key, scriptHandler.readOutputFile());
        }

        return this;
    }
    public AnsiCursor getCursorPosition(String key) {
        if (isScreenResizable) {
            int[] cursorPositon = cursorPositions.get(key);
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
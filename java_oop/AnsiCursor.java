package java_oop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class AnsiCursor extends Ansi {
    final String command = "bash";
    final String scriptName = "cursor.bash";
    final String outputFileName = "temp.txt";

    String script, outputFile;
    
    static String scriptLocation;
    HashMap<Object, int[]> cursorPositions; 
    public AnsiCursor () {
        cursorPositions = new HashMap<>();

        String currDir = System.getProperty("user.dir") + OSFileSymbol();
        script = currDir + scriptName;
        outputFile = currDir + outputFileName;
    }

    private static String OSFileSymbol () {
        String OS = System.getProperty("os.name");
        if (OS.startsWith("Windows")) {
            return "\\";
        } else {
            return "/";
        }
    }

    private AnsiCursor write(String s) {
        System.out.print(ESCAPE + s); 
        return this; 
    }

    public AnsiCursor toHome() { return write("[" + "H"); }

    //Cursor Controller
    public AnsiCursor up   (int i) { return write("[" + i + "A"); }
    public AnsiCursor down (int i) { return write("[" + i + "B"); }
    public AnsiCursor right(int i) { return write("[" + i + "C"); }
    public AnsiCursor left (int i) { return write("[" + i + "D"); }

    public AnsiCursor downAndStart(int i) { return write("[" + i + "E"); }
    public AnsiCursor upAndStart (int i) { return write("[" + i + "F"); }
    public AnsiCursor toColumn(int i) { return write("[" + i + "G"); }
    public AnsiCursor toLineToColumn(int i, int j) { return write("[" + i + ";" + j + "f"); }


    //You cannot interchange to have two saved cursor positions
    //Dec is used more often, so it'll be used instead
    //      Save        Restore
    //Dec   ESC + 7     ESC + 8
    //SCO   ESC + [s    ESC + [u
    public AnsiCursor saveCursorPosition() { return write("7"); }
    public AnsiCursor restoreCursorPosition() { return write("8"); }
    
    
    //TODO: Test these functions
    //Getting cursor positions
    public AnsiCursor storeCurrentCursorPosition(String key) { 
        //Using this doesn't properly give the value back.
        //return write("6N"); 

        //Output the cursor position to a file, and the read it back
        runScript(); 
        cursorPositions.put(key, readScript());

        return this;
    }
    private void runScript() {
        //Run the process first; send the screen cords to a file
        try { 
            List<String> args = new ArrayList<String>();
            args.add(command); 
            args.add(scriptLocation);
            args.add(outputFile);

            ProcessBuilder pb = new ProcessBuilder(args);
            pb.inheritIO();
            pb.start();
        } catch (Exception e) {
            System.out.println("Process could not be run");
            e.getStackTrace();
        } 
    }
    private int[] readScript () {
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
    public AnsiCursor getCursorPosition(String key) {
        int[] cursorPositon = cursorPositions.get(key);
        return toLineToColumn(cursorPositon[0], cursorPositon[1]);
    }
    public AnsiCursor removeCurrentCursorPosition(String key) {
        cursorPositions.remove(key);
        return this;
    }


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


    /*
    //TODO: Figure out the correct arguments for these
    //Visuals
    public AnsiCursor slow_blink () { args += "5;"; return this; }
    public AnsiCursor blink_off () { args += "25;"; return this; }
    public AnsiCursor rapid_blink () { args += "6;"; return this; }
    */

    public String toString() {
        return "";
    }
    public AnsiCursor print(String s) { System.out.print(s); return this; }
    public AnsiCursor println(String s) { System.out.println(s); return this; }
}


final class AnsiCursorDemo extends AnsiCursor {
    //TODO
    private AnsiCursor ansi; 
    public AnsiCursorDemo () {
        ansi = new AnsiCursor();
    }

    public void clearRow (int i) {
        ansi.up(i).clearLine().down(i);
    }
    
    public void saveAndRestore() {
        ansi.saveCursorPosition().up(10).print("FIRST OVERWRITE");
        ansi.restoreCursorPosition().println("SECOND OVERWRITE");
    }

    public void saveAndRestore_multiple() {
        String homeKey = "Home";
        String baseKey = "Second base";

        //First Home location saved
        ansi.storeCurrentCursorPosition(homeKey);

        //Second base 
        ansi.up(5).storeCurrentCursorPosition(baseKey);
        ansi.left(7).print("Up Left");
        ansi.getCursorPosition(baseKey).right(7).print("Up Right");
        
        //Return directly to home
        ansi.getCursorPosition(homeKey).left(7).print("Bottom Left");
        ansi.getCursorPosition(homeKey).right(7).print("Bottom Right");

        //For Terminal formatting
        //System.out.println();
    }

}
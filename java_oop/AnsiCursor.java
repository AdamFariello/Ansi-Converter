package java_oop;

import java.io.*;
import java.util.*;

class fileHandler {
    final String command = "bash";
    final String outputFileName = "temp.txt";
    final String currDir = "java_oop";
    String scriptName, script, outputFile;

    fileHandler () {
        //"cursor.bash",
        //"cursor.newLine_noIncrement.bash", or
        //"cursor.newLine_Increment.bash"
        scriptName = "cursor.sameLine_noIncrement.bash";
        init();
    }
    fileHandler(String scriptName) {
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

class AnsiCursor extends Ansi {
    HashMap<String, int[]> cursorPositions; 
    fileHandler filehandler;
    public AnsiCursor () {
        cursorPositions = new HashMap<String, int[]>();
        filehandler = new fileHandler();
    }

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
    public AnsiCursor clearScreen() { return write("J"); }
    public AnsiCursor clearScreen_endOfScreen()  { return write("0J"); }
    public AnsiCursor clearScreen_begOfScreen()  { return write("1J"); }
    public AnsiCursor clearScreen_entireScreen() { return write("2J"); }

    //Clear Line
    public AnsiCursor clearLine ()           { return write("K"); }
    public AnsiCursor clearLine_endOfLine()  { return write("0K"); }
    public AnsiCursor clearLine_begOfLine()  { return write("1K"); }
    public AnsiCursor clearLine_entireLine() { return write("2K"); }


    //TODO: Figure out the correct arguments for these
    //Visuals
    public AnsiCursor slow_blink () { return write("5;"); }
    public AnsiCursor blink_off () { return write("25;"); }
    public AnsiCursor rapid_blink () { return write("6;"); }


    public AnsiCursor print(String s) { System.out.print(s); return this; }
    public AnsiCursor println(String s) { System.out.println(s); return this; }
}


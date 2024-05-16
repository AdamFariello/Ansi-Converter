package java_oop;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

class AnsiCursor extends Ansi {
    final String command = "bash";
    
    //final String scriptName = "cursor.bash";
    final String scriptName = "cursor.sameLine_noIncrement.bash";
    //final String scriptName = "cursor.newLine_noIncrement.bash";
    //final String scriptName = "cursor.newLine_Increment.bash";

    final String outputFileName = "temp.txt";

    //TODO: Ugly solution, include this with OSFileSymbol
    //      to have a more coherent getting of file stuff
    final String currDir = "java_oop";


    //TODO: Replace with file data type, and get location using getabsolutepath
    String script, outputFile;
    static HashMap<String, int[]> cursorPositions; 
    public AnsiCursor () {
        cursorPositions = new HashMap<String, int[]>();

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
    
    
    //Getting cursor position
    //TODO: Test these functions
    //This solutions seems to only get the bottom of the terminal screen.
    public AnsiCursor storeCurrentCursorPosition(String key, String a) { 
        //Using this doesn't properly give the value back.
        //return write("6N"); 

        //Output the cursor position to a file, and the read it back
        System.out.print(a);
        runScript(); 
        cursorPositions.put(key, readOutputFile());
        return this;
    }
    private void runScript() {
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
    private int[] readOutputFile () {
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

    //TODO: Execute the ansi string by disabline tty and other stuff script did
    public void disableTerminalCommands() {
        //TODO
        //Maybe just execute functions in bash script for stuff like disable 
        //Text output

        //pb.redirectInput(ProcessBuilder.Redirect.from(ttyDev));

        try {
            //String [] args = {"/bin/stty", "-icanon", "-echo", "Joe"};
            String [] args = {"/bin/stty", "-icanon", "-echo"};
            ProcessBuilder pb = new ProcessBuilder(args);
            pb.redirectInput(ProcessBuilder.Redirect.from(new File("/dev/tty")));
            Process process = pb.start();


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            
            System.setOut(ps);
            System.out.println(ESCAPE + "[6n");
            System.out.flush();
            System.setOut(old);

            System.out.println("Here: " + baos.toString());

            String [] args2 = {"/bin/stty", "icanon", "echo"};
            pb = new ProcessBuilder(args2);
            pb.redirectInput(ProcessBuilder.Redirect.from(new File("/dev/tty")));
            process = pb.start();

            System.out.println("Or here: " + baos.toString());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    private void writeAnsiCommand() {
        //TODO
        
    }
    private void readAnsicommand() {
        //TODO
        InputStream input = new FileInputStream(FileDescriptor.in);

    }
    private void reEnableTerminalCommands() {
        //TODO
        ProcessBuilder pb = new ProcessBuilder(new String[] {"/bin/stty", "icanon", "echo", "Joe"});
        pb.redirectInput(Redirect.from(new File("/dev/tty")));

        Process process = pb.start();
    }
    public String getCurrentCursorPosition() {
        //TODO

        return null;
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
        String homeKey = "Home"; //First one on q
        String baseKey = "Upper";

        //First Home location saved
        //ansi.storeCurrentCursorPosition(homeKey).print("Mom");
        ansi.storeCurrentCursorPosition(homeKey, "Mom");
        

        //Setup second base 
        ansi.up(5).right(9);
        //ansi.print("Q").left(1); //Q for second home, 1 left to off balance it
        //ansi.storeCurrentCursorPosition(baseKey);
        ansi.storeCurrentCursorPosition(baseKey, "Q");
        //System.out.println("Key: " + cursorPositions.get(baseKey));

        //Print upper values
        ansi.left(10).print("L").left(1).print("Z");
        ansi.getCursorPosition(baseKey).print("P");

        //Print lower values
        ansi.getCursorPosition(homeKey).print("Dad");
        
        //ansi.getCursorPosition(baseKey).right(7).print("OOOOOOOOO");
        
        /* 
        //Return directly to home
        ansi.getCursorPosition(homeKey).left(7).print("ZZZZZZZZZZ");
        ansi.getCursorPosition(homeKey).right(7).print("PPPPPPPPPP");
        */

        //For Terminal formatting
        System.out.println();
        int [] arr = cursorPositions.get(baseKey); 
        ansi.toLineToColumn_startOfLine(arr[0], arr[1]).print("B");
        System.out.printf(
            "Hashmap contents: \n[%d,%d] \n", arr[0],arr[1]
        );
        //Subtract 8?
        ansi.toLineToColumn_startOfLine(arr[0], arr[1]).print("G");
    }

}
package java_oop;

import static java_oop.AnsiCursor.OSFileSymbol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnsiCursorScreen extends AnsiCursor {
    final String command = "bash";
    //"cursor.bash", "cursor.newLine_noIncrement.bash", "cursor.newLine_Increment.bash"
    final String scriptName = "cursor.sameLine_noIncrement.bash";
    final String outputFileName = "temp.txt";
    final String currDir = "java_oop";

    //TODO: Replace with file data type, and get location using getabsolutepath
    String script, outputFile;
    
    public AnsiCursorScreen () {
        //Make sure cursorPositons is inherented
        super();

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
    }    //TODO: Execute the ansi string by disabline tty and other stuff script did


}

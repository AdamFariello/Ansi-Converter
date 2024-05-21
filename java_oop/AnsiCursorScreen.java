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

package ansiUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScriptHandler {
    final String command = "bash";
    final String currDir = "ansiUtil";

    final String scriptName = "script.bash";
    final String scriptLocation = OSproperties.createPathToProjectFile(
                                    new String[] {currDir}, 
                                    scriptName
                                );


    final String outputFileName = "temp.txt";
    final String outputFileLocation = OSproperties.createPathToProjectFile(
                                        new String[] {currDir}, 
                                        outputFileName
                                    );


    protected void runScript() {
        //Run the process first; send the screen cords to a file
        try { 
            List<String> args = new ArrayList<String>();
            args.add(command); 
            args.add(scriptLocation);
            args.add(outputFileLocation);

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
            File file = new File(outputFileLocation);
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

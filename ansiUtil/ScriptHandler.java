package ansiUtil;

import java.util.ArrayList;
import java.util.List;

public class ScriptHandler {
    final static String command = "bash";
    final static String currDir = "ansiUtil";
    
    final static String scriptName = "script.bash";
    final static String scriptLocation = OSproperties.createPathToProjectFile(
        new String[] {currDir}, 
        scriptName
    );

    final static String outputFileName = "temp.txt";
    final static String outputFileLocation = OSproperties.createPathToProjectFile(
        new String[] {currDir}, 
        outputFileName
    );



    public static void runScript() {
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
    public static int[] readOutputFile () {
        //Whether the file exists is checked here
        String line = FileHandler.readSingleLineFile(outputFileLocation);
        if (line != null) {
            FileHandler.deleteFile(outputFileLocation);
            
            String [] arr = line.split(" ");
            int row = Integer.parseInt(arr[0]);
            int col = Integer.parseInt(arr[1]);
            return new int [] {row, col};
        } else {
            return null;
        } 
    }
}

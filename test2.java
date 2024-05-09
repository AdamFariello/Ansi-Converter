import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class test2 {
    public static void main (String args [] ) { 
        try { 
            //ProcessBuilder pb = new ProcessBuilder("sh", "-c", "java -cp test.jar test"); 
            ProcessBuilder pb = new ProcessBuilder(); 
            
            List<String> builderList = new ArrayList<>();
            builderList.add("sh"); 
            builderList.add("-c"); 
            builderList.add("java -cp test.jar test"); 

            pb.command(builderList);
            Process process = pb.start(); 
  
            BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(process.getInputStream())
                                    ); 
  
            String line; 
            while ((line = reader.readLine()) != null) { 
                System.out.println(line);
            } 
  
            int exitVal = process.waitFor(); 
            if (exitVal == 0) { 
                System.out.println("\nExited with error code : " + exitVal); 
            } 
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }
}

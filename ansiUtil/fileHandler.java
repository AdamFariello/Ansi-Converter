package ansiUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class fileHandler {
    public static String readSingleLineFile(String fileNameAndLocation) {
        String line = null;

        FileReader fr = null;
        BufferedReader br = null;
        try {
            File file = new File(fileNameAndLocation);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            line = br.readLine();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return line;
    }

    public static boolean createFile(String fileNameAndLocation) { 
        try {
            File file = new File("filename.txt");
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteFile(String fileNameAndLocation) {
        File file = new File(fileNameAndLocation);
        return file.delete();
    }
}

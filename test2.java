import java.io.BufferedReader;
import java.io.Console;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test2 {
    final static String ESC = "\u001B";
    final static String cursorPosition = ESC + "[6n";

    static String fileName = "outputFile.txt";
    static String bashFile = "cursor2.bash";
    static String bashArgs = "bash";


    public static void solution1 () {
        String fileName = "solution1.txt";

        InputStream input = null;
        try {
            input = new FileInputStream(FileDescriptor.in);
            byte [] arr = new byte[100];

            //Where the system print should be
            System.out.println(cursorPosition);
            input.read(arr);


            String data = new String(arr);
            writeToFile(fileName, data);
            readFile(fileName);
            System.out.println("Successful run");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
    }
    public static void solution2 () {
    }
    public static void solution3 () {
        String fileName = "solution3.txt";

        InputStream input = null;
        try { 
            input = new FileInputStream(FileDescriptor.in);
            byte[] bytes = new byte[10];
            DataInputStream dataInputStream = new DataInputStream(input);

            System.out.println(cursorPosition);
            dataInputStream.readFully(bytes);
            
            writeToFile(fileName, dataInputStream.toString());
            readFile(fileName);
            System.out.println("Successful run");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
    }
    public static void solution4 () {
        String fileName = "solution4.txt";
        try { 
            Console console = System.console();
            System.out.println(cursorPosition);
            System.out.println("?");
            String s = console.readLine();    

            writeToFile(fileName, console.toString());
            readFile(fileName);
            System.out.println("Successful run");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void solution5 () {
        String fileName = "solution5.txt";
        InputStream input = new FileInputStream(FileDescriptor.in);
        InputStreamReader isr = new InputStreamReader(input);

        try {
            char c;
            String s = "";
            System.out.println(cursorPosition);
            do {
                c = (char) (isr.read() + '0');
                s += c;
            } while ( c != 'R');

            writeToFile(fileName, s);
            readFile(fileName);
            System.out.println("Successful run");

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void solution6 () {
        String fileName = "solution6.txt";

        try {
            // Execute the command to print cursor position
            String [] arr = {"/bin/sh", "-c", "echo -en '\\033[6n'"};
            Process process = Runtime.getRuntime().exec(arr);
            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.readLine();

            writeToFile(fileName, output);
            readFile(fileName);
        } catch (Exception e) {
            e.getStackTrace();
        }    
    }
    public static void solution7 () { 
        String fileName = "solution7.txt";

        System.out.println(cursorPosition);
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));   

        try {
            writeToFile(fileName, obj.readLine());
            readFile(fileName);
        } catch (Exception e) {
            e.getStackTrace();
        }           
    }
    public static void solution8() {
        try {
            byte[] bytes = cursorPosition.getBytes("UTF-8");
            String s = new String(bytes, "UTF-8");
            
            writeToFile(fileName, s);
            readFile(fileName);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void solution9() {
        //Run this program using jar
        System.out.print(cursorPosition);
    }
    public static void solution10() {
        Scanner in = new Scanner(cursorPosition).useDelimiter("[^0-9]+");
        int i = in.nextInt();
        int j = in.nextInt();
        in.close();
        System.out.printf("%d $d", i, j) ;
    }
    public static String solution11() {
        String result = "";
        try {
            //Maybe it was println?
            System.out.print(cursorPosition);

            //result = "";
            int character;

            do {
                character = System.in.read();
                if (character == 27) {
                    result += "^";
                } else {
                    result += (char) character;
                }
            } while (character != 82); //'R'
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            //System.out.println(result);
            return result;
        }

        

        /*
        Pattern pattern = Pattern.compile("\\^\\[(\\d+);(\\d+)R");
        Matcher matcher = pattern.matcher(result);
        if (matcher.maches()) {
            System
        }
        */
    }
    public static void solution12()  {
        try { 
            List<String> args = new ArrayList<String>();
            args.add("bash"); 
            //args.add("cursor3.bash"); 
            args.add("cursor.bash");

            ProcessBuilder pb = new ProcessBuilder(args);
            pb.inheritIO();
            Process process = pb.start();

            
            BufferedReader stdInput = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            ); 

            writeToFile(fileName, stdInput.readLine());
            readFile(fileName);
            
            //String s = stdInput.readLine()
            //String [] arr = s.split(" ");
            //System.out.println(arr[0].charAt(0));
        } catch (Exception e) {
            e.getStackTrace();
        } 
    }
    public static void solution13()  {
        try { 
            List<String> args = new ArrayList<String>();
            //args.add("python3"); 
            //args.add("cursor.py"); 
            args.add("bash");
            args.add("cursor.bash");

            ProcessBuilder pb = new ProcessBuilder(args);
            pb.inheritIO();
            Process process = pb.start();

            
            BufferedReader stdInput = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            ); 

            //String s = stdInput.readLine();
            //String [] arr = s.split(" ");
            //System.out.printf("Row %s Col %s \n", arr[0], arr[1]);
            //System.out.println("The command appears as such: " + s);
            writeToFile(fileName, stdInput.readLine());
            readFile(fileName);
        } catch (Exception e) {
            e.getStackTrace();
        } 
    }
    public static void solution14() {
        try { 
            InputStream input = new FileInputStream(FileDescriptor.in);
            
            int capacity = cursorPosition.length() + 1;
            ByteBuffer buffer = ByteBuffer.allocate(capacity);

            Runtime.getRuntime().exec("python3 cursor.py");
            
            input.close();
            //System.out.println("Test:" + buffer.toString());

            writeToFile(fileName, buffer.toString());
            readFile(fileName);
        } catch (Exception e) {
            e.getStackTrace();
        } 

    }
    public static void solution15()  {
        try{
            Console console = System.console();
            Runtime.getRuntime().exec("python3 cursor.py");
            System.out.println("?");
            String s = console.readLine();

            //System.out.println("test:" + s);
            writeToFile(fileName, s);
            readFile(fileName);
        } catch (Exception e) {
            e.getStackTrace();
        } 
    }

    //TODO: Research terminal character vs line mode
    //Important for when you want user to insert characters w/o pressing "enter" over and over
    //TODO: Control terminal size
    //Both TODOs seem like stuff to do after this program is done.
    //the program has already over stayed its welcome with over extending to all of ansi.
    public static void solution16() {
        //Run the process first; send the screen cords to a file
        try { 
            List<String> args = new ArrayList<String>();
            args.add("bash"); 
            args.add("cursor.bash"); 

            ProcessBuilder pb = new ProcessBuilder(args);
            pb.inheritIO();
            pb.start();
        } catch (Exception e) {
            System.out.println("Process could not be run");
            e.getStackTrace();
        } 


        //Output text into file and read it back.
        Scanner scanner = null;
        try {
            File file = new File("temp.txt");
            scanner = new Scanner(file);

            String data = null;
            while (scanner.hasNextLine()){
                data = scanner.nextLine();
                System.out.println(data);
            }
            System.out.println("Printed after file write");

            String [] arr = data.split(",");
            int row = Integer.parseInt(arr[0]);
            int col = Integer.parseInt(arr[1]);
            System.out.printf("Row: %d \nCol: %d \n", row + 1, col - 1);


        } catch (FileNotFoundException e) {
            //TODO: Check if an exception can trigger more than 1 catchs (Don't remember)
            System.out.println("File not found");
            e.getStackTrace();
        } finally {
            if (scanner != null) {
                try {
                    scanner.close();
                } catch (Exception e) {
                    // This is unrecoverable. Just report it and move on
                    e.printStackTrace();
                }
            }
        }
    }
    public static void solution17() {
        //SUCCESS

        //Run the process first; send the screen cords to a file
        try { 
            List<String> args = new ArrayList<String>();
            args.add("bash"); 
            args.add("cursor.bash"); 

            ProcessBuilder pb = new ProcessBuilder(args);
            pb.inheritIO();
            pb.start();
        } catch (Exception e) {
            System.out.println("Process could not be run");
            e.getStackTrace();
        } 

        FileReader fr = null;
        try {
            File file = new File("temp.txt");
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            System.out.println(line);
            System.out.println("Printed after line");

            String [] arr = line.split(",");
            int row = Integer.parseInt(arr[0]);
            int col = Integer.parseInt(arr[1]);
            System.out.printf("Rowing: %d \nColumn: %d \n", row, col);
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
    }
    public static void solution18() {
        String fileName = "write.txt";

        //Write to File
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(cursorPosition);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    // This is unrecoverable. Just report it and move on
                    e.printStackTrace();
                }
            }
        }


        //Read file 
        FileReader fr = null;
        try {
            File file = new File(fileName);
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            System.out.println(line); //Empty line
            System.out.println("Printed after line");

            /*
            String [] arr = line.split(",");
            int row = Integer.parseInt(arr[0]);
            int col = Integer.parseInt(arr[1]);
            System.out.printf("Rowing: %d \nColumn: %d \n", row, col);
            */
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
    }
    public static void solution19() {
        //Works
        String fileName = "write.txt";

        try {
            // Execute the command to print cursor position
            String [] arr = {"/bin/sh", "-c", "echo -en '\\033[6n'"};
            Process process = Runtime.getRuntime().exec(arr);

            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.readLine();


            //Write the file
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(fileName);
                fileWriter.write(output);
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            } finally {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        // This is unrecoverable. Just report it and move on
                        e.printStackTrace();
                    }
                }
            }


            //Read the file
            FileReader fr = null;
            try {
                File file = new File("temp.txt");
                fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String line = br.readLine();
                System.out.println(line);
                System.out.println("Printed after line");
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
        } catch (Exception e) {
            e.getStackTrace();
        }    
    }
    public static void solution20() {
        String fileName = "solution20.txt";

        System.out.println(cursorPosition);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
        readFile(fileName);
    }


    public static void runScript () {
        //Run the process first; send the screen cords to a file
        try { 
            List<String> args = new ArrayList<String>();
            args.add(bashArgs); 
            args.add(bashFile); 

            ProcessBuilder pb = new ProcessBuilder(args);
            pb.inheritIO();
            pb.start();
        } catch (Exception e) {
            System.out.println("Process could not be run");
            e.getStackTrace();
        } 
    }
    public static void writeToFile (String fileName, String input) {
        //Write to File
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(input);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    // This is unrecoverable. Just report it and move on
                    e.printStackTrace();
                }
            }
        }
    }
    public static void readFile (String fileName) {
        //Read file 
        FileReader fr = null;
        try {
            File file = new File(fileName);
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            System.out.println(line); //Empty line
            System.out.println("Printed after line");
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
    }

    public static void main (String args[]) {
        //test();
        //solution16();

        runScript();
        readFile(fileName);
    }    
}

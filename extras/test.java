package extras;

import java.util.*;
import java.io.*;
import java.nio.*;


public class test {
    final static String ESC = "\u001B";
    final static String cursorPosition = ESC + "[6n";
    static Console console = System.console();


    public static void solution1 () {
        //Idea 1: Redirect the output of system.out using InputStream
        //        and tap into the FileDecsriptor.in
        //
        //Problem is that it requires enter key for input since it's waiting
        //for a "-1" to understand that it's the end of the message
        try {
            InputStream input = new FileInputStream(FileDescriptor.in);
            byte [] arr = new byte[100];

            //Where the system print should be
            System.out.println(cursorPosition);
            //console.readLine(cursorPosition);
            input.read(arr);

            String data = new String(arr);
            input.close();

            System.out.println(data);

            //System.setOut(null);
            System.out.println("Reaches here");

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void solution2 () {
        //Tried using .nio buffer
        try {
            InputStream input = new FileInputStream(FileDescriptor.in);
            
            int capacity = cursorPosition.length() + 1;
            ByteBuffer buffer = ByteBuffer.allocate(capacity);


            System.out.println(cursorPosition);  
            //input.read(buffer);          

            input.close();
            System.out.println("Test:" + buffer.toString());

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void solution3 () {
        try { 
            InputStream input = new FileInputStream(FileDescriptor.in);
            byte[] bytes = new byte[10];
            DataInputStream dataInputStream = new DataInputStream(input);

            System.out.println(cursorPosition);
            dataInputStream.readFully(bytes);

            System.out.println("Test:" + dataInputStream.toString());
            
            input.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void solution4 () {
        try { 
            Console console = System.console();
            System.out.println(cursorPosition);
            System.out.println("?");
            String s = console.readLine();
            System.out.println("test:" + s);            
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void solution5 () {
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

            System.out.println("The string: " + s);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                isr.close();
            } catch (Exception e) {

            }
            
        }
    }

    public static void solution6 () {
        try {
            // Execute the command to print cursor position
            Process process = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "echo -en '\\033[6n'"});
            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.readLine();

            // Parse the output to extract cursor position
            String[] parts = output.split("\\[|;");
            int row = Integer.parseInt(parts[1]);
            int column = Integer.parseInt(parts[2]);

            // Print the cursor position
            System.out.println("Cursor position - Row: " + row + ", Column: " + column);
        } catch (Exception e) {
            e.getStackTrace();
        }    
    }

    public static void solution7 () { 
        System.out.println(cursorPosition);

        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));   
        String str = null;

        try {
            str = obj.readLine();
            System.out.println("test:" +str);
        } catch (Exception e) {
            e.getStackTrace();
        }           
    }

    public static void solution8() {
        try {
            byte[] bytes = cursorPosition.getBytes("UTF-8");
            String s = new String(bytes, "UTF-8");
            System.out.println("Test:" + s);
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
        } 

        return result;

        

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
            args.add("cursor3.bash"); 

            ProcessBuilder pb = new ProcessBuilder(args);
            pb.inheritIO();
            Process process = pb.start();

            
            BufferedReader stdInput = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            ); 

            /*
            //System.out.println("Outputs: ");
            String line; 
            String row = null;
            String col = null;
            while ((line = stdInput.readLine()) != null) { 
                //System.out.println(line);
                String [] arr = line.split(" ");
                row = arr[0];
                col = arr[1];
            } 
            //System.out.println("\nIs it over");
            System.out.printf("Row: %s Col: %s", row, col);
            */

            String s = stdInput.readLine();
            String [] arr = s.split(" ");
            System.out.println(arr[0].charAt(0));
            //System.out.printf("Row %s Col %s \n", arr[0], arr[1]);

            /*
            String [] arr = stdInput.readLine().split(" ");
            int row = Integer.parseInt(arr[0]);
            int col = Integer.parseInt(arr[1]);
            System.out.println("Test: " + (row + 30) + " " + col);
            */
        } catch (Exception e) {
            e.getStackTrace();
        } 
    }

    public static void solution13()  {
        try { 
            List<String> args = new ArrayList<String>();
            args.add("python3"); 
            args.add("cursor.py"); 

            ProcessBuilder pb = new ProcessBuilder(args);
            pb.inheritIO();
            Process process = pb.start();

            
            BufferedReader stdInput = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            ); 

            String s = stdInput.readLine();
            //String [] arr = s.split(" ");
            //System.out.printf("Row %s Col %s \n", arr[0], arr[1]);
            System.out.println("The command appears as such: " + s);
        } catch (Exception e) {
            e.getStackTrace();
        } 
    }

    @Deprecated
    public static void solution14() {
        try { 
            InputStream input = new FileInputStream(FileDescriptor.in);
            
            int capacity = cursorPosition.length() + 1;
            ByteBuffer buffer = ByteBuffer.allocate(capacity);


            Runtime.getRuntime().exec("python3 cursor.py");

            input.close();
            System.out.println("Test:" + buffer.toString());
        } catch (Exception e) {
            e.getStackTrace();
        } 

    }


    @Deprecated
    public static void solution15()  {
        try{
            Console console = System.console();
            Runtime.getRuntime().exec("python3 cursor.py");
            System.out.println("?");
            String s = console.readLine();
            System.out.println("test:" + s);
        } catch (Exception e) {
            e.getStackTrace();
        } 
    }


    //Important for when you want user to insert characters w/o pressing "enter" over and over
    //Both TODOs seem like stuff to do after this program is done.
    //the program has already over stayed its welcome with over extending to all of ansi.
    public static void solution16() {
        //Going to assume the name is temp.txt, and not give it a file name, for now
        
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
        BufferedReader br = null;
        try {
            File file = new File("temp.txt");
            fr = new FileReader(file);
            br = new BufferedReader(fr);

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
            // If an error is thrown, god is not on your side, (and exists)
            // Just report it and move on

            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    
                    e.printStackTrace();
                }
            }

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
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
            @SuppressWarnings("resource")
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
                @SuppressWarnings("resource")
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
        
        //Write to File
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(reader.readLine());
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
            @SuppressWarnings("resource")
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

    public static void testing() {
        try {
            // creating list of commands
            List<String> commands = new ArrayList<String>();
            commands.add("ls"); // command
            commands.add("-l"); // command
    
            // creating the process
            ProcessBuilder pb = new ProcessBuilder(commands);
    
            // starting the process
            pb.inheritIO();
            Process process = pb.start();
    
            // for reading the output from stream
            BufferedReader stdInput = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );
 

            String line; 
            while ((line = stdInput.readLine()) != null) { 
                System.out.println(line);
            } 

            /* 
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            */
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void test1 () {
        try {
            List<String> args = new ArrayList<String>();
            args.add("pwd"); 

            ProcessBuilder pb = new ProcessBuilder(args);
            System.out.print("Current directory: ");  
            pb.inheritIO();
            pb.start(); 


        } catch (Exception e) {
            e.getStackTrace();
        } 
    }
    public static void test2 () {
        try {
            List<String> args = new ArrayList<String>();
            args.add("bash");
            args.add("cursor2.bash"); 

            ProcessBuilder pb = new ProcessBuilder(args);  
            pb.inheritIO();
            pb.start(); 

        } catch (Exception e) {
            e.getStackTrace();
        } 
    }

    public static void main (String args[]) {
        //test();
        solution20();
    }    
}
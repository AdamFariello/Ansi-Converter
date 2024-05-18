package java_oop;

import java.nio.file.FileSystems;
import java.nio.file.Paths;


public class AnsiDemos {
    // TODO 1) Switch from abstract to regular class
    //      2) Implement Screen Mode in Ansi class
    //      3) 


    // Main class
    public static void main(String args[]) {
        /*
        AnsiTextDemo demoText = new AnsiTextDemo();
        AnsiCursorDemo demoCursor = new AnsiCursorDemo();
        
        System.out.println("Before");
        demoText.rainbow("#".repeat(60));
        System.out.println();

        System.out.println("After");
        demoText.rainbow("#".repeat(60));
        
        
        //demoCursor.toLineToColumn(25, 1);
        //demoCursor.print("test");

        //demoCursor.clearRow(5);
        demoCursor.saveAndRestore_multiple();
        */

        String ESC = "\u001B[0m";

        //All arguments seperated
        System.out.print("\u001B[34m");
        System.out.print("\u001B[4m"); 
        System.out.print("test"); 
        System.out.println(ESC);

        //One line, arguments merged
        System.out.println("\u001B[34;4m" + "test" + ESC);
    }
}




final class AnsiTextDemo extends AnsiText {
    private AnsiText ansi;
    public AnsiTextDemo () { 
        ansi = new AnsiText(); 
    }
    
    public void errorText(String s) {
        ansi.reset().setString("[ERROR] " + s).red().bold().println();
    }

    public void rainbow() {
        ansi.reset()
            .setString("Red").red()
            .storeStringln("Yellow").yellow()
            .storeStringln("Green").green()
            .storeStringln("Blue").blue()
            .storeStringln("Cyan").cyan()
            .storeStringln("Purple").purple()
            .storeStringln("White").white()
            .storeStringln("Black").black()
            .println(); //Text Formatting
        ;
    }
 
    public void rainbow(String s) {
        ansi.reset()
            .setString(s).red()
            .storeStringln(s).yellow()
            .storeStringln(s).green()
            .storeStringln(s).blue()
            .storeStringln(s).cyan()
            .storeStringln(s).purple()
            .storeStringln(s).white()
            .storeStringln(s).black()
            .println(); //Text Formatting
        ;
    }
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
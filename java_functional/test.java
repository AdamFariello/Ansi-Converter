package java_functional;

import java_functional.Ansi.Colors;


public class test {

    private static void testCursor () {
        Ansi.Cursor.UP.move(5);
        System.out.println("#");
        Ansi.Cursor.HOME.move(0);
        System.out.println("Location");
    }

    private static void advanced () {
        //AnsiGame game = new AnsiGame();
        
        Ansi.CursorPosition.SAVE.position();
        Ansi.Cursor.UP.move(3);
        System.out.print("Before");
        Ansi.CursorPosition.RESTORE.position();
        System.out.println("#################################3");
    }

    private static void rainbow () {
        String s = "#".repeat(30);
        for (Colors c : Colors.values()) {
            if (c.name() == "RGB"){
                break;
            } 

            c.text();
            System.out.println("test");
        }
        Ansi.resetText();
    }

    public static void main (String args []) {
        rainbow();
        advanced();
    }
}

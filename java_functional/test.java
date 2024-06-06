package java_functional;

import java.util.*;

public class test {

    private static void testCursor () {
        Ansi.Cursor.Move.UP.by(5);
        System.out.println("#");
        Ansi.Cursor.Move.HOME.set();
        System.out.println("Location");
    }

    private static void advanced () {
        //AnsiGame game = new AnsiGame();
        
        Ansi.Cursor.CursorStorageDEC.SAVE.position();
        Ansi.Cursor.Move.UP.by(3);
        System.out.print("Before");
        Ansi.Cursor.CursorStorageDEC.RESTORE.position();
        System.out.print("//".repeat(20));
        System.out.println();
    }

    private static void rainbow () {
        String s = "#".repeat(30);
        for (Ansi.Text.Colors c : Ansi.Text.Colors.values()) {
            if (c.name() == "RGB"){
                break;
            } 

            c.text();
            System.out.println(s);
        }
        Ansi.Text.reset();
    }

    private static void limitsOfMoveCursor() {
        //Cursor can be moved by General system writing
        
        Ansi.Cursor.Move.UP.by(3);
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter");
        System.out.println("\n\nTest\n");
        String trash = scanner.nextLine();

        Ansi.Cursor.Move.DOWN.by(3);
        System.out.println("Return");
    }

    public static void main (String args []) {
        rainbow();
        //advanced();
        limitsOfMoveCursor();
    }
}

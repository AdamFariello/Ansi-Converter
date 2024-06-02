package java_functional;

import java_functional.Ansi.Colors;

public class test {

    private static void testCursor () {
        Ansi.Cursor.UP.move(5);
        System.out.println("#");
        Ansi.Cursor.HOME.move(0);
        System.out.println("Location");
    }

    private static void testColors () {
        String s = "#".repeat(30);
        for (Colors c : Colors.values()) {
            if (c.name() == "RGB"){
                break;
            } 

            c.text();
            System.out.println("test");
        }
        Ansi.reset();
    }

    public static void main (String args []) {
        testColors();
        testCursor();
    }
}

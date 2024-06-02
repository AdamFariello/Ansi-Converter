package java_functional;

import java_functional.Ansi.Colors;

public class test {

    private static void testCursor () {
        String c = "#";
        for (int i = 0; i < 5; i++){
            System.out.println(c.repeat(20));
        }

        Ansi.Cursor.UP.move(5);
        System.out.println(c);
        Ansi.reset();
        Ansi.Cursor.SCROLLDOWN.move(0);
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
        //testCursor();
    }
}

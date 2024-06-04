package java_functional;

//import java_functional.Ansi.Colors;


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

    public static void main (String args []) {
        rainbow();
        advanced();
    }
}

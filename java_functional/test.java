package java_functional;

public class test {
    public static void main (String args []) {
        String c = "#";
        for (int i = 0; i < 5; i++){
            System.out.println(c.repeat(20));
        }

        Ansi.Cursor.UP.move(5);
        Ansi.Color.YELLOW.text();
        System.out.println(c);
        Ansi.reset();
        Ansi.Cursor.HOME.move(2);
        System.out.println("Location");
    }
}

package java_oop;

import java.util.concurrent.TimeUnit;

public class AnsiDemos {
    // TODO 1) Switch from abstract to regular class
    //      2) Implement Screen Mode in Ansi class
    //      3) 
    protected final static String ESCAPE = "\u001B";
    protected final static String CSI = ESCAPE + "[";
    protected final static String END = CSI + "0m";

    // Main class
    public static void main(String args[]) throws Exception{
        AnsiText text = new AnsiText();
        AnsiCursor cursor = new AnsiCursor();
        AnsiTextDemo demoText = new AnsiTextDemo();
        AnsiCursorDemo demoCursor = new AnsiCursorDemo();
        
        /* 
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


        /*
        AnsiTextDemo demoText = new AnsiTextDemo();
        demoText.rainbow();
        demoText.rainbow("#".repeat(60));
        
        AnsiCursorDemo demoCursor = new AnsiCursorDemo();
        demoCursor.scrollUp(4);
        //TimeUnit.SECONDS.sleep(1);
        System.out.println("test");

        System.out.println("\u001B[32m" + "test");
        */

        //System.out.println("Before");
        text.italic().bold().crossed_out().reverse().underline().print("ABCDEFGHIJK");
        text.resetln();

        text.overlined().print("example");
        text.resetln();

        //Color text
        text.color("red").print("test").resetln();
        text.color(233, true).print("test2").resetln();
        text.color(34,145,244,true).print("test3").resetln();


        /*
        System.out.println(CSI + "1;3;34" + "m" + "test" + END);

        System.out.print(CSI + "1" + "m");
        System.out.print(CSI + "3" + "m");
        System.out.print(CSI + "34" + "m");
        System.out.print("test");
        System.out.println(END);
        */
    }
}


final class AnsiTextDemo extends AnsiText {
    private AnsiText ansi;
    public AnsiTextDemo () { 
        ansi = new AnsiText(); 
    }
    
    public void errorText(String s) {
        ansi.color("red").bold().print("[ERROR] " + s).reset();
    }

    public void rainbow() {
        ansi.color("red").println("red")
            .color("yellow").println("yellow")
            .color("green").println("green")
            .color("blue").println("blue")
            .color("cyan").println("cyan")
            .color("purple").println("purple")
            .color("white").println("white")
            .color("black").println("black")
            .reset();
        ;
    }
 
    public void rainbow(String s) {
        ansi.color("red").println(s)
            .color("yellow").println(s)
            .color("green").println(s)
            .color("blue").println(s)
            .color("cyan").println(s)
            .color("purple").println(s)
            .color("white").println(s)
            .color("black").println(s)
            .reset();
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
}
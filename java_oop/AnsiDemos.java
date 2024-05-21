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
      
        //demoCursor.updatingPercentage();
        demoCursor.loadingBar(20);

        /*
        //System.out.println("Before");
        text.italic().bold().crossed_out().reverse().underline().print("ABCDEFGHIJK");
        text.resetln();

        text.overlined().print("example");
        text.resetln();

        //Color text
        text.color("red").print("test").resetln();
        text.color(233, true).print("test2").resetln();
        text.color(34,145,244,true).print("test3").resetln();
        */
    }
}


final class AnsiTextDemo {
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


final class AnsiCursorDemo {
    //TODO
    private AnsiCursor ansi; 
    public AnsiCursorDemo () {
        ansi = new AnsiCursor();
    }

    public void clearEntireRow (int i) {
        ansi.up(i).clearScreen_entire().down(i);
    }
    
    public void saveAndRestore() {
        ansi.saveCursorPosition().up(10).print("FIRST OVERWRITE");
        ansi.restoreCursorPosition().println("SECOND OVERWRITE");
    }

    public void updatingPercentage() {
        try {
            ansi.blink_off();
            
            System.out.print(0 + "%");
            for (int i = 0; i < 101; i++) {
                ansi.left(10);
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.print(i + "%");
            }
            System.out.println();
            System.out.println("Completed!");
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println();
        } finally {
            ansi.blink_on();
        }
    }

    public void loadingBar(int size) {
        String emptySpace = "-";
        String fillSpace = "=";

        String loadBar = String.format("[%s]", emptySpace.repeat(size));
        try {
            ansi.blink_off();
            
            System.out.print(loadBar);
            //offset bracket
            ansi.left(loadBar.length() - 1);

            //Offset brackets
            for (int i = 1; i < loadBar.length()-1; i++) {
                //TimeUnit.SECONDS.sleep(1);
                TimeUnit.MILLISECONDS.sleep(250);
                
                //This naturally moves the cursor right
                System.out.print(fillSpace);
            }
            
            //Don't want to overwrite last bracket with \n
            ansi.right(2);
            System.out.println();
            System.out.println("Completed!");
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println();
        } finally {
            ansi.blink_on();
        }       
    }
}
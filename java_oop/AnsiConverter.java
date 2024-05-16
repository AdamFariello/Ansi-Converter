package java_oop;


public class AnsiConverter {
    // TODO 1) Switch from abstract to regular class
    //      2) Implement Screen Mode in Ansi class
    //      3) 


    // Main class
    public static void main(String args[]) {
        AnsiTextDemo demoText = new AnsiTextDemo();
        AnsiCursorDemo demoCursor = new AnsiCursorDemo();
        
        System.out.println("Before");
        demoText.rainbow("#".repeat(60));
        System.out.println();

        System.out.println("After");
        demoText.rainbow("#".repeat(60));
        demoCursor.clearRow(5);

        //demoCursor.saveAndRestore_sco("_G");
        //demoCursor.saveAndRestore_dec("E");
        //demoCursor.saveAndRestoreTwoFunctions();
        demoCursor.saveAndRestore_multiple();
    }
}






/*
class AnsiSetMode extends Ansi {
    //TODO: Look if any of this is implementable:
    //  https://prirai.github.io/blogs/ansi-esc/#set-mode
    //TODO: See if bell can be actually renderable (\a or "\u0007")

    public AnsiSetMode () { super(); }

    public String restoreScreen() { return ESCAPE + "[?" + "47l"; }
    public String saveScreen() { return ESCAPE + "[?" + "47h"; }

    public String hideCursor() { return ESCAPE + "[?" + "25l"; }
    public String showCursor() { return ESCAPE + "[?" + "25h"; }

    //Use these to clear console screen. 
    public String enableAltBuffer() { return ESCAPE + "[?" + "1049h"; }
    public String disableAltBuffer() { return ESCAPE + "[?" + "1049l"; }
}
*/
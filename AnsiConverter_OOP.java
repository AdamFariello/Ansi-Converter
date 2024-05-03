import java.lang.annotation.Inherited;
import java.util.HashMap;

/*
        AnsiTextDemo ansi = new AnsiTextDemo();
        ansi.rainbow();

        //Test 1 (Manually)
        System.out.println("*".repeat(20));
        System.out.println("Before");
        System.out.print("\u001B[31;1m" + "abcdef" + "\u001B[0m");
        System.out.println("\u001B[32;1m" + "ghijk" + "\u001B[0m");
        System.out.print("\u001B[34;1m" + "lmnop" + "\u001B[0m");
        System.out.println("\u001B[33;1m" + "qrstu" + "\u001B[0m");
        System.out.println();

        System.out.println("After");
        System.out.print("\u001B[31;1m" + "abcdef" + "\u001B[0m");
        System.out.println("\u001B[32;1m" + "ghijk" + "\u001B[0m");

        System.out.print("\u001B[34;1m" + "" + "\u001B[0m");
        System.out.print("\u001B[H"); 
        System.out.println("\u001B[33;1m" + "Yellow Text" + "\u001B[0m");
        System.out.println("*".repeat(20));
 */

public class AnsiConverter_OOP {
    // TODO 1) Switch from abstract to regular class
    //      2) Implement Screen Mode in Ansi class
    //      3) 


    // Main class
    public static void main(String args[]) {
        //AnsiText text = new AnsiText();
        //System.out.println(text.toString());

        AnsiTextDemo demo = new AnsiTextDemo();
        demo.rainbow();
    }
}

abstract class Ansi {
    /*
    Octal: \033
    Unicode: \u001b
    Hexadecimal: \x1b
    Decimal: 27
    */
    protected final static String ESCAPE = "\u001B";
    protected final static String END = ESCAPE + "[" + "0m";

    protected String storeS = "";
    public Ansi () {}


    //TODO: Figure out if the string methods should be in the parent class
    //String Methods 

    public String toString() {
        if (storeS.length() > 0) {
            //Removes ';'
            return storeS.substring(0, storeS.length() - 1);
        } else {
            return "";
        }
    }
    public void print() { System.out.print(toString()); }
    public void println() { System.out.println(toString()); }
}



class AnsiText extends Ansi {
    //Output is already done, using home sends it to the first printed line
    //After overwriting how ever lines, the new code entry line is injected mid
    private String s = "";
    private String args = "";
    private String[] color = {"",""}; 


    public AnsiText () { }
    public AnsiText (String s) { this.s = s; }


    //String Methods
    public String getString () { return s; }
    public AnsiText setString (String s) { this.s = s; return this; }
    public AnsiText addToString (String s) { this.s += s; return this; }


    //Storing String Methods
    public AnsiText storeString() { 
        storeS += toString(); s = args = color[0] = color[1] = ""; return this; 
    }
    public AnsiText storeString(String s) { 
        storeString(); this.s = s; return this; 
    }
    public AnsiText storeStringln() { 
        storeS += toString() + "\n"; s = args = color[0] = color[1] = ""; return this; 
    }
    public AnsiText storeStringln(String s) { 
        storeStringln(); this.s = s; return this; 
    }


    //Inherented SoftReset Methods    
    public AnsiText reset() { s = args = storeS = ""; return this; }
    public AnsiText resetString() { s = ""; return this; }
    public AnsiText resetArgs() { args = ""; return this; }
    public AnsiText resetColor() { color[0] = color[1] = ""; return this;}
    public AnsiText resetStoredStr() { storeS = ""; return this; }


    //Color
    protected AnsiText black()  { color[1] = "0;"; return this; }
    protected AnsiText red()    { color[1] = "1;"; return this; }
    protected AnsiText green()  { color[1] = "2;"; return this; }
    protected AnsiText yellow() { color[1] = "3;"; return this; }
    protected AnsiText blue()   { color[1] = "4;"; return this; }
    protected AnsiText purple() { color[1] = "5;"; return this; }
    protected AnsiText cyan()   { color[1] = "6;"; return this; }
    protected AnsiText white()  { color[1] = "7;"; return this; }
    //8-bit Color
    public AnsiText eightBitColor(int r, int g, int b) { 
        color[1] = "8;2;" + r + ';' + g + ';' + b + ";"; return this;
    }
    //Effect
    protected AnsiText text()            { color[0] = "3"; return this; }
    protected AnsiText highlight()       { color[0] = "4"; return this; }
    protected AnsiText bright()          { color[0] = "9"; return this; }
    protected AnsiText brightHighlight() { color[0] = "10"; return this; }

 
    //Color manipulate
    public AnsiText reverse () { args += "7;"; return this; }
    public AnsiText inverse_off () { args += "27;"; return this; }
    public AnsiText conceal () { args += "8;"; return this; }
    public AnsiText reveal_off () { args += "28;"; return this; }
    public AnsiText crossed_out () { args += "9;"; return this; }
    public AnsiText not_crossed_out () { args += "29;"; return this; }


    //Text change
    public AnsiText bold() { args += "1;"; return this; }
    public AnsiText italic() { args += "3;"; return this; } 
    public AnsiText itallic_off () { args += "23;"; return this; }
    public AnsiText underline () { args += "4;"; return this; }
    public AnsiText underline_off () { args += "24;"; return this; }
    //Underlining text
    public AnsiText double_underline () { args += "21;"; return this; }
    public AnsiText framed () { args += "51;"; return this; }
    public AnsiText framed_off () { args += "54;"; return this; }
    public AnsiText encircled () { args += "52;"; return this; }
    public AnsiText overlined () { args += "53;"; return this; }
    public AnsiText overline_off () { args += "55;"; return this; }


    //toString must be called when calling the function
    @Override 
    public String toString() {
        /*
        System.out.printf(
            "String: %s \n Stored String: %s \n Args: %s \n color: %s \n"
            , s, storeS, args, color[0] + color[1]
        );
        */

        //Fix text color
        if (color[1] != "" && color[0] == "") {
            color[0] = "3";    
        }   

        String FORMAT = ESCAPE + "[" + color[0] + color[1] + args;
        
        //System.out.println(FORMAT.length());
        if (FORMAT.length() == (ESCAPE + "[").length()) {
            return "";
        } else {
            FORMAT = FORMAT.substring(0, FORMAT.length() - 1);
            return FORMAT + "m" + s + END; 
        }
    }
    @Override
    public void print() { System.out.print(storeS + toString()); }
    @Override
    public void println() { System.out.println(storeS + toString()); }
}
class AnsiTextDemo extends AnsiText {
    AnsiText ansi;
    public AnsiTextDemo () { ansi = new AnsiText(); }
    
    //TODO: Figure out if this is going to be a class that 
    //      allows/required strings in decorations
    //public AnsiExt (String s) { ansi = new Ansi(s); }
    
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
            .println();
        ;
    }
}


class AnsiCursor extends Ansi {
    public AnsiCursor () { super(); }


    //Reset
    public AnsiCursor toHome() { storeS += (ESCAPE + "[" + "H"); return this; }

    enum move {
        TOHOME('H'),
        UP('A'), DOWN('B'), RIGHT('C'), LEFT('D'),
        DOWNLINES('E'), UPLINES('F'), TOCOLUMN('G')
        ;
        private char id;
        move(char id) { this.id = id; }

        @Override
        public String toString () { return ESCAPE + '[' + id + ';'; }
        public String toString (int i) { return ESCAPE + '[' + i + id + ';'; }
    }
    public AnsiCursor up   (int i) { storeS += move.UP.toString(i); return this; }
    public AnsiCursor down (int i) { storeS += move.DOWN.toString(i); return this; }
    public AnsiCursor right(int i) { storeS += move.RIGHT.toString(i); return this; }
    public AnsiCursor left (int i) { storeS += move.LEFT.toString(i); return this; }
    public AnsiCursor downLines(int i) { storeS += move.DOWN.toString(i); return this; }
    public AnsiCursor upLines (int i) { storeS += move.UPLINES.toString(i); return this; }
    public AnsiCursor toColumn(int i) { storeS += move.TOCOLUMN.toString(i); return this; }

    /*
    //Cursor Position
    public AnsiCursor up   (int i) { args += i + "A;"; return this; }
    public AnsiCursor down (int i) { args += i + "B;"; return this; }
    public AnsiCursor right(int i) { args += i + "C;"; return this; }
    public AnsiCursor left (int i) { args += i + "D;"; return this; }
    public AnsiCursor downLines(int i) { args += i + "E;"; return this; }
    public AnsiCursor upLines (int i) { args += i + "F;"; return this; }
    public AnsiCursor toColumn(int i) { args += i + "G;"; return this; }
    */

    //TODO: test seperately, unsure if they work
    //Unsure
    public String getCursorPosition() { return storeS += ESCAPE + "6N;"; }
    public String saveCursorPosition_dec() { return storeS += ESCAPE + "7;"; }
    public String restoreCursorPosition_dec() { return storeS += ESCAPE + "8;"; }
    public String saveCursorPosition_sco() { return storeS += ESCAPE + "[" + "s"; }
    public String restoreCursorPosition_sco() { return storeS += ESCAPE + "[" + "u"; }


    //Clear Screen
    public AnsiCursor clearScreen() { storeS += ESCAPE + "J;"; return this; }
    public AnsiCursor clearScreen_endOfScreen()  { storeS += ESCAPE + "0J;"; return this; }
    public AnsiCursor clearScreen_begOfScreen()  { storeS += ESCAPE + "1J;"; return this; }
    public AnsiCursor clearScreen_entireScreen() { storeS += ESCAPE + "2J;"; return this; }

    //Clear Line
    public AnsiCursor clearLine ()           { storeS += ESCAPE + "K;"; return this; }
    public AnsiCursor clearLine_endOfLine()  { storeS += ESCAPE + "0K;"; return this; }
    public AnsiCursor clearLine_begOfLine()  { storeS += ESCAPE + "1K;"; return this; }
    public AnsiCursor clearLine_entireLine() { storeS += ESCAPE + "2K;"; return this; }

    /*TODO: Figure out the correct arguments for these
    //Visuals
    public AnsiCursor slow_blink () { args += "5;"; return this; }
    public AnsiCursor blink_off () { args += "25;"; return this; }
    public AnsiCursor rapid_blink () { args += "6;"; return this; }
    */

    /*
    @Override public String toString() {
        if (args.length() > 0) {
            //Removes the ';'
            String FORMAT = ESCAPE + "[" + "%s";
            String args_temp = args.substring(0, args.length() - 1);
            return String.format(FORMAT, args_temp) + END;   
        } else {
            return "";
        }
    }
    */
}


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
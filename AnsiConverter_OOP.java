import java.lang.annotation.Inherited;

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
        AnsiText text = new AnsiText();
        System.out.println(text.toString());

    }
}

enum Color {
    // 8 is used to unlock 8bit colors
    BLACK("0"), RED("1"), GREEN("2"),
    YELLOW("3"), BLUE("4"), PURPLE("5"),
    CYAN("6"), WHITE("7"), DEFAULT("9");
    private final String id;

    Color(String id) { this.id = id; }

    @Override
    public String toString () { return id;}
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
    
    public AnsiText () {}
    public AnsiText (String s) { this.s = s; }


    //String Methods
    public String getString () { return s; }
    public AnsiText setString (String s) { this.s = s; return this; }
    public AnsiText addToString (String s) { this.s += s; return this; }

    //Storing String Methods
    public AnsiText storeString() { storeS += toString(); s = args = ""; return this; }
    public AnsiText storeString(String s) { storeS += toString(); this.s = s; args = ""; return this; }
    public AnsiText storeStringln() { storeS += toString() + "\n"; s = args = ""; return this; }
    public AnsiText storeStringln(String s) { storeS += toString() + "\n"; this.s = s; args = ""; return this; }


    //Inherented SoftReset Methods    
    public AnsiText reset() { s = args = storeS = ""; return this; }
    public AnsiText resetString() { s = ""; return this; }
    public AnsiText resetArgs() { args = ""; return this; }
    public AnsiText resetStoredStr() { storeS = ""; return this; }


    public AnsiText Text red() { }

    //4-bit Color 
    public AnsiText text(Color c) { return combine("3", c.toString()); }
    public AnsiText highlight(Color c) { return combine("4", c.toString()); }
    public AnsiText textBright(Color c) { return combine("9", c.toString()); }
    public AnsiText highlightBright(Color c) { return combine("10", c.toString()); }
    //8-bit Color
    public AnsiText text(int r, int g, int b) { return combine("38;", "2;" + r + ';' + g + ';' + b); }
    public AnsiText highlight(int r, int g, int b) { return combine("48;", "2;" + r + ';' + g + ';' + b); }
    private AnsiText combine (String effect, String color) { args += effect + color + ';'; return this; }
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
        if (args.length() > 0) {
            String FORMAT = ESCAPE + "[" + "%s" + "m";
            String args_temp = args.substring(0, args.length() - 1);
            return String.format(FORMAT, args_temp) + s + END;   
        } else {
            //return s;
            return "tostring" + storeS;
        }
    }
}
class AnsiTextDemo extends AnsiText {
    AnsiText ansi;
    public AnsiTextDemo () { ansi = new AnsiText(); }
    
    //TODO: Figure out if this is going to be a class that 
    //      allows/required strings in decorations
    //public AnsiExt (String s) { ansi = new Ansi(s); }
    
    public void errorText(String s) {
        ansi.reset().setString("[ERROR] " + s).text(Color.RED).bold().println();
    }

    public void rainbow() {
        ansi.reset()
            .setString("Red").text(Color.RED)
            .storeStringln("Yellow").text(Color.YELLOW)
            .storeStringln("Green").text(Color.GREEN)
            .storeStringln("Blue").text(Color.BLUE)
            .storeStringln("Cyan").text(Color.CYAN)
            .storeStringln("Purple").text(Color.PURPLE)
            .storeStringln("White").text(Color.WHITE)
            .storeStringln("Black").text(Color.BLACK)
            .println()
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
import java.lang.annotation.Inherited;

public class AnsiConverter_OOP {
    // TODO 1) Switch from abstract to regular class
    //      2) Implement Screen Mode in Ansi class
    //      3) 


    // Main class
    public static void main(String args[]) {
        /*
        System.out.println("\u001B[31;1m" + "Red  text" + "\u001B[0m");  //Good
        System.out.println("\u001B[31;1;m" + "Red  text" + "\u001B[0m"); //Bad, no ';' before the m
        System.out.println(ansi.text(Color.RED).resetS("Blue text").text(Color.BLUE));
        */

        System.out.println(
            "\u001B[31;1m" + "Red text" + "\u001B[0m" +
            "\u001B[32;1D;1m" + "Green text" + "\u001B[0m"
        );
        
        System.out.println(
            "\u001B[31;1m" + "Red text"  +
            "\u001B[32;5D;1m" + "Green text" + "\u001B[0m"
        );

        System.out.println(
            "\u001B[31;1m" + "Red text"  +
            "\u001B[32;0k;1m" + "Green text" + "\u001B[0m"
        );

        System.out.print("\u001B[31;1m" + "Red text");
        System.out.print("\u001B[32;0k;1m" + "Green text" + "\u001B[0m");
        System.out.println();
        
        System.out.print("\u001B[31;1m" + "Red text");
        System.out.print("\u001B[2D"); 
        System.out.print("\u001B[32;1m" + "Green text");
        System.out.println("\u001B[0m");



        //Test 1 (Manually)
        System.out.println("*".repeat(20));
        System.out.println("Before");
        System.out.print("\u001B[31;1m" + "Red text" + "\u001B[0m");
        System.out.println("\u001B[32;1m" + "Green text" + "\u001B[0m");
        System.out.print("\u001B[34;1m" + "Blue text" + "\u001B[0m");
        System.out.println("\u001B[33;1m" + "Yellow text" + "\u001B[0m");
        System.out.println();
        
        System.out.println("After");
        System.out.print("\u001B[31;1m" + "Red text" + "\u001B[0m");
        System.out.println("\u001B[32;1m" + "Green text" + "\u001B[0m");

        System.out.print("\u001B[34;1m" + "Blue text" + "\u001B[0m");
        System.out.print("\u001B[1A" + "\u001B[2K"); 
        System.out.println("\u001B[33;1m" + "Yellow text" + "\u001B[0m");
        System.out.println("*".repeat(20));


        //Test 2 Auto
        System.out.println("\n" + "*".repeat(20));
        System.out.println("Before");
        AnsiText ansi = new AnsiText("This is red text");
        ansi.text(Color.RED).bold().toPrintln();

        System.out.println("After");
        AnsiCursor cursor = new AnsiCursor();
        //cursor.left(1000).toPrint();
        ansi.toPrint();
        ansi.storeText("Overwritten").text(Color.BLUE).underline().toPrintln();

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
    protected final String ESCAPE = "\u001B";

    protected final String FORMAT = ESCAPE + "[" + "%s" + "m";
    protected final String END = String.format(FORMAT, "0");

    protected String s, args;
    protected String storeS = "";

    public Ansi () {
        //Only made for clasees that extend this.
        //Other wise it gives this error:
        //  "Implicit super constructor Ansi() is undefined for default constructor. 
        //   Must define an explicit constructor"
        s = args = "";
    }
    public Ansi (String s) {
        this.s = s;
        args = "";
    }


    //Resetting text
    public void dumpStr() { storeS += toString(); s = ""; }
    public void resetStr() { s = ""; }
    public void resetArgs() { args = ""; }
    public void resetStoreStr() { storeS = ""; }
    public void reset() { s = args = storeS = ""; }
}

class AnsiText extends Ansi {
    public AnsiText () { super(); }
    public AnsiText (String s) { super(s); }

    //String updates
    public AnsiText setString (String s) { this.s = s; return this; }
    public String getString () { return s; }
    public AnsiText addString (String s) { this.s += s; return this; }


    //Color 
    public AnsiText text(Color c) { return combine("3", c.toString()); }
    public AnsiText highlight(Color c) { return combine("4", c.toString()); }
    public AnsiText textBright(Color c) { return combine("9", c.toString()); }
    public AnsiText highlightBright(Color c) { return combine("10", c.toString()); }

    public AnsiText text(int r, int g, int b) { 
        return combine("38;", "2;" + r + ';' + g + ';' + b); 
    }
    public AnsiText highlight(int r, int g, int b) { 
        return combine("48;", "2;" + r + ';' + g + ';' + b); 
    }
    private AnsiText combine (String effect, String color) {
        args += effect + color + ';';
        return this;
    }

    //Text change
    public AnsiText bold() { args += "1;"; return this; }
    public AnsiText italic() { args += "3;"; return this; } 
    public AnsiText itallic_off () { args += "23;"; return this; }
    public AnsiText underline () { args += "4;"; return this; }
    public AnsiText underline_off () { args += "24;"; return this; }
    
    //Color manipulate
    public AnsiText reverse () { args += "7;"; return this; }
    public AnsiText inverse_off () { args += "27;"; return this; }
    public AnsiText conceal () { args += "8;"; return this; }
    public AnsiText reveal_off () { args += "28;"; return this; }
    public AnsiText crossed_out () { args += "9;"; return this; }
    public AnsiText not_crossed_out () { args += "29;"; return this; }
    
    //Underlining text
    public AnsiText double_underline () { args += "21;"; return this; }
    public AnsiText framed () { args += "51;"; return this; }
    public AnsiText framed_off () { args += "54;"; return this; }
    public AnsiText encircled () { args += "52;"; return this; }
    public AnsiText overlined () { args += "53;"; return this; }
    public AnsiText overline_off () { args += "55;"; return this; }


    //toString must be called when calling the function
    public String toString() {
        if (args.length() > 0) {
            //Removes the ';'
            String args_temp = args.substring(0, args.length() - 1);
            return String.format(FORMAT, args_temp) + s + END;   
        } else {
            return s;
        }
    }
    public void toPrint() { System.out.print(storeS + toString()); }
    public void toPrintln() { System.out.println(storeS + toString()); }
}

class AnsiExt extends AnsiText {
    AnsiText ansi;
    public AnsiExt () { ansi = new AnsiText(); }
    
    //TODO: Figure out if this is going to be a class that 
    //      allows/required strings in decorations
    //public AnsiExt (String s) { ansi = new Ansi(s); }
    
    public Ansi errorText(String s) {
        return ansi.setString("[ERROR] " + s).text(Color.RED).bold();
    }
}


class AnsiCursor extends Ansi {
    public AnsiCursor () { super(); }
    
    public AnsiCursor toHome() { args += "H"; return this; }
    
    //Cursor Position
    public AnsiCursor up   (int i) { args += i + "A;"; return this; }
    public AnsiCursor down (int i) { args += i + "B;"; return this; }
    public AnsiCursor right(int i) { args += i + "C;"; return this; }
    public AnsiCursor left (int i) { args += i + "D;"; return this; }
    public AnsiCursor downLines(int i) { args += i + "E;"; return this; }
    public AnsiCursor upLines (int i) { args += i + "F;"; return this; }
    public AnsiCursor toColumn(int i) { args += i + "G;"; return this; }

    //TODO: test seperately, unsure if they work
    //Unsure
    public String getCursorPosition() { return ESCAPE + "6N;"; }
    public String saveCursorPosition_dec() { return ESCAPE + "7;"; }
    public String restoreCursorPosition_dec() { return ESCAPE + "8;"; }
    public String saveCursorPosition_sco() { return ESCAPE + "[" + "s"; }
    public String restoreCursorPosition_sco() { return ESCAPE + "[" + "u"; }


    //Clear Screen
    public AnsiCursor clearScreen() { args += "J"; return this; }
    public AnsiCursor clearScreen_endOfScreen()  { args += "0J;"; return this; }
    public AnsiCursor clearScreen_begOfScreen()  { args += "1J;"; return this; }
    public AnsiCursor clearScreen_entireScreen() { args += "2J;"; return this; }

    //Clear Line
    public AnsiCursor clearLine ()           { args += "K;"; return this; }
    public AnsiCursor clearLine_endOfLine()  { args += "0K;"; return this; }
    public AnsiCursor clearLine_begOfLine()  { args += "1K;"; return this; }
    public AnsiCursor clearLine_entireLine() { args += "2K;"; return this; }


    //Visuals
    public AnsiCursor slow_blink () { args += "5;"; return this; }
    public AnsiCursor blink_off () { args += "25;"; return this; }
    public AnsiCursor rapid_blink () { args += "6;"; return this; }


    //Reset the String
    public AnsiCursor resetS(String s) {
        return reset(" " + s);
    }
    public AnsiCursor reset(String s) {
        storeS += toString();
        this.s = s;
        args = "";
        return this;
    }
    public AnsiCursor reset() { s = args = ""; return this; }


    //String
    public String toString() {
        if (args.length() > 0) {
            //Removes the ';'
            String args_temp = args.substring(0, args.length() - 1);
            return String.format(FORMAT, args_temp) + s + END;   
        } else {
            return "";
        }
    }
    public void toPrint() { System.out.print(toString()); }
    public void toPrintln() { System.out.println(toString()); }
}
import java.lang.annotation.Inherited;

public class AnsiConverter_OOP {
    /*
     * public String reset() {
     * // Return reset ansi string
     * return String.format(FORMAT, 0);
     * }
     */

    // Main class
    public static void main(String args[]) {
        //System.out.println("\u001B[31;1m" + "Red  text" + "\u001B[0m");  //Good
        //System.out.println("\u001B[31;1;m" + "Red  text" + "\u001B[0m"); //Bad
        //System.out.println("\u001B[31;1m Red  text \u001B[0m \u001B[32;1m Green text \u001B[0m");

        AnsiText ansi = new AnsiText("This is red text");
        
        System.out.println(ansi.text(Color.RED).resetS("Blue text").text(Color.BLUE));
        System.out.println(ansi.text(Color.RED));
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
    protected final String ESCAPE = "\u001B";
    protected final String FORMAT = ESCAPE + "[" + "%s" + "m";
    protected final String END = String.format(FORMAT, "0");

    protected String s, args;
    protected String currS = "";

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
}

class AnsiText extends Ansi {
    public AnsiText () { super(); }
    public AnsiText (String s) { super(s); }

    //String updates
    public AnsiText setString (String s) { this.s = s; return this; }
    public String getString () { return s; }
    public AnsiText addString (String s) { this.s += s; return this; }
        
    //Reset the String
    public AnsiText resetS(String s) {
        return reset(" " + s);
    }
    public AnsiText reset(String s) {
        currS += toString();
        this.s = s;
        args = "";
        return this;
    }
    public AnsiText reset() {
        s = args = "";
        return this;
    }

    //Color 
    public AnsiText text(Color c) { return combine("3", c); }
    public AnsiText highlight(Color c) { return combine("4", c); }
    public AnsiText textBright(Color c) { return combine("9", c); }
    public AnsiText highlightBright(Color c) { return combine("10", c); }
    private AnsiText combine (String effect, Color c) {
        args += effect + c + ';';
        return this;
    }
 
    //Text change
    public AnsiText bold() { args += "1;"; return this; }
    public AnsiText italic() { args += "3;"; return this; } 
    public AnsiText itallic_off () { args += "23;"; return this; }
    public AnsiText underline () { args += "4;"; return this; }
    public AnsiText underline_off () { args += "24;"; return this; }

    //Cursor manupulation
    public AnsiText slow_blink () { args += "5;"; return this; }
    public AnsiText blink_off () { args += "25;"; return this; }
    public AnsiText rapid_blink () { args += "6;"; return this; }
    
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
            return currS + String.format(FORMAT, args_temp) + s + END;   
        } else {
            return currS + s;
        }
    }
    public void toPrint() { System.out.println(toString()); }
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
            /*
        AnsiExt ansi = new AnsiExt();
        //System.out.println(ansi.errorText("This functions is bad"));
        ansi.errorText("This functions is bad").toPrint();
        */    

        /*
        ESC[J	clears the screen
        ESC[0J	clears from cursor until end of screen
        ESC[1J	clears from cursor to beginning of screen
        ESC[2J	clears entire screen
        ESC[K	clears the current line
        ESC[0K	clears from cursor to end of line
        ESC[1K	clears from cursor to start of line
        ESC[2K	clears entire line 
        
        Clear Screen: \u001b[{n}J clears the screen
        n=0 clears from cursor until end of screen,
        n=1 clears from cursor to beginning of screen
        n=2 clears entire screen
        Clear Line: \u001b[{n}K clears the current line
        n=0 clears from cursor to end of line
        n=1 clears from cursor to start of line
        n=2 clears entire line
        */

        public AnsiCursor () { super(); }
        
        
}
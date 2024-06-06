package extras;

import java.util.ArrayList;



public class AnsiConverter_OOP {
    // Main class
    public static void main(String args[]) {
        /* 
        clearScreen() { storeS.add(ESCAPE + "[" + "J"); return this; }
        clearScreen_endOfScreen()  { storeS.add(ESCAPE + "[" + "0J"); return this; }
        clearScreen_begOfScreen()  { storeS.add(ESCAPE + "[" + "1J"); return this; }
        clearScreen_entireScreen() { storeS.add(ESCAPE + "[" + "2J"); return this; }

        //Clear Line
        clearLine ()           { storeS.add(ESCAPE + "[" + "K"); return this; }
        clearLine_endOfLine()  { storeS.add(ESCAPE + "[" + "0K"); return this; }
        clearLine_begOfLine()  { storeS.add(ESCAPE + "[" + "1K"); return this; }
        clearLine_entireLine() { storeS.add(ESCAPE + "[" + "2K"); return this; }

        */

        /* 
        AnsiTextDemo demo = new AnsiTextDemo();
        AnsiCursor cursor = new AnsiCursor();        
        
        System.out.println("========= Before =========");
        demo.rainbow();


        System.out.println("========= After =========");
        demo.rainbow();
        cursor.up(2).clearScreen().print();
        System.out.println("|||||||||||||");
 
        //Current position
        cursor.reset().down(2).print();
        //System.out.print("\u001B" + "[u");
        System.out.println("XXXXXXXXXXXXXXXXX");
        */
        
        /*
        //No matter what, the cursor returns to the same end position... 
        //There is only one save variable
        System.out.print("\u001B" + "[s");
        cursor.reset().up(5).print();
        demo.rainbow();
        System.out.print("\u001B" + "[u");
        System.out.print("ZZZZZZZZZZZZZZZZZ");

        cursor.reset().up(2).print();
        System.out.print("\u001B" + "[s");
        cursor.reset().down(1).print();
        System.out.print("VVVVVVVVVVVVVVVVV");
        System.out.print("\u001B" + "[u");
        System.out.print("YYYYYYYYYYYYYYYYY");

        System.out.print("\u001B" + "[u");
        System.out.print("OOOOOOOOOOOOOOOOO");
        */

        
        /* 
        //Positions is saved and automatically updated
        cursor.reset().up(5).print();
        System.out.print("\u001B" + "7");
        cursor.reset().up(2).print();
        System.out.println("QQQQQQQQQQQQQQQQQ");
        System.out.println("QQQQQQQQQQQQQQQQQ");
        System.out.println("QQQQQQQQQQQQQQQQQ");
        System.out.println("QQQQQQQQQQQQQQQQQ");
        System.out.println("QQQQQQQQQQQQQQQQQ");
        System.out.println("QQQQQQQQQQQQQQQQQ");
        System.out.println("QQQQQQQQQQQQQQQQQ");
        System.out.print("\u001B" + "[u");
        System.out.print("ZZZZZZZZZZZZZZZZZ");
        */


        /* 
        //Cusor location always stays the same
        cursor.reset().toLineToColumn(2, 4).print();
        System.out.println("QQQQQQQQQQQQQQQQQ");
        cursor.reset().toLineToColumn(2, 4).print();
        System.out.print("ZZZZZZZZZZZZZZZZZ");
        */


        //Putting console tag next line
        System.out.println();
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
    protected AnsiText brightText()      { color[0] = "9"; return this; }
    protected AnsiText brightHighlight() { color[0] = "10"; return this; }

 
    //Color manipulate
    public AnsiText reverse () { args += "7;"; return this; } //reverse swaps color of highlight and foreground
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
final class AnsiTextDemo extends AnsiText {
    AnsiText ansi;
    public AnsiTextDemo () { ansi = new AnsiText(); }
    

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
    ArrayList<String> storeS = new ArrayList<String>();

    public AnsiCursor () { }

    
    //Reset
    public AnsiCursor toHome() { storeS.add(ESCAPE + "[" + "H"); return this; }

    public AnsiCursor up   (int i) { storeS.add(ESCAPE + "[" + i + "A"); return this; }
    public AnsiCursor down (int i) { storeS.add(ESCAPE + "[" + i + "B"); return this; }
    public AnsiCursor right(int i) { storeS.add(ESCAPE + "[" + i + "C"); return this; }
    public AnsiCursor left (int i) { storeS.add(ESCAPE + "[" + i + "D"); return this; }

    public AnsiCursor downAndStart(int i) { storeS.add(ESCAPE + "[" + i + "E"); return this; }
    public AnsiCursor upAndStart (int i) { storeS.add(ESCAPE + "[" + i + "F"); return this; }
    public AnsiCursor toColumn(int i) { storeS.add(ESCAPE + "[" + i + "G"); return this; }
    public AnsiCursor toLineToColumn(int i, int j) { 
        storeS.add(ESCAPE + "[" + i + ";" + j + "f"); return this;
    }


    public AnsiCursor getCursorPosition() { storeS.add(ESCAPE + "6N"); return this; }
    public AnsiCursor saveCursorPosition_dec() { storeS.add(ESCAPE + "7"); return this; }
    public AnsiCursor restoreCursorPosition_dec() { storeS.add(ESCAPE + "8"); return this; }
    public AnsiCursor saveCursorPosition_sco() { storeS.add(ESCAPE + "[" + "s"); return this; }
    public AnsiCursor restoreCursorPosition_sco() { storeS.add(ESCAPE + "[" + "u"); return this; }



    //Clear Screen
    public AnsiCursor clearScreen() { storeS.add(ESCAPE + "[" + "J"); return this; }
    public AnsiCursor clearScreen_endOfScreen()  { storeS.add(ESCAPE + "[" + "0J"); return this; }
    public AnsiCursor clearScreen_begOfScreen()  { storeS.add(ESCAPE + "[" + "1J"); return this; }
    public AnsiCursor clearScreen_entireScreen() { storeS.add(ESCAPE + "[" + "2J"); return this; }

    //Clear Line
    public AnsiCursor clearLine ()           { storeS.add(ESCAPE + "[" + "K"); return this; }
    public AnsiCursor clearLine_endOfLine()  { storeS.add(ESCAPE + "[" + "0K"); return this; }
    public AnsiCursor clearLine_begOfLine()  { storeS.add(ESCAPE + "[" + "1K"); return this; }
    public AnsiCursor clearLine_entireLine() { storeS.add(ESCAPE + "[" + "2K"); return this; }


    /*
    //Visuals
    public AnsiCursor slow_blink () { args += "5;"; return this; }
    public AnsiCursor blink_off () { args += "25;"; return this; }
    public AnsiCursor rapid_blink () { args += "6;"; return this; }
    */

    public AnsiCursor reset() { storeS.clear(); return this; }

    @Override
    public void print() { 
        if (!storeS.isEmpty()) {
            for (int i = 0; i < storeS.size(); i++) {
                System.out.print(storeS.get(i));
            } 
        } else {
            System.out.print("");
        }
    }
    @Override
    public void println() { 
        if (!storeS.isEmpty()) {
            for (int i = 0; i < storeS.size(); i++) {
                System.out.println(storeS.get(i));
            } 
        } else {
            System.out.println("");
        }
    }
}
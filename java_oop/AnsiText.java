package java_oop;

import java.util.*;

class AnsiText extends Ansi {
    private String s;
    private StringBuffer storeS, args;
    private String[] color; 
    public AnsiText () { 
        s = "";
        storeS = new StringBuffer();
        args = new StringBuffer();
        color = new String[] {"",""};
    }
    public AnsiText (String s) { 
        super();
        this.s = s; 
    }


    //String Methods
    public String getString () { return s; }
    public AnsiText setString (String s) { this.s = s; return this; }


    //Storing String Methods
    private AnsiText storeStringDefault(String ln, String s) {
        storeS.append(toString() + ln);
        this.s = s;
        color[0] = color[1] = "";
        args = new StringBuffer(); 
        return this;
    }
    public AnsiText storeString() { return storeStringDefault("", ""); }
    public AnsiText storeString(String s) { return storeStringDefault("", s); }
    public AnsiText storeStringln() { return storeStringDefault("\n", ""); }
    public AnsiText storeStringln(String s) { return storeStringDefault("\n", s); }


    //Inherented SoftReset Methods    
    //public AnsiText reset() { s = storeS = color[0] = color[1] = ""; args = new StringBuffer(); return this; }
    public AnsiText reset() { resetString(); resetArgs(); resetColor(); resetStoredStr(); return this; }
    public AnsiText resetString() { s = ""; return this; }
    public AnsiText resetArgs() { args = new StringBuffer() ; return this; }
    public AnsiText resetColor() { color[0] = color[1] = ""; return this;}
    public AnsiText resetStoredStr() { storeS = new StringBuffer(); return this; }


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
    public AnsiText reverse () { args.append("7;"); return this; } //reverse swaps color of highlight and foreground
    public AnsiText inverse_off () { args.append("27;"); return this; }
    public AnsiText conceal () { args.append("8;"); return this; }
    public AnsiText reveal_off () { args.append("28;"); return this; }
    public AnsiText crossed_out () { args.append("9;"); return this; }
    public AnsiText not_crossed_out () { args.append("29;"); return this; }


    //Text change
    public AnsiText bold() { args.append("1;"); return this; }
    public AnsiText italic() { args.append("3;"); return this; } 
    public AnsiText itallic_off () { args.append("23;"); return this; }
    public AnsiText underline () { args.append("4;"); return this; }
    public AnsiText underline_off () { args.append("24;"); return this; }
    //Underlining text
    public AnsiText double_underline () { args.append("21;"); return this; }
    public AnsiText framed () { args.append("51;"); return this; }
    public AnsiText framed_off () { args.append("54;"); return this; }
    public AnsiText encircled () { args.append("52;"); return this; }
    public AnsiText overlined () { args.append("53;"); return this; }
    public AnsiText overline_off () { args.append("55;"); return this; }


    //toString must be called when calling the function
    @Override 
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (color[1] != "" && color[0] == "") {
            //Fix text color
            color[0] = "3";    
        }   
        sb.append(color[0] + color[1]);
        sb.append(args);


        if (sb.length() > 0 ) {
            /*
            String temp = String.format(
                            ESCAPE + "[" + "%s%s%s" + "m" + "%s" + END, 
                            color[0], color[1], sb.substring(0, (sb.length() - 1)), s
                          );
            System.out.println("temp string: " + temp);
            return temp;
            */

            return String.format(
                        ESCAPE + "[" + "%s" + "m" + "%s" + END, 
                        sb.substring(0, (sb.length() - 1)), s
                    );
        } else {
            //return storeS.toString();
            return "";
        }
    }
    @Override
    public void print() { System.out.print(storeS + toString()); }
    @Override
    public void println() { System.out.println(storeS + toString()); }
}



final class AnsiTextDemo extends AnsiText {
    private AnsiText ansi;
    public AnsiTextDemo () { 
        ansi = new AnsiText(); 
    }
    
    //TODO: Figure out if this is going to be a class that 
    //      allows/required strings in decorations
    //public AnsiExt (String s) { ansi = new Ansi(s); }
    
    public void errorText(String s) {
        ansi.reset();
        ansi.setString("[ERROR] " + s).red().bold().println();
    }

    public void rainbow() {
        ansi.reset();
        ansi.setString("Red").red()
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
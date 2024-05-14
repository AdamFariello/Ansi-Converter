package java_oop;

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
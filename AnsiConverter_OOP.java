class Ansi {
    private final static String FORMAT = "\u001B[" + "%s" + "m";
    private final String END = String.format(FORMAT, "0");
    
    private String s;
    private String args;

    public Ansi (String s) {
        this.s = s;
        args = "";
    }


    //Color 
    enum Color {
        // 8 is used to unlock 8bit colors
        BLACK("0"), RED("1"), GREEN("2"),
        YELLOW("3"), BLUE("4"), PURPLE("5"),
        CYAN("6"), WHITE("7"), DEFAULT("9");
        private final String id;

        Color(String id) { this.id = id; }

        /* 
        @Override
        public String toString () { return id;}
        */
    }
    public Ansi text(Color c) { return combine("3", c); }
    public Ansi highlight(Color c) { return combine("4", c); }
    public Ansi textBright(Color c) { return combine("9", c); }
    public Ansi highlightBright(Color c) { return combine("10", c); }
    private Ansi combine (String effect, Color c) {
        args += effect + c + ';';
        return this;
    }
 
    //Text change
    public Ansi bold() { args += "1;"; return this; }
    public Ansi italic() { args += "3;"; return this; } 
    public Ansi itallic_off () { args += "23;"; return this; }
    public Ansi underline () { args += "4;"; return this; }
    public Ansi underline_off () { args += "24;"; return this; }

    //Cursor manupulation
    public Ansi slow_blink () { args += "5;"; return this; }
    public Ansi blink_off () { args += "25;"; return this; }
    public Ansi rapid_blink () { args += "6;"; return this; }
    
    //Color manipulate
    public Ansi reverse () { args += "7;"; return this; }
    public Ansi inverse_off () { args += "27;"; return this; }
    public Ansi conceal () { args += "8;"; return this; }
    public Ansi reveal_off () { args += "28;"; return this; }
    public Ansi crossed_out () { args += "9;"; return this; }
    public Ansi not_crossed_out () { args += "29;"; return this; }
    
    //Underlining text
    public Ansi double_underline () { args += "21;"; return this; }
    public Ansi framed () { args += "51;"; return this; }
    public Ansi framed_off () { args += "54;"; return this; }
    public Ansi encircled () { args += "52;"; return this; }
    public Ansi overlined () { args += "53;"; return this; }
    public Ansi overline_off () { args += "55;"; return this; }

    //TODO: Check if this auto prints using this when calling the function(s)
    public String toString() {
        if (args.length() > 0) {
            //Remove the ';'
            String args_temp = args.substring(0, args.length() - 1);
            return String.format(FORMAT, args_temp) + s + END;   
        } else {
            return s;
        }
    }
}

public class AnsiConverter_OOP {
    /*
     * public String reset() {
     * // Return reset ansi string
     * return String.format(FORMAT, 0);
     * }
     */

    // Main class
    public static void main(String args[]) {
        Ansi ansi = new Ansi("This is red text");
        System.out.println(ansi);
    }
}
package java_oop;

import java.util.*;


enum Color {
    BLACK("0"), RED("1"), GREEN("2"), YELLOW("3"), BLUE("4"),
    PURPLE("5"), CYAN("6"), WHITE("7"),
    TEXT("3"), HIGHLIGHT("4"), BRIGHTTEXT("9"), BRIGHTHIGHLIGHT("10");

    String id;
    Color (String id) {
        this.id = id;
    }

    public String text() { return "3" + id; }
    public String highlight() { return "4"+ id; }
    public String brightText() { return "9" + text(); }
    public String brightHighlight() { return "10" + highlight(); }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return text();
    }
}


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

    public AnsiText write (String s) {
        System.out.print(ESCAPE + "[" + s);
        return this; 
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


    public AnsiText color(String color) {
        return color(color, false, false);
    }
    public AnsiText color (String color, Boolean isHighlight) {
        return color(color, true, false);
    }
    public AnsiText color (String color, Boolean isHighlight, Boolean isBright) {
        StringBuffer sb = new StringBuffer();

        if (isBright && isHighlight) {
            sb.append(Color.BRIGHTHIGHLIGHT);
        } else if (isBright) {
            sb.append(Color.BRIGHTTEXT);
        } else if (isHighlight) {
            sb.append(Color.HIGHLIGHT);
        } else {
            sb.append(Color.TEXT);
        }

        color = color.toLowerCase();
        switch (color) {
            case "black":
                sb.append(Color.BLACK);
                break;
            case "red":
                sb.append(Color.RED);
                break;
            case "green":
                sb.append(Color.GREEN);      
                break;
            case "yellow":
                sb.append(Color.YELLOW);     
                break;
            case "blue":
                sb.append(Color.BLUE);       
                break;
            case "purple":
                sb.append(Color.PURPLE);     
                break;
            case "cyan":
                sb.append(Color.CYAN);       
                break;
            case "white":
                sb.append(Color.WHITE);      
                break;
        }
        
        return write(sb.toString());
    }

 
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
    public void print() { System.out.print(storeS + toString()); }
    public void println() { System.out.println(storeS + toString()); }
}
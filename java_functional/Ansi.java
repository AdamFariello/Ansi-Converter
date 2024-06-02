package java_functional;

import java.util.HashMap;

class Ansi {
    //Octal: \033
    //Unicode: \u001b
    //Hexadecimal: \x1b
    //Decimal: 27
    final static String ESCAPE = "\u001B";
    final static String CSI = ESCAPE + "[";
    final static String END = CSI + "0m";

    static void reset () { System.out.print(END); }; //This resets arguments
    static void resetln() { System.out.println(END); }; //This resets arguments
    static void write (String s) { System.out.print(CSI + s + "m"); }
    static void print (String s) { System.out.print(s); }
    static void println (String s) { System.out.println(s); }


    //interface AnsiText {}


    class Color {

    }


    enum Color2 {
        BLACK("0"), RED("1"), GREEN("2"), YELLOW("3"), BLUE("4"),
        PURPLE("5"), CYAN("6"), WHITE("7");

        String id;
        Color2 (String id) {
            this.id = id;
        }
    
        //Required if you want Color.{color} to give back a color
        @Override
        public String toString() {
            text();
            return id;
        }

        public void text() { write("3" + id); }
        public void highlight() { write("4" + id); }
        public void brightText() { write("9" + id); }
        public void brightHighlight() { write("10" + id); }

        public void eightBitColor(int r, int g, int b) {

        } 

        /* 
        public static void color(int color) {  color(color, false); }
        public static void color(int color, boolean isHighlight) {
            if (isHighlight) {
                 write("48:5:" + color);
            } else {
                 write("38:5:" + color);
            }
        }
    
        //The values are based off the decimal values
        public static void color(int r, int g, int b) {  color(r, g, b, false); }
        public static void color(int r, int g, int b, boolean isHighlight) {
            //Using "println" after using the highlight will highlight all voidafter 
            if (isHighlight) {
                 write("48;2;" + r + ";" + g + ";" + b);
            } else {
                 write("38;2;" + r + ";" + g + ";" + b);
            }
        }
        */

    }

    enum Font {
        BOLD("1"), 
        ITALIC("3"), ITALLIC_OFF("23"), 
        
        UNDERLINE("4"), UNDERLINE_OFF("24"), 
        DOUBLE_UNDERLINE("21"),
        
        REVERSE("7"), INVERSE_OFF("27"), 
        CONCEAL("8"), REVEAL_OFF("28"), 
        CROSSED_OUT("9"), NOT_CROSSED_OUT("29"), 
    
        FRAMED("51"), FRAMED_OFF("54"), 
        ENCIRCLED("52"), 
        OVERLINED("53"), OVERLINE_OFF("55");
        String id;
        Font (String id) {
            this.id = id;
        }
    
        @Override
        public String toString() {
            write(id);
            return id;
        }
    }


    

    /* 
    class Text implements Ansi{ 
        //Inherented functions
        public void write (String s) {
            System.out.print(CSI + s + "m");
             
        }
        public void reset () {
            System.out.print(END);
            
        }
        public static void resetln () {
            System.out.println(END);
        }
    
    
        //TODO Figure out if I want to use enum solution or not, still unhappy with it...
        public static void color(String color) {
             color(color, false, false);
        }
        public static void color(String color, Boolean isHighlight) {
             color(color, true, false);
        }
        public static void color(String color, Boolean isHighlight, Boolean isBright) {
            StringBuffer sb = new StringBuffer();
    
            if (isBright && isHighlight) {
                sb.append(Color.BRIGHTHIGHLIGHT);
            } else if (isBright) {
                sb.append(Color.BRIGHTvoid);
            } else if (isHighlight) {
                sb.append(Color.HIGHLIGHT);
            } else {
                sb.append(Color.void);
            }

        }
       
        public static void color(int color) {  color(color, false); }
        public static void color(int color, boolean isHighlight) {
            if (isHighlight) {
                 write("48:5:" + color);
            } else {
                 write("38:5:" + color);
            }
        }
    
        //The values are based off the decimal values
        public static void color(int r, int g, int b) {  color(r, g, b, false); }
        public static void color(int r, int g, int b, boolean isHighlight) {
            //Using "println" after using the highlight will highlight all voidafter 
            if (isHighlight) {
                 write("48;2;" + r + ";" + g + ";" + b);
            } else {
                 write("38;2;" + r + ";" + g + ";" + b);
            }
        }
    }
    */
}
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

    
    enum Color {
        BLACK("0"), RED("1"), GREEN("2"), YELLOW("3"), BLUE("4"),
        PURPLE("5"), CYAN("6"), WHITE("7"),
        TEXT("3"), HIGHLIGHT("4"), BRIGHTTEXT("9"), BRIGHTHIGHLIGHT("10");
    
        String id;
        Color (String id) {
            this.id = id;
        }
    
        //Required if you want Color.{color} to give back a color
        @Override
        public String toString() {
            return id;
        }
        
        public void text() { write("3" + id); }
        public void highlight() { write("4" + id); }
        public void brightText() { write("9" + id); }
        public void brightHighlight() { write("10" + id); }
    }


    /*
    class Text implements Ansi{ 
        //Inherented functions
        public void write (String s) {
            System.out.print(CSI + s + "m");
             
        }
        public void writeRaw(String s) {
            String tempCSI = "\\u001B" + "[";
            System.out.print(tempCSI + s + "m"); 
            
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
                default:
                    sb.append(Color.BLACK);
            }
    
             write(sb.toString());
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
    
    
     
        //Color manipulate
        public static void reverse () {  write("7");  } //reverse swaps color of highlight and foreground
        public static void inverse_off () {  write("27");  }
        public static void conceal () {  write("8");  }
        public static void reveal_off () {  write("28");  }
        public static void crossed_out () {  write("9");  }
        public static void not_crossed_out () {  write("29");  }
    
    
        //void change
        public static void bold() {  write("1");  }
        public static void italic() {  write("3");  } 
        public static void itallic_off () {  write("23");  }
        public static void underline () {  write("4");  }
        public static void underline_off () {  write("24");  }
        
    
        //Underlining void
        public static void double_underline () {  write("21");  }
        public static void framed () {  write("51");  }
        public static void framed_off () {  write("54");  }
        public static void encircled () {  write("52");  }
        public static void overlined () {  write("53");  } //Not supported...
        public static void overline_off () {  write("55");  }
    }
    */
}
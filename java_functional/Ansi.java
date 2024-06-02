package java_functional;

import java.util.HashMap;

class Ansi {
    // Octal: \033
    // Unicode: \u001b
    // Hexadecimal: \x1b
    // Decimal: 27
    final static String ESCAPE = "\u001B";
    final static String CSI = ESCAPE + "[";
    final static String END = CSI + "0m";

    static void reset() {
        System.out.print(END);
    }; // This resets arguments

    static void resetln() {
        System.out.println(END);
    }; // This resets arguments

    static void print(String s) {
        System.out.print(s);
    }

    static void println(String s) {
        System.out.println(s);
    }



    interface TextInterface {
        default void write(String s) {
            System.out.print(CSI + s + "m");
        }
    }
    enum Colors implements TextInterface{
        BLACK("0"), RED("1"), GREEN("2"), YELLOW("3"), BLUE("4"),
        PURPLE("5"), CYAN("6"), WHITE("7"),
        RGB("")
        ;

        String id;
        Colors(String id) {
            this.id = id;
        }


        public void text() {
            text(id);
        }
        public void text(int color) { //8bit
            String id = String.format("8;2;%d", color);
            text(String.valueOf(id));
        }
        public void text(int r, int g, int b) { //24-bit
            String id = String.format("8;2;%d;%d;%d", r,g,b);
            text(id);
        }
        private void text(String id) {
            write("3" + id);
        }


        public void highlight() {
            highlight(id);
        }
        public void highlight(int color) { //8-bit
            String id = String.format("8;2;%d", color);
            highlight(String.valueOf(id));
        }
        
        public void highlight(int r, int g, int b) { //24-bit
            String id = String.format("8;2;%d;%d;%d", r,g,b);
            highlight(id);
        }
        private void highlight(String id) {
            write("4" + id);
        }


        public void brightText() {
            write("9" + id);
        }
        public void brightHighlight() {
            write("10" + id);
        }
    }
    enum Fonts implements TextInterface {
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

        public void print() { write(id); }
    }


    interface CursorInterface {
        default void write(String s) {
            System.out.print(CSI + s); 
        }
    }
    enum Cursor implements CursorInterface {
        //TODO, check if values commands are changed by value:
        //  1) Home
        //  2) Scroll
        UP("A"), DOWN("B"), RIGHT("C"), LEFT("D"),
        
        DOWNANDNEWLINE("E"), UPANDNEWLINE("F"), COLUMN("G"), 
        HOME("H"),

        SCROLLUP("S"), SCROLLDOWN("T")
        ;
        String id;
        Cursor (String id) {
            this.id = id;
        }

        public void move(int i) {
            write(String.valueOf(i) + id);
        }
    }
    
}
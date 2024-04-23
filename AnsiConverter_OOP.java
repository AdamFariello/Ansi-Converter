/* 
class Ansi {
    private final static String FORMAT = "\u001B[" + "%s" + "m";
    private final String END = FORMAT.format(FORMAT, 0);
    
    private String s; 

    Ansi () {
        s = FORMAT;
    }


    enum Color {
        // 8 is used to unlock 8bit colors
        BLACK("0"), RED("1"), GREEN("2"),
        YELLOW("3"), BLUE("4"), PURPLE("5"),
        CYAN("6"), WHITE("7"), DEFAULT("9");

        private final String id;

        Color(String id) {
            this.id = id;
        }
    }
    public String text(Color c) { return '3' + c.id; }
    public String highlight(Color c) { return '4' + c; }

    // These are usually never implemented
    public String textBright(Color c) { return '9' + c; }
    public String highlightBright(Color c) { return combined(c, "10"); }


    public String toString() {
        return s + END;
    }
}
*/

public class AnsiConverter_OOP {
    /*
     * public String reset() {
     * // Return reset ansi string
     * return String.format(FORMAT, 0);
     * }
     */

    // Main class
    public static void main(String args[]) {
        enum Color {
            // 8 is used to unlock 8bit colors
            BLACK("0"), RED("1"), GREEN("2"),
            YELLOW("3"), BLUE("4"), PURPLE("5"),
            CYAN("6"), WHITE("7"), DEFAULT("9");
    
            private final String id;
    
            Color(String id) {
                this.id = id;
            }
            
            @Override
            public String toString () {
                return id;
            }

            public String text() {
                return '3' + id;
            }
        }

        System.out.println(Color.BLACK.text());
    }
}
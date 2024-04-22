class Ansi {
    // final static String FORMAT = "\u001B[" + "%s" + "m";
    private String FORMAT = "\u001B[m";
    
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

    public String getFormat() {
        return FORMAT;
    }


    static class ColorEffect extends Ansi {  
        public ColorEffect() {} 

        public String text(Color c) {
            return combined(c, "3");
        }
    
        public String highlight(Color c) {
            return combined(c, "4");
        }
    
        // These are usually never implemented
        public String textBright(Color c) {
            return combined(c, "9");
        }
    
        public String highlightBright(Color c) {
            return combined(c, "10");
        }
    
        private String combined(Color c, String effect) {
            String passedString = this.getFormat();
            passedString = passedString.substring(0, passedString.length() - 1);
            return passedString + ';' + effect + c.id + 'm';
        }
    }
}

public class AnsiConverter_Functions {
    /*
     * public String reset() {
     * // Return reset ansi string
     * return String.format(FORMAT, 0);
     * }
     */

    // Main class
    public static void main(String args[]) {
        // Ansi obj = new Ansi();
        // System.out.println(Ansi.text("BLACK"));

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

        Ansi.ColorEffect obj = new Ansi.ColorEffect();
        System.out.println(Color.BLACK);
    }
}
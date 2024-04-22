public class AnsiConverter_Functions {
    final String FORMAT = "\u001B[m";
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
        String passedString = this;
        passedString = passedString.substring(0, passedString.length() - 1);
        return passedString + ';' + effect + c.id + 'm';
    }


    // Main class
    public static void main(String args[]) {
        // Ansi obj = new Ansi();
        // System.out.println(Ansi.text("BLACK"));



        System.out.println(Color.BLACK);
    }
}
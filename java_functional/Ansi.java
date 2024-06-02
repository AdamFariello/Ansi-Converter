package java_functional;

import java.util.HashMap;

class Ansi implements Interface_Ansi {
    // TODO: Refactor Interface_Ansi so it's not needed to implement
    //       for these functions.
    //       Most likely need to remove such functions...
    public static void resetText() {
        System.out.print(END);
    }

    public static void resetCursor() {
        Cursor.HOME.set();
        System.out.println("\n");
    }

    public enum Colors implements Interface_Text {
        BLACK("0"), RED("1"), GREEN("2"), YELLOW("3"), BLUE("4"),
        PURPLE("5"), CYAN("6"), WHITE("7"),
        RGB("");

        String id;

        Colors(String id) {
            this.id = id;
        }

        public void text() {
            text(id);
        }

        public void text(int color) { // 8bit
            String id = String.format("8;2;%d", color);
            text(String.valueOf(id));
        }

        public void text(int r, int g, int b) { // 24-bit
            String id = String.format("8;2;%d;%d;%d", r, g, b);
            text(id);
        }

        private void text(String id) {
            write("3" + id);
        }

        public void highlight() {
            highlight(id);
        }

        public void highlight(int color) { // 8-bit
            String id = String.format("8;2;%d", color);
            highlight(String.valueOf(id));
        }

        public void highlight(int r, int g, int b) { // 24-bit
            String id = String.format("8;2;%d;%d;%d", r, g, b);
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
    public enum Fonts implements Interface_Text {
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

        Fonts(String id) {
            this.id = id;
        }

        public void print() {
            write(id);
        }
    }

    public enum Cursor implements Interface_Cursor {
        // TODO, check if values commands are changed by value:
        // 1) Home
        // 2) Scroll
        UP("A"), DOWN("B"), RIGHT("C"), LEFT("D"),

        DOWNANDNEWLINE("E"), UPANDNEWLINE("F"), COLUMN("G"),
        HOME("H"),

        SCROLLUP("S"), SCROLLDOWN("T"),;

        String id;

        Cursor(String id) {
            this.id = id;
        }

        public void move(int lines) {
            write(String.valueOf(lines) + id);
        }

        public void set() {
            write(id);
        }
    }
    public enum CursorPosition implements Interface_Cursor {
        // You cannot interchange to have two saved cursor positions
        // Dec is used more often, so it'll be used instead
        // Save Restore
        // Dec ESC + 7 ESC + 8
        // SCO ESC + [s ESC + [u
        SAVE("7"), RESTORE("8");

        String id;

        CursorPosition(String id) {
            this.id = id;
        }

        public void position() {
            write(id);
        }
    }
    public enum ClearScreen implements Interface_Cursor {
        // TODO: Figure out the discrempencies of:
        // 1) clearScreen_entire() { return write("J"); }
        // clearLine_current () { return write("K"); }
        // 2) clearScreen_cursorToEntireScreen() { return write("2J"); }
        // clearLine_entire() { return write("2K"); }
        SCREEN("J"), LINE("K");

        String id;

        ClearScreen(String id) {
            this.id = id;
        }

        public void entire() { write(id) };

        public void toEnd() {
            write("0" + id);
        }

        public void toBeginning() {
            write("1" + id);
        }

        public void toEndOf() {
            write("2" + id);
        }
    }
    public enum CursorSettings implements Interface_Cursor {
        ON("5m"), OFF("25m"), RAPID("6m");

        String id;

        CursorSettings(String id) {
            this.id = id;
        }

        public void change() {
            write(id);
        }
    }
}
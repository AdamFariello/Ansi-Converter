package java_functional;

import ansiUtil.ScriptHandler;

public class Ansi implements Interface_AnsiVariables {
    public class Text extends Ansi {
        public static void reset() { System.out.print(END); }
        public static void resetln() { System.out.println(END); }

        public enum Colors implements Interface_Text {
            BLACK("0"), RED("1"), GREEN("2"), YELLOW("3"), BLUE("4"),
            PURPLE("5"), CYAN("6"), WHITE("7"),
            RGB("");
            String id;
            Colors(String id) { this.id = id; }

            public void text() { write("3" + id); }
            public void highlight() { write("4" + id); }
            public void brightText() { write("9" + id); }
            public void brightHighlight() { write("10" + id); }
        }
        public enum ColorsEight implements Interface_Text {
            TEXT("38:5:%d"), HIGHLIGHT("48:5:%d");
            String format;
            ColorsEight(String format) { this.format = format; }

            public void print(int color) {
                write(String.format(format, color));
            }
        }
        public enum ColorsTwoFour implements Interface_Text {
            TEXT("38;2;%d;%d;%d"), HIGHLIGHT("48;2;%d;%d;%d");            
            String format;
            ColorsTwoFour(String format) { this.format = format; }

            public void print(int r, int g, int b) {
                write(String.format(format, r, g, b));
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
            Fonts(String id) { this.id = id;}
    
            public void print() { write(id); }
        }
    }



    
    public class Cursor extends Ansi {
        public static void reset() {
            Cursor.Move.HOME.set();
            System.out.println("\n");
        }

        public enum Move implements Interface_Cursor {
            UP("A"), DOWN("B"), RIGHT("C"), LEFT("D"),
            DOWNNEWLINE("E"), UPNEWLINE("F"), //Don't this this here 
            COLUMN("G"), 
            HOME("H");
            ;
            String id;
            Move(String id) { this.id = id; }
    
            public void set() { write(id); }
            public void by(int spaces) {
                write(String.valueOf(spaces) + id);
            }
        }

        public static int[] getCurrentCursorPosition () {
            ScriptHandler.runScript();
            return ScriptHandler.readOutputFile();

        }

        public enum To implements Interface_Cursor {
            //TODO: Figure out if I want to keep this
            LINETOCOLUMN("%d;%d" + "%c");
            String format;
            To(String format) { this.format = format; }

            public void go(int line, int col) {  go(line, col, 'H'); } 
            public void go2(int line, int col) { go(line, col, 'f'); }
            private void go(int line, int col, char c) { 
                write(String.format(format,line,col,c)); 
            } 
        }
        public enum Scroll implements Interface_Cursor {
            UP("S"), DOWN("T");
            String id; 
            Scroll(String id) { this.id = id; }

            public void by(int lines) {
                write(String.valueOf(lines) + id);
            }
        }
        public enum CursorStorageSCO implements Interface_Cursor {
            // You cannot interchange to have two saved cursor positions
            // Dec is used more often, so it'll be used instead
            // Save Restore
            //
            // Also, this way of saving and restoring cursor positions is
            // unlikely to work...
            SAVE("s"), RESTORE("u");
            String id;
            CursorStorageSCO(String id) { this.id = id; }
    
            public void position() { write(id); }
        }
        public enum CursorStorageDEC implements Interface_CursorDEC {
            // You cannot interchange to have two saved cursor positions
            // Dec is used more often, so it'll be used instead
            // Save Restore
            SAVE("7"), RESTORE("8");
            String id;
            CursorStorageDEC(String id) { this.id = id; }
    
            public void position() { write(id); }
        }
        public enum Clear implements Interface_Cursor {
            // TODO: Figure out the discrempencies of:
            //       1) clearScreen_entire() { return write("J"); }
            //          clearLine_current () { return write("K"); }
            //       2) clearScreen_cursorToEntireScreen() { return write("2J"); }
            //          clearLine_entire() { return write("2K"); }
            SCREEN("J"), LINE("K");
            String id;
            Clear(String id) {
                this.id = id;
            }
    
            public void entire() { write(id); };
            public void toEnd() { write("0" + id); }
            public void toBeginning() { write("1" + id); }
            public void toEndOf() { write("2" + id);}
        }
        public enum Settings implements Interface_Cursor {
            ON("5m"), OFF("25m"), RAPID("6m");
            String id;
            Settings(String id) { this.id = id; }
    
            public void change() { write(id); }
        }   
    }
}
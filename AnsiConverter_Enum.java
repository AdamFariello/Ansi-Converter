/*
//TODO: Add these effects later

//Fonts
PRIMARY("10"),             //Computer Default font(?)
ALTERNATE_FONT("11–19"),   //No clue on what fonts are connected
NORMAL_COLOR("22"),        //Terminal's default color (?)

//Unsupported
FAINT("2"),
FRAKTUR("20"),                         
UNDERLINE_COLOR("58;5")
IDEOGRAM_UNDERLINE("60"),
IDEOGRAM_DOUBLE_UNDERLINE("61"),
IDEOGRAM_OVERLINE("62"),
IDEOGRAM_DOUBLE_OVERLINE("63"),
IDEOGRAM_STRESS("64"),
IDEOGRAM_ATTRIBUTES_RESET("65"),

//For the T.61 and T.416
PROPORTIOAL_SPACING("26"),             
DISABLE_PROPORTIONAL_SPACING("50"),

//Implemented only in mintty
SUPERSCRIPT("73"),                      
SUBSCRIPT("74"),
NEITHER("75")
*/

public class AnsiConverter_Enum {
    private final static String ANSI_FORMAT = "\u001B[" + "%s" + "m";

    private enum Ansi {
        //Default
        RESET("0"), ESCAPE("0");
        private final String id;
        Ansi(String id) { this.id = id; }
        private String Ansi() { return id; }

        enum Color {
            BLACK   ("0"), 
            RED	    ("1"),
            GREEN	("2"),
            YELLOW	("3"),
            BLUE	("4"),
            PURPLE	("5"),
            CYAN	("6"),
            WHITE	("7"),
            //8 is used to unlock 8bit colors
            DEFAULT ("9")
            ;

            private final String id;   
            Color(String id) { this.id = id; }

            //Default is text Color
            private String text() { return "3" + id; }
            private String highlight() { return "4" + id; }

            //Not in standard, doesn't hurt to add
            private String textBright() { return "9" + id; }      
            private String highlightBright() { return "10" + id; }
        }

        enum Effect {                    
            //Font changing
            BOLD("1"),
            ITALIC("3"),        ITALLIC_OFF("23"),
            UNDERLINE("4"),     UNDERLINE_OFF("24"),
            SLOW_BLINK("5"),    BLINK_OFF("25"),
            RAPID_BLINK("6"),   
            REVERSE("7"),       INVERSE_OFF("27"), //Reverse foreground and background colors
            CONCEAL("8"),       REVEAL_OFF("28"),
            CROSSED_OUT("9"),   NOT_CROSSED_OUT("29"),
            
            //Underlining
            DOUBLE_UNDERLINE("21"),         
            FRAMED("51"),       FRAMED_OFF("54"),
            ENCIRCLED("52"),
            OVERLINED("53"),    OVERLINE_OFF("55")
            ;
            private final String id;

            Effect(String id) { this.id = id; }
            private String Effect() { return id; }
        }

        private String Reset() { return String.format(ANSI_FORMAT, id); }
    }

    public static void main (String [] args) throws Exception {
        /*
        String blueText = Color.BLUE + "Blue text" + Effect.RESET;

        System.out.println(blueText);
        System.out.println("Default text");
        */
        
        System.out.println(Ansi.Color.BLUE.Color());
    }
}

    /*
    private enum EffectUmplemented {
        //Rarely unsupported; not on unix so who cares
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
        PROPORTIOAL_SPACING("26"),  DISABLE_PROPORTIONAL_SPACING("50")

        //Implemented only in mintty
        SUPERSCRIPT("73"),
        SUBSCRIPT("74"),
        NEITHER("75")

        private final char effectID;
        EffectUmplemented(char effectID) { 
            this.effectID = effectID;
        }
        private char effect() { return effectID; }
    }
    */

public class AnsiConverter_Enum {
    //Comes with "%s" since there"s alot of arguments to give"
    //private static final String ANSI_ESACPE = "\u001B[";
    private static final String ANSI_FORMAT = "\u001B[%sm"; 


    private enum Ansi {
        RESET("0"),         ESACPE("0"),
        BOLD("1"),

        ITALIC("3"),        ITALLIC_OFF("23"),
        UNDERLINE("4"),     UNDERLINE_OFF("24"),
        SLOW_BLINK("5"),    BLINK_OFF("25"),
        RAPID_BLINK("6"),   
        
        REVERSE("7"),       INVERSE_OFF("27"), //Reverse foreground and background colors
        CONCEAL("8"),       REVEAL_OFF("28"),
        CROSSED_OUT("9"),   NOT_CROSSED_OUT("29"), 

        //PRIMARY("10"),             //Default font
        //ALTERNATE_FONT("11–19"),   //No clue on what fonts are connected
        
        DOUBLE_UNDERLINE("21"), 

        //What?
        NORMAL_COLOR("22"), 
        FRAMED("51"),       FRAMED_OFF("54"),
        ENCIRCLED("52"),
        OVERLINED("53"),    OVERLINE_OFF("55")
        ;

        private final String effectID;
        Effect(String effectID) { 
            this.effectID = effectID;
        }
        private String effect() { return effectID; }
    }

    private enum FourBitColor {
        /* 
        Color Name	Color code	Background Color	Background Color code
        BLACK	\u001B[30m	BLACK_BACKGROUND	    \u001B[40m
        RED	    \u001B[31m	RED_BACKGROUND	        \u001B[41m
        GREEN	\u001B[32m	GREEN_BACKGROUND	    \u001B[42m
        YELLOW	\u001B[33m	YELLOW_BACKGROUND	    \u001B[43m
        BLUE	\u001B[34m	BLUE_BACKGROUND	        \u001B[44m
        PURPLE	\u001B[35m	PURPLE_BACKGROUND	    \u001B[45m
        CYAN	\u001B[36m	CYAN_BACKGROUND	        \u001B[46m
        WHITE	\u001B[37m	WHITE_BACKGROUND	    \u001B[47m
        Basically: \u001B[__m

        To reset color (so it resets): ANSI_RESET = "\u001B[0m"; 
        */
        BLACK   ("0"), 
        RED	    ("1"),
        GREEN	("2"),
        YELLOW	("3"),
        BLUE	("4"),
        PURPLE	("5"),
        CYAN	("6"),
        WHITE	("7"),
        DEFAULT ("9"); //8 is used to unlock 8bit colors
    
        private final String colorID;   
        FourBitColor(String colorID) { 
            this.colorID = colorID; 
        }
        private String color() { return colorID; }

        private String textColor() { return "3" + colorID; }
        private String textForeground() { return "3" + colorID; }
        private String highlightColor() { return "4" + colorID; }
        private String backgroundColor() { return "4" + colorID; }

        //Not in standard, doesn"t hurt to add
        private String textColorBright() { return "9" + colorID; }
        private String forgroundColorBright() { return "9" + colorID; }
        private String highlightColorBright() { return "10" + colorID; }
        private String backgroundColorBright() { return "10" + colorID; }
    }


    public String convertText() {
        
    }

    public String Rainbow(String s) {
        for (int i = 0; i < s.length(); i++) {
            int color = (i + 1) % 8;
            System.out.printf(ANSI_FORMAT + s.charAt(i) + .effect(), 31 + 0)
        }

        RED	    ("1"),
        GREEN	("2"),
        YELLOW	("3"),
        BLUE	("4"),
        PURPLE	("5"),
        CYAN	("6"),
        WHITE	("7"),
        DEFAULT ("9"); //8 is used to unlock 8bit colors

        return null;
    }


    public static void main (String [] args) throws Exception {
        //String blueText = BASE + TEXT + BLUE + 1; 
        String blueText = BASE + TEXT + 4;

        System.out.println(blueText + "test" + ANSI_RESET);
        System.out.println("This text should be default");
    }
}

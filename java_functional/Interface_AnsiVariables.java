package java_functional;

public interface Interface_AnsiVariables {    
    // Octal: \033
    // Unicode: \u001B or \u001b 
    // Hexadecimal: \x1b
    // Decimal: 27
    final static String ESC = "\u001B";
    final static String CSI = ESC + "[";
    final static String END = CSI + "0m";
    
    final static String ESC_raw = "\\u001B";
    final static String CSI_raw = ESC_raw + "[";
    final static String END_raw = CSI_raw + "0m";
}


interface Interface_Ansi extends Interface_AnsiVariables {    
    //Interface is not an object, so I can't do:
    //  1) super.write,
    //  2) Interface_Ansi.write 
    //  3) etc.
    default void writeEither(String formated) {
        System.out.print(formated);
    }
}


interface Interface_Text extends Interface_Ansi {
    String format = "%s" + "%s" + "m";
    default void write(String s) {
        writeEither(String.format(format, CSI, s));
    }
    default void writeRaw(String s) {
        writeEither(String.format(format, CSI_raw, s));
    }
}


interface Interface_Cursor extends Interface_Ansi {
    String format = "%s" + "%s";
    default void write(String s) {
        writeEither(String.format(format, CSI, s));
    } 
    default void writeRaw(String s) {
        writeEither(String.format(format, CSI_raw, s));
    }
}


interface Interface_CursorDEC extends Interface_Ansi {
    String format = "%s" + "%s";
    default void write(String s) {
        writeEither(String.format(format, ESC, s));
    } 
    default void writeRaw(String s) {
        writeEither(String.format(format, ESC, s));
    }
}
package java_functional;

public interface Interface_Ansi {
    // Octal: \033
    // Unicode: \u001b
    // Hexadecimal: \x1b
    // Decimal: 27
    final static String ESCAPE = "\u001B";
    final static String CSI = ESCAPE + "[";
    final static String END = CSI + "0m";
}

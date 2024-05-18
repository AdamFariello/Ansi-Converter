package java_oop;

abstract class Ansi {
    //Octal: \033
    //Unicode: \u001b
    //Hexadecimal: \x1b
    //Decimal: 27
    protected final static String ESCAPE = "\u001B";
    protected final static String END = ESCAPE + "[" + "0m";
    
    public Ansi () {}

    public abstract Ansi write (String s);

    @Override
    public abstract String toString();
}
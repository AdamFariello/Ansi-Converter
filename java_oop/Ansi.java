package java_oop;

abstract class Ansi {
    //Octal: \033
    //Unicode: \u001b
    //Hexadecimal: \x1b
    //Decimal: 27
    protected final static String ESCAPE = "\u001B"; //Alt: CSI
    protected final static String END = ESCAPE + "[" + "0m";
    
    public Ansi () {}

    public abstract Ansi write (String s);
    public abstract Ansi reset (); //This resets arguments
    public abstract Ansi print (String s);
    public abstract Ansi println (String s);
}
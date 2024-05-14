package java_oop;

abstract class Ansi {
    //Octal: \033
    //Unicode: \u001b
    //Hexadecimal: \x1b
    //Decimal: 27
    protected final static String ESCAPE = "\u001B";
    protected final static String END = ESCAPE + "[" + "0m";
    
    public Ansi () {}


    //TODO: Figure out if the string methods should be in the parent class
    //String Methods 
    /*

    */
    @Override
    public abstract String toString();

    public void print() { System.out.print(toString()); }
    public void println() { System.out.println(toString()); }    
}
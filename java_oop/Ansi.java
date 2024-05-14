package java_oop;

abstract class Ansi {
    //Octal: \033
    //Unicode: \u001b
    //Hexadecimal: \x1b
    //Decimal: 27
    protected final static String ESCAPE = "\u001B";
    protected final static String END = ESCAPE + "[" + "0m";

    protected String storeS = "";
    public Ansi () {}


    //TODO: Figure out if the string methods should be in the parent class
    //String Methods 
    public String toString() {
        if (storeS.length() > 0) {
            //Removes ';'
            return storeS.substring(0, storeS.length() - 1);
        } else {
            return "";
        }
    }
    public void print() { System.out.print(toString()); }
    public void println() { System.out.println(toString()); }    
}
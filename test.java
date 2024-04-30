import java.lang.annotation.Inherited;

public class test {
    public static void main (String args[]) {
        //Test 1 (Manually)
        System.out.println("*".repeat(20));
        System.out.println("Before");
        System.out.print("\u001B[31;1m" + "abcdef" + "\u001B[0m");
        System.out.println("\u001B[32;1m" + "ghijk" + "\u001B[0m");
        System.out.print("\u001B[34;1m" + "lmnop" + "\u001B[0m");
        System.out.println("\u001B[33;1m" + "qrstu" + "\u001B[0m");
        System.out.println();

        System.out.println("After");
        System.out.print("\u001B[31;1m" + "abcdef" + "\u001B[0m");
        System.out.println("\u001B[32;1m" + "ghijk" + "\u001B[0m");

        System.out.print("\u001B[34;1m" + "" + "\u001B[0m");
        //System.out.print("\u001B[H" + "\u001B[2K"); 
        System.out.print("\u001B[H\u001B[2K"); 
        System.out.println("\u001B[33;1m" + "Yellow Text" + "\u001B[0m");
        System.out.println("*".repeat(20));
    }    
}

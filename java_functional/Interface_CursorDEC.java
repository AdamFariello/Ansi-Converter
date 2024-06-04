package java_functional;

public interface Interface_CursorDEC extends Interface_Ansi {
    default void write(String s) {
        System.out.print(ESC + s);
    }
    default void writeRaw(String s) { 
        System.out.print(ESC_raw + s);
    }
}
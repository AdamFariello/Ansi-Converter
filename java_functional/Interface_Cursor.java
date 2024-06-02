package java_functional;

public interface Interface_Cursor extends Interface_Ansi {
    default void write(String s) {
        System.out.print(CSI + s);
    }
}

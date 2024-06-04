package java_functional;

public interface Interface_Text extends Interface_Ansi{
    default void write(String s) {
        System.out.print(CSI + s + "m");
    }

    default void write_raw(String s) {
        System.out.print(CSI_raw + s + "m");
    }
}

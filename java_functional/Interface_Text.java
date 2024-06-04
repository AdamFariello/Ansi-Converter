package java_functional;

public interface Interface_Text extends Interface_Ansi{
    default void write(String s) {
        System.out.print(CSI + s + "m");
    }

    default void writeRaw(String s) {
        System.out.print(CSI_raw + s + "m");
    }
}

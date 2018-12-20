package me.gg.jnicmakedemo;

public class JniUtils {
    static {
        System.loadLibrary("native-lib");
    }
    public static native String getName();

    public static native int add(int a, int b);

    public static native String saiHi(String name);
}

public class JNI {

    static {
        System.loadLibrary("JNI.dylib");
    }
    public native void greeting();
    public native String getMessageFromInputString(String msg);
    public native int getSumOfTwoNumbers(int a, int b);

    public static void main(String[] args) {
        JNI jni = new JNI();
        jni.greeting();
        
        System.out.println(jni.getMessageFromInputString("Hello world"));
        System.out.println(jni.getSumOfTwoNumbers(1, 2));
    }
}
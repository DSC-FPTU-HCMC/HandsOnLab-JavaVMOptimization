
This sub-repository is to learn JNI

Firstly, create a Java file (.java) by magic. For example I used: ```nano JNI.java```.
Then write down the following code:
```
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
```

- You must declare the ```System.loadLibrary("...libfile...");``` in the static block to load the library first.
- Then declare the methods which only contain empty body with the "native" keyword.

Get the C header file by using the compilation method of javac to convert the Java file into header file to be used in the C main file.
```
javac -h . JNI.java
```

Then create a C file.
```
touch JNI.c
```

- Write down the code.

```
#include <jni.h>
#include "JNI.h"

JNIEXPORT void JNICALL Java_JNI_greeting
  (JNIEnv* env, jobject obj) {
      printf("Hello World from void method!");
      return;
  }

JNIEXPORT jstring JNICALL Java_JNI_getMessageFromInputString
  (JNIEnv* env, jobject obj, jstring s) {
      return s;
  }

JNIEXPORT jint JNICALL Java_JNI_getSumOfTwoNumbers
  (JNIEnv* env, jobject obj, jint a, jint b) {
      return a+b;
  }
```

You must specific JAVA_HOME Path before doing this. For example, my machine:
JAVA_HOME_PATH: ```/Library/Java/JavaVirtualMachines/adoptopenjdk8-openj9.jdk/Contents/Home/```

SYSTEM_TYPE: ```darwin``` (for macOS)  or ```linux``` (for Linux) or ```windows``` (for Windows).

INPUT_C_FILE: ```The name of the C to be compiled to external library.```

LIB_OUTPUT_FILE: ```The name of the library file want to be loaded in Java code.```

LIB_OUTPUT_EXTENSION: ```dll``` (for Windows) or  ```so``` (for Linux) or ```dylib``` (for macOS).

Then we use the ```gcc``` compiler tool to compile the C file into a specified library file.
```
gcc -I${JAVA_HOME_PATH}/include -I${JAVA_HOME_PATH}/include/${SYSTEM_TYPE}  -shared -o ${LIB_OUTPUT_FILE}.${LIB_OUTPUT_EXTENSION} ${INPUT_C_FILE}.c
```

For now, we have a library file, a Java file. We now compile the Java file into Java class file (bytecode).

```
javac JNI.java
```

Before using the JNI method, we must define the library path as the JVM Flag. If not, the JVM will occur an error of not able to find the library file that we compiled from C file.
```
javac -Djava.library.path=. JNI
```

Finally, we successfully did the lab.

By BangMaple. 2020.

------------





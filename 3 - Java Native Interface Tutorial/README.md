# 3 - Java Native Interface Tutorial
## This sub-repository is to learn JNI

---------------------
## Prerequisites:
- Nothing yet.

### !!!PLEASE READ BEFORE PROCEEDING

```
In this tutorial, there is something different than yours:
- Operating System: macOS 10.14.6.
- Java Virtual Machine: AdoptOpenJDK OpenJ9 Java Virtual Machine 8.
- C Compiler: Apple CLang version 11.0.0.

If you are using Windows, please install GNU GCC Compiler or LLVM Clang or something that can do the C Compilation thing.

If you are using Linux, make sure you do have GCC Compiler.

If you are using macOS, there is no need for the compiler because Apple Inc. has already embedded the compiler in the operating system.


Make sure you have your Java Virtual Machine installed. OpenJDK or AdoptOpenJDK are both compatible for this tutorial.

-- For checking GCC Compiler version or make sure it is already installed, using the following command: gcc --version
```

In order to use the ```JNI (Java Native Interface)``` as an external dynamic library for the Java code, we should follow the topic and be careful!

Good luck!

--------------
# Step 1: CREATE A JAVA CLASS THAT CONTAINS 'NATIVE' KEYWORD IN THE ABSTRACT METHODS

In this tutorial, we will create a Java file named ```JNI.java```. You can freely change your filename but rememeber what are you going to do with.

To create a new external library, we must define our ```abstract method``` just like how we declare a full-body method for a class which implemented an interface/abstract class but with ```native``` keyword.

Firstly, we create a Java file (.java) by magic.
- For example, in macOS we use the below command to enter the GNU Nano Editor for the Java coding. For the Linux system, the ```nano``` command should be working for you too. If not, please install an editor to continue.
```
nano JNI.java
```

- In Windows OS, the command should be changed a little but still have the same meaning, please do make sure this is the first time you are going to create this file:
```
copy nul JNI.java > nul
notepad JNI.java
```

We are not going to use an IDE for this lab, so please do the coding like the time we first do the Java fundamental coding and bare ```javac``` command.

Then we write down or copy the following code (you can remove the comment if you don't like and please change the ```.dylib``` extension to your OS's type we learnt before):
```
public class JNI {

    //Load the native dynamic library first
    static {
        System.loadLibrary("JNI.dylib"); //Please change the extension
                                          //if you don't use macOS
    }

    //We declare the following 3 abstract methods with native keyword for example
    public native void greeting();
    public native String getMessageFromInputString(String msg);
    public native int getSumOfTwoNumbers(int a, int b);

    //Do the coding as usual
    public static void main(String[] args) {
        JNI jni = new JNI();
        jni.greeting();
        System.out.println(jni.getMessageFromInputString("Hello world"));
        System.out.println(jni.getSumOfTwoNumbers(1, 2));
    }
}
```

- For macOS/Linux users, please do the combination of keys ```Ctrl + X``` and type ```Y``` key to attempt to save the file.
- For Windows users, please do the combination of keys ```Ctrl + S``` to save the file.

-----------------
# Step 2: GENERATE C HEADER FILE USING JAVA TOOLS

- Generate C header file by using the compilation method of ```javac``` to convert the written Java file into C header file to be used in the ```.c``` file.
```
javac -h . JNI.java
```

After having the generated C header file with the filename ```JNI.h```, we now create a new ```.c``` file and write down the code.

```
nano JNI.c
```

- Write down the code (Delete the comment if you don't like):

```
//Include the main header file of Java Native Interface
#include <jni.h>

//Include the generated header file.
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

You can have a look at the generated C header file, ```JNI.h``` contains the abstract functions just like how we implemented those functions in the ```.c``` file.

- You must specify JAVA_HOME Path before doing the next step. For example:

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





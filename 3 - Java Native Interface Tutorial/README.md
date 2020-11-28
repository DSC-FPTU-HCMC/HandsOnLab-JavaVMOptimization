![DSC FPTU HCMC](../assets/images/dsc-fptu-hcmc/DSC_FPT_University_HCMC_Horizontal_Logo.png)

# 3 - Java Native Interface Tutorial
### Hand-on lab

---------------------
## Prerequisites:
- Having a Java Virtual Machine installed and ready to be run.
- Having a ```Terminal``` or ```Command-line```.
- No ```Java IDE``` is required.
- A ```nice text editor``` should do it job.
- The ```java``` and ```javac``` commands should be running fine.

### !!!PLEASE READ BEFORE PROCEEDING

```
In this tutorial, there is something different than yours:
- Operating System: macOS 10.14.6.
- Java Virtual Machine: AdoptOpenJDK OpenJ9 Java Virtual Machine 8.
- C Compiler: Apple CLang version 11.0.0.
```


If you are using Windows, please download the ```"MinGW64.7z"``` and move to ```C:\``` directory then click ```"Extract Here"``` the compressed file. After that, open the command-line then type
```bash 
path %PATH%;%homedrive%\MinGW64\bin
``` 
and you are good to go.

If you are using Linux, you already have the default GNU GCC.

If you are using macOS, there is no need for the compiler installation because Apple Inc. has already embedded the compiler in their operating system.


```
Make sure you have your Java Virtual Machine installed. OpenJDK or AdoptOpenJDK are both compatible for this tutorial.
```


-- For checking GCC Compiler version or make sure it is already installed, using the following command:
```bash
gcc --version
```

In order to use the ```JNI (Java Native Interface)``` as an external dynamic library for the Java code, we should follow the topic and be careful!

Good luck!

--------------
# Step 1: CREATE A JAVA CLASS THAT CONTAINS 'NATIVE' KEYWORD IN THE ABSTRACT METHODS

In this tutorial, we will create a Java file named ```JNI.java```. You can freely change your filename but rememeber what are you going to do with.

To create a new external library, we must define our ```abstract method``` just like how we declare a full-body method for a class which implemented an interface/abstract class but with ```native``` keyword.

Firstly, we create a Java file (.java) by magic.
- For example, in macOS we use the below command to enter the GNU Nano Editor for the Java coding. For the Linux system, the ```nano``` command should be working for you too. If not, please install an editor to continue.

```bash
nano JNI.java
```

- In Windows OS, the command should be changed a little but still have the same meaning, please do make sure this is the first time you are going to create this file:

```bash
copy nul JNI.java > nul
notepad JNI.java
```

We are not going to use an IDE for this lab, so please do the coding like the time we first do the Java fundamental coding and bare ```javac``` command.

Then we write down or copy the following code (you can remove the comment if you don't like and please change the ```.dylib``` extension to your OS's type we learnt before):


```java
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

```bash
javac -h . JNI.java
```

After having the generated C header file with the filename ```JNI.h```, we now create a new ```.c``` file and write down the code.

Firstly, we create a C file (.c) by magic.
- For example, in macOS we use the below command to enter the GNU Nano Editor for the Java coding. For the Linux system, the ```nano``` command should be working for you too. If not, please install an editor to continue.

```bash
nano JNI.c
```

- In Windows OS, the command should be changed a little but still have the same meaning, please do make sure this is the first time you are going to create this file:

```bash
copy nul JNI.c > nul
notepad JNI.c
```

- Write down the code (Delete the comment if you don't like):

```c
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

- For macOS/Linux users, please do the combination of keys ```Ctrl + X``` and type ```Y``` key to attempt to save the file.
- For Windows users, please do the combination of keys ```Ctrl + S``` to save the file.

You can have a look at the generated C header file, ```JNI.h``` contains the abstract functions just like how we implemented those functions in the ```.c``` file.

```c
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class JNI */

#ifndef _Included_JNI
#define _Included_JNI
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     JNI
 * Method:    greeting
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_JNI_greeting
  (JNIEnv *, jobject);

/*
 * Class:     JNI
 * Method:    getMessageFromInputString
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_JNI_getMessageFromInputString
  (JNIEnv *, jobject, jstring);

/*
 * Class:     JNI
 * Method:    getSumOfTwoNumbers
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_JNI_getSumOfTwoNumbers
  (JNIEnv *, jobject, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
```

--------------

# Step 3: BUILD DYNAMIC LIBRARY FILE USING C COMPILER

### Prerequisites:
- The written Java file as ```.java``` extension file.
- The generated C header file as ```.h``` extension file.
- The written C code as ```.c``` extension file.


* Before proceeding the next step, please pre-define your paths in your language as pseudo-variables as to easily remember to copy and replace:

---

- I'm using ```macOS``` and have the ```AdoptOpenJDK OpenJ9 JVM 8``` installed, so the full path of the JVM should be:

-> ```JAVA_HOME_PATH```: ```/Library/Java/JavaVirtualMachines/adoptopenjdk8-openj9.jdk/Contents/Home/```


- For Linux users, with the ```AdoptOpenJDK OpenJ9 JVM 8``` installed, the path should be:

-> ```JAVA_HOME_PATH```: ``` \\ ```


- For Windows users, with the ```AdoptOpenJDK OpenJ9 JVM 8``` installed, the path should be:

-> ```JAVA_HOME_PATH```: ``` "%homedrive%\Program Files\Java\ ```

---

- Pick one and you are good to go.

```SYSTEM_TYPE```: 

-> ```darwin``` (for macOS users).

->  ```linux``` (for Linux users).

-> ```windows``` (for Windows users).


---

Please pre-define your ```.c``` extension file name in the current working directory. For example, I created and wrote the code in the filename ```JNI.c```. If you didn't create with that name, please define yours.

INPUT_C_FILE_WITH_EXTENSION: ```The full file name with the extension of the C to be compiled to external library.```


---

Please pre-define your ```dynamic library``` file type as an output file. For example, I'm a macOS user so I need the ```.dylib``` file extension so I will remember ```JNI.dylib```.

If you are a Windows user, you will need a ```.dll``` file extension, so yours will be named ```JNI.dll```.


LIB_OUTPUT_FILE_WITH_EXTENSION: ```The full file name with the extension of the library file want to be loaded in Java code.```


---

Then we use the ```gcc``` compiler tool to compile the C file into a specified library file as the format, please replace your pseudo-variables here (just copy and replace).

```bash
gcc -I${JAVA_HOME_PATH}/include -I${JAVA_HOME_PATH}/include/${SYSTEM_TYPE}  -shared -o ${LIB_OUTPUT_FILE_WITH_EXTENSION} ${INPUT_C_FILE_WITH_EXTENSION}
```


For me, it should be:

```bash
gcc -I$/Library/Java/JavaVirtualMachines/adoptopenjdk8-openj9.jdk/Contents/Home//include -I/Library/Java/JavaVirtualMachines/adoptopenjdk8-openj9.jdk/Contents/Home/include/darwin -shared -o JNI.dylib JNI.c
```

After the compilation from the ```.c``` file, you will have your dynamic library file appeared for now without any error.

--------------

# Step 4 - FINAL: RUN YOUR JAVA CODE WITH THE DYNAMIC LIBRARY FILE!

### Prerequisites:
- The written Java file as ```.java``` extension file.
- The compiled dynamic librar file. For me, it should be: ```JNI.dylib```.


After having gathered the required files, we are nearly done. In this step, we will compile our ```Java code``` into ```Java bytecode``` as ```.class``` extension and use it.

Please use the below command to compile our Java file.
```
javac JNI.java
```

Before having our ```dynamic library``` loaded, we must make sure it is loaded correctly by defining the ```Java library path``` as the ```below JVM Flag```.

```-Djava.library.path=.``` the ```.``` (dot) is marked as the current working directory containing the dynamic library.

If not, the JVM occurs an error of not able to find the satisfied library file that we compiled from C file.

After all, write down the command into the command-line and see if it runs.

```
java -Djava.library.path=. JNI
```

See if your command-line output appears like the below output:

```
Hello world
3
Hello World from void method!
```

Finally, we successfully did the lab.

If you are not done with the lab, you can easily find and run to see the result by ```downloading/cloning this repository``` and run for yourself.

-------------

# Thank you!

![DSC FPTU HCMC](../assets/images/dsc-fptu-hcmc/HOME_PAGE_BANNERS.png)

------------





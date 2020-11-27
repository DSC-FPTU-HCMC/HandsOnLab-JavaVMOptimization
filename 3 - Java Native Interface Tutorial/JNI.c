#include <jni.h>
#include "JNI.h"

/*
 * Class:     JNI
 * Method:    greeting
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_JNI_greeting
  (JNIEnv* env, jobject obj) {
      printf("Hello World from void method!");
      return;
  }

/*
 * Class:     JNI
 * Method:    getMessageFromInputString
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_JNI_getMessageFromInputString
  (JNIEnv* env, jobject obj, jstring s) {
      return s;
  }

/*
 * Class:     JNI
 * Method:    getSumOfTwoNumbers
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_JNI_getSumOfTwoNumbers
  (JNIEnv* env, jobject obj, jint a, jint b) {
      return a+b;
  }
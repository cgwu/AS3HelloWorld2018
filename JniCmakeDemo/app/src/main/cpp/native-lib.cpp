#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_me_gg_jnicmakedemo_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "喂你好, Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_me_gg_jnicmakedemo_JniUtils_getName(JNIEnv *env, jclass type) {
    std::string hello = "张C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jint JNICALL
Java_me_gg_jnicmakedemo_JniUtils_add(JNIEnv *env, jclass type, jint a, jint b) {

    jint c = a + b;
    return c;

}

extern "C"
JNIEXPORT jstring JNICALL
Java_me_gg_jnicmakedemo_JniUtils_saiHi(JNIEnv *env, jclass type, jstring name_) {
    const char *name = env->GetStringUTFChars(name_, 0);

    std::string str(name);
    str = "你好，[" + str + "]，吃早饭了吗?";

    env->ReleaseStringUTFChars(name_, name);

    return env->NewStringUTF(str.c_str());
}
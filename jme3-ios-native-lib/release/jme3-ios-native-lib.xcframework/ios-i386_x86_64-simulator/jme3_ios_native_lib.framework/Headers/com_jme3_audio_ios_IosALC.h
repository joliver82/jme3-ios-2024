/* DO NOT EDIT THIS FILE - it is machine generated */
#include "jni.h"
/* Header for class com_jme3_audio_ios_IosALC */

#ifndef _Included_com_jme3_audio_ios_IosALC
#define _Included_com_jme3_audio_ios_IosALC
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_jme3_audio_ios_IosALC
 * Method:    createALC
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_jme3_audio_ios_IosALC_createALC
  (JNIEnv *, jobject);

/*
 * Class:     com_jme3_audio_ios_IosALC
 * Method:    destroyALC
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_jme3_audio_ios_IosALC_destroyALC
  (JNIEnv *, jobject);

/*
 * Class:     com_jme3_audio_ios_IosALC
 * Method:    isCreated
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_jme3_audio_ios_IosALC_isCreated
  (JNIEnv *, jobject);

/*
 * Class:     com_jme3_audio_ios_IosALC
 * Method:    alcGetString
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_jme3_audio_ios_IosALC_alcGetString
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_jme3_audio_ios_IosALC
 * Method:    alcIsExtensionPresent
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_com_jme3_audio_ios_IosALC_alcIsExtensionPresent
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_jme3_audio_ios_IosALC
 * Method:    alcGetInteger
 * Signature: (ILjava/nio/IntBuffer;I)V
 */
JNIEXPORT void JNICALL Java_com_jme3_audio_ios_IosALC_alcGetInteger
  (JNIEnv *, jobject, jint, jobject, jint);

/*
 * Class:     com_jme3_audio_ios_IosALC
 * Method:    alcDevicePauseSOFT
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_jme3_audio_ios_IosALC_alcDevicePauseSOFT
  (JNIEnv *, jobject);

/*
 * Class:     com_jme3_audio_ios_IosALC
 * Method:    alcDeviceResumeSOFT
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_jme3_audio_ios_IosALC_alcDeviceResumeSOFT
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
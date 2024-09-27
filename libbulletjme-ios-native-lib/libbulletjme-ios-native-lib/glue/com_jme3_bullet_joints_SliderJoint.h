/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_jme3_bullet_joints_SliderJoint */

#ifndef _Included_com_jme3_bullet_joints_SliderJoint
#define _Included_com_jme3_bullet_joints_SliderJoint
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    createJoint
 * Signature: (JJLcom/jme3/math/Vector3f;Lcom/jme3/math/Matrix3f;Lcom/jme3/math/Vector3f;Lcom/jme3/math/Matrix3f;Z)J
 */
JNIEXPORT jlong JNICALL Java_com_jme3_bullet_joints_SliderJoint_createJoint
  (JNIEnv *, jclass, jlong, jlong, jobject, jobject, jobject, jobject, jboolean);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    createJoint1
 * Signature: (JLcom/jme3/math/Vector3f;Lcom/jme3/math/Matrix3f;Z)J
 */
JNIEXPORT jlong JNICALL Java_com_jme3_bullet_joints_SliderJoint_createJoint1
  (JNIEnv *, jclass, jlong, jobject, jobject, jboolean);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getDampingDirAng
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getDampingDirAng
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getDampingDirLin
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getDampingDirLin
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getDampingLimAng
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getDampingLimAng
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getDampingLimLin
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getDampingLimLin
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getDampingOrthoAng
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getDampingOrthoAng
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getDampingOrthoLin
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getDampingOrthoLin
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getFrameOffsetA
 * Signature: (JLcom/jme3/math/Transform;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_getFrameOffsetA
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getFrameOffsetB
 * Signature: (JLcom/jme3/math/Transform;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_getFrameOffsetB
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getLowerAngLimit
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getLowerAngLimit
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getLowerLinLimit
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getLowerLinLimit
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getMaxAngMotorForce
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getMaxAngMotorForce
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getMaxLinMotorForce
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getMaxLinMotorForce
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getRestitutionDirAng
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getRestitutionDirAng
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getRestitutionDirLin
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getRestitutionDirLin
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getRestitutionLimAng
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getRestitutionLimAng
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getRestitutionLimLin
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getRestitutionLimLin
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getRestitutionOrthoAng
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getRestitutionOrthoAng
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getRestitutionOrthoLin
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getRestitutionOrthoLin
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getSoftnessDirAng
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getSoftnessDirAng
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getSoftnessDirLin
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getSoftnessDirLin
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getSoftnessLimAng
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getSoftnessLimAng
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getSoftnessLimLin
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getSoftnessLimLin
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getSoftnessOrthoAng
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getSoftnessOrthoAng
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getSoftnessOrthoLin
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getSoftnessOrthoLin
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getTargetAngMotorVelocity
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getTargetAngMotorVelocity
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getTargetLinMotorVelocity
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getTargetLinMotorVelocity
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getUpperAngLimit
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getUpperAngLimit
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    getUpperLinLimit
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_joints_SliderJoint_getUpperLinLimit
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    isPoweredAngMotor
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_jme3_bullet_joints_SliderJoint_isPoweredAngMotor
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    isPoweredLinMotor
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_jme3_bullet_joints_SliderJoint_isPoweredLinMotor
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setDampingDirAng
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setDampingDirAng
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setDampingDirLin
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setDampingDirLin
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setDampingLimAng
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setDampingLimAng
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setDampingLimLin
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setDampingLimLin
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setDampingOrthoAng
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setDampingOrthoAng
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setDampingOrthoLin
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setDampingOrthoLin
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setLowerAngLimit
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setLowerAngLimit
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setLowerLinLimit
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setLowerLinLimit
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setMaxAngMotorForce
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setMaxAngMotorForce
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setMaxLinMotorForce
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setMaxLinMotorForce
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setPoweredAngMotor
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setPoweredAngMotor
  (JNIEnv *, jclass, jlong, jboolean);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setPoweredLinMotor
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setPoweredLinMotor
  (JNIEnv *, jclass, jlong, jboolean);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setRestitutionDirAng
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setRestitutionDirAng
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setRestitutionDirLin
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setRestitutionDirLin
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setRestitutionLimAng
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setRestitutionLimAng
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setRestitutionLimLin
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setRestitutionLimLin
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setRestitutionOrthoAng
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setRestitutionOrthoAng
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setRestitutionOrthoLin
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setRestitutionOrthoLin
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setSoftnessDirAng
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setSoftnessDirAng
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setSoftnessDirLin
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setSoftnessDirLin
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setSoftnessLimAng
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setSoftnessLimAng
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setSoftnessLimLin
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setSoftnessLimLin
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setSoftnessOrthoAng
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setSoftnessOrthoAng
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setSoftnessOrthoLin
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setSoftnessOrthoLin
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setTargetAngMotorVelocity
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setTargetAngMotorVelocity
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setTargetLinMotorVelocity
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setTargetLinMotorVelocity
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setUpperAngLimit
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setUpperAngLimit
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     com_jme3_bullet_joints_SliderJoint
 * Method:    setUpperLinLimit
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_joints_SliderJoint_setUpperLinLimit
  (JNIEnv *, jclass, jlong, jfloat);

#ifdef __cplusplus
}
#endif
#endif

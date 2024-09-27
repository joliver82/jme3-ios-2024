/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_jme3_bullet_MultiBodyLink */

#ifndef _Included_com_jme3_bullet_MultiBodyLink
#define _Included_com_jme3_bullet_MultiBodyLink
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    addConstraintForce
 * Signature: (JLcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_addConstraintForce
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    addConstraintTorque
 * Signature: (JLcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_addConstraintTorque
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    addForce
 * Signature: (JLcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_addForce
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    addJointTorque
 * Signature: (JIF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_addJointTorque
  (JNIEnv *, jclass, jlong, jint, jfloat);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    addTorque
 * Signature: (JLcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_addTorque
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getAppliedForce
 * Signature: (JLcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_getAppliedForce
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getAppliedTorque
 * Signature: (JLcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_getAppliedTorque
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getAxisBottom
 * Signature: (JILcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_getAxisBottom
  (JNIEnv *, jclass, jlong, jint, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getAxisTop
 * Signature: (JILcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_getAxisTop
  (JNIEnv *, jclass, jlong, jint, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getCollider
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_com_jme3_bullet_MultiBodyLink_getCollider
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getConstraintForce
 * Signature: (JLcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_getConstraintForce
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getConstraintTorque
 * Signature: (JLcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_getConstraintTorque
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getDofCount
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_jme3_bullet_MultiBodyLink_getDofCount
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getDVector
 * Signature: (JLcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_getDVector
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getEVector
 * Signature: (JLcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_getEVector
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getFlags
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_jme3_bullet_MultiBodyLink_getFlags
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getInertiaLocal
 * Signature: (JLcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_getInertiaLocal
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getJointPos
 * Signature: (JI)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_MultiBodyLink_getJointPos
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getJointTorque
 * Signature: (JI)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_MultiBodyLink_getJointTorque
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getJointType
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_jme3_bullet_MultiBodyLink_getJointType
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getJointVel
 * Signature: (JII)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_MultiBodyLink_getJointVel
  (JNIEnv *, jclass, jlong, jint, jint);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getLinkId
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_com_jme3_bullet_MultiBodyLink_getLinkId
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getMass
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_jme3_bullet_MultiBodyLink_getMass
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getParent2LinkRotation
 * Signature: (JLcom/jme3/math/Quaternion;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_getParent2LinkRotation
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getParentIndex
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_jme3_bullet_MultiBodyLink_getParentIndex
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getPosVarCount
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_jme3_bullet_MultiBodyLink_getPosVarCount
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getQ0Parent2LinkRotation
 * Signature: (JLcom/jme3/math/Quaternion;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_getQ0Parent2LinkRotation
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    getWorldTransform
 * Signature: (JLcom/jme3/math/Transform;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_getWorldTransform
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    localFrameToWorld
 * Signature: (JILcom/jme3/math/Matrix3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_localFrameToWorld
  (JNIEnv *, jclass, jlong, jint, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    localPosToWorld
 * Signature: (JILcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_localPosToWorld
  (JNIEnv *, jclass, jlong, jint, jobject);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    setCollider
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_setCollider
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    setJointPos
 * Signature: (JIIF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_setJointPos
  (JNIEnv *, jclass, jlong, jint, jint, jfloat);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    setJointVel
 * Signature: (JIIF)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_setJointVel
  (JNIEnv *, jclass, jlong, jint, jint, jfloat);

/*
 * Class:     com_jme3_bullet_MultiBodyLink
 * Method:    worldPosToLocal
 * Signature: (JILcom/jme3/math/Vector3f;)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_MultiBodyLink_worldPosToLocal
  (JNIEnv *, jclass, jlong, jint, jobject);

#ifdef __cplusplus
}
#endif
#endif
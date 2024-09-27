/*
 * Copyright (c) 2009-2012 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Author: Normen Hansen
 */
#include "com_jme3_bullet_collision_shapes_SimplexCollisionShape.h"
#include "jmeBulletUtil.h"

/*
 * Class:     com_jme3_bullet_collision_shapes_SimplexCollisionShape
 * Method:    createShape
 * Signature: (Lcom/jme3/math/Vector3f;)J
 */
JNIEXPORT jlong JNICALL Java_com_jme3_bullet_collision_shapes_SimplexCollisionShape_createShape__Lcom_jme3_math_Vector3f_2
(JNIEnv *pEnv, jclass, jobject vector1) {
    jmeClasses::initJavaClasses(pEnv);

    btVector3 vec1;
    jmeBulletUtil::convert(pEnv, vector1, &vec1);
    EXCEPTION_CHK(pEnv, 0);

    btBU_Simplex1to4* simplexShape = new btBU_Simplex1to4(vec1); //dance016
    return reinterpret_cast<jlong> (simplexShape);
}

/*
 * Class:     com_jme3_bullet_collision_shapes_SimplexCollisionShape
 * Method:    createShape
 * Signature: (Lcom/jme3/math/Vector3f;Lcom/jme3/math/Vector3f;)J
 */
JNIEXPORT jlong JNICALL Java_com_jme3_bullet_collision_shapes_SimplexCollisionShape_createShape__Lcom_jme3_math_Vector3f_2Lcom_jme3_math_Vector3f_2
(JNIEnv *pEnv, jclass, jobject vector1, jobject vector2) {
    jmeClasses::initJavaClasses(pEnv);

    btVector3 vec1;
    jmeBulletUtil::convert(pEnv, vector1, &vec1);
    EXCEPTION_CHK(pEnv, 0);

    btVector3 vec2;
    jmeBulletUtil::convert(pEnv, vector2, &vec2);
    EXCEPTION_CHK(pEnv, 0);

    btBU_Simplex1to4*
            simplexShape = new btBU_Simplex1to4(vec1, vec2); //dance016
    return reinterpret_cast<jlong> (simplexShape);
}

/*
 * Class:     com_jme3_bullet_collision_shapes_SimplexCollisionShape
 * Method:    createShape
 * Signature: (Lcom/jme3/math/Vector3f;Lcom/jme3/math/Vector3f;Lcom/jme3/math/Vector3f;)J
 */
JNIEXPORT jlong JNICALL Java_com_jme3_bullet_collision_shapes_SimplexCollisionShape_createShape__Lcom_jme3_math_Vector3f_2Lcom_jme3_math_Vector3f_2Lcom_jme3_math_Vector3f_2
(JNIEnv *pEnv, jclass, jobject vector1, jobject vector2, jobject vector3) {
    jmeClasses::initJavaClasses(pEnv);

    btVector3 vec1;
    jmeBulletUtil::convert(pEnv, vector1, &vec1);
    EXCEPTION_CHK(pEnv, 0);

    btVector3 vec2;
    jmeBulletUtil::convert(pEnv, vector2, &vec2);
    EXCEPTION_CHK(pEnv, 0);

    btVector3 vec3;
    jmeBulletUtil::convert(pEnv, vector3, &vec3);
    EXCEPTION_CHK(pEnv, 0);

    btBU_Simplex1to4*
            simplexShape = new btBU_Simplex1to4(vec1, vec2, vec3); //dance016
    return reinterpret_cast<jlong> (simplexShape);
}

/*
 * Class:     com_jme3_bullet_collision_shapes_SimplexCollisionShape
 * Method:    createShape
 * Signature: (Lcom/jme3/math/Vector3f;Lcom/jme3/math/Vector3f;Lcom/jme3/math/Vector3f;Lcom/jme3/math/Vector3f;)J
 */
JNIEXPORT jlong JNICALL Java_com_jme3_bullet_collision_shapes_SimplexCollisionShape_createShape__Lcom_jme3_math_Vector3f_2Lcom_jme3_math_Vector3f_2Lcom_jme3_math_Vector3f_2Lcom_jme3_math_Vector3f_2
(JNIEnv *pEnv, jclass, jobject vector1, jobject vector2, jobject vector3,
        jobject vector4) {
    jmeClasses::initJavaClasses(pEnv);

    btVector3 vec1;
    jmeBulletUtil::convert(pEnv, vector1, &vec1);
    EXCEPTION_CHK(pEnv, 0);

    btVector3 vec2;
    jmeBulletUtil::convert(pEnv, vector2, &vec2);
    EXCEPTION_CHK(pEnv, 0);

    btVector3 vec3;
    jmeBulletUtil::convert(pEnv, vector3, &vec3);
    EXCEPTION_CHK(pEnv, 0);

    btVector3 vec4;
    jmeBulletUtil::convert(pEnv, vector4, &vec4);
    EXCEPTION_CHK(pEnv, 0);

    btBU_Simplex1to4*
            simplexShape = new btBU_Simplex1to4(vec1, vec2, vec3, vec4); //dance016
    return reinterpret_cast<jlong> (simplexShape);
}

/*
 * Class:     com_jme3_bullet_collision_shapes_SimplexCollisionShape
 * Method:    recalcAabb
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_collision_shapes_SimplexCollisionShape_recalcAabb
(JNIEnv *pEnv, jclass, jlong shapeId) {
    btBU_Simplex1to4 *pShape
            = reinterpret_cast<btBU_Simplex1to4 *> (shapeId);
    NULL_CHK(pEnv, pShape, "The btBU_Simplex1to4 does not exist.",);
    ASSERT_CHK(pEnv, pShape->getShapeType() == TETRAHEDRAL_SHAPE_PROXYTYPE,);

    pShape->recalcLocalAabb();
}

/*
 * Copyright (c) 2022-2024 jMonkeyEngine
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
 * Author: Stephen Gold
 */
#include "com_jme3_bullet_DeformableSpace.h"
#include "jmeBulletUtil.h"
#include "jmeDeformableSpace.h"
#include "jmeUserInfo.h"
#include "BulletSoftBody/btDeformableMultiBodyDynamicsWorld.h"

/*
 * Class:     com_jme3_bullet_DeformableSpace
 * Method:    addSoftBody
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_DeformableSpace_addSoftBody
(JNIEnv *pEnv, jclass, jlong spaceId, jlong softBodyId) {
    jmeDeformableSpace * const
            pSpace = reinterpret_cast<jmeDeformableSpace *> (spaceId);
    NULL_CHK(pEnv, pSpace, "The physics space does not exist.",)
    btDeformableMultiBodyDynamicsWorld * const
            pWorld = pSpace->getDeformableWorld();
    NULL_CHK(pEnv, pWorld, "The deformable world does not exist.",);
    ASSERT_CHK(pEnv, pWorld->getWorldType() == BT_DEFORMABLE_MULTIBODY_DYNAMICS_WORLD,);

    btSoftBody * const
            pSoftBody = reinterpret_cast<btSoftBody *> (softBodyId);
    NULL_CHK(pEnv, pSoftBody, "The collision object does not exist.",)
    ASSERT_CHK(pEnv, pSoftBody->getInternalType() & btCollisionObject::CO_SOFT_BODY,);

    jmeUserPointer const pUser = (jmeUserPointer) pSoftBody->getUserPointer();
    NULL_CHK(pEnv, pUser, "The user object does not exist.",)
    ASSERT_CHK(pEnv, pUser->m_jmeSpace == NULL,);
    pUser->m_jmeSpace = pSpace;

    pWorld->addSoftBody(pSoftBody);
}

/*
 * Class:     com_jme3_bullet_DeformableSpace
 * Method:    createSpace
 * Signature: (Lcom/jme3/math/Vector3f;Lcom/jme3/math/Vector3f;IJ)J
 */
JNIEXPORT jlong JNICALL Java_com_jme3_bullet_DeformableSpace_createSpace
(JNIEnv *pEnv, jobject object, jobject minVector, jobject maxVector,
        jint broadphase, jlong infoId) {
    jmeClasses::initJavaClasses(pEnv);

    NULL_CHK(pEnv, minVector, "The min vector does not exist.", 0)
    btVector3 min;
    jmeBulletUtil::convert(pEnv, minVector, &min);
    EXCEPTION_CHK(pEnv, 0);

    NULL_CHK(pEnv, maxVector, "The max vector does not exist.", 0)
    btVector3 max;
    jmeBulletUtil::convert(pEnv, maxVector, &max);
    EXCEPTION_CHK(pEnv, 0);

    jmeDeformableSpace * const
            pSpace = new jmeDeformableSpace(pEnv, object); //dance003

    const btDefaultCollisionConstructionInfo * const pInfo
            = reinterpret_cast<btDefaultCollisionConstructionInfo *> (infoId);
    NULL_CHK(pEnv, pInfo, "The construction info does not exist.", 0)

    pSpace->createDeformableSpace(min, max, (int)broadphase, pInfo);

    return reinterpret_cast<jlong> (pSpace);
}

/*
 * Class:     com_jme3_bullet_DeformableSpace
 * Method:    getNumSoftBodies
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_jme3_bullet_DeformableSpace_getNumSoftBodies
(JNIEnv *pEnv, jclass, jlong spaceId) {
    const jmeDeformableSpace * const
            pSpace = reinterpret_cast<jmeDeformableSpace *> (spaceId);
    NULL_CHK(pEnv, pSpace, "The physics space does not exist.", 0);
    const btDeformableMultiBodyDynamicsWorld * const
            pWorld = pSpace->getDeformableWorld();
    NULL_CHK(pEnv, pWorld, "The deformable world does not exist.", 0);
    ASSERT_CHK(pEnv, pWorld->getWorldType() == BT_DEFORMABLE_MULTIBODY_DYNAMICS_WORLD, 0);

    const btSoftBodyArray& softBodies = pWorld->getSoftBodyArray();
    int sz = softBodies.size();
    return (jint) sz;
}

/*
 * Class:     com_jme3_bullet_DeformableSpace
 * Method:    getWorldInfo
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_jme3_bullet_DeformableSpace_getWorldInfo
(JNIEnv *pEnv, jclass, jlong spaceId) {
    jmeDeformableSpace * const
            pSpace = reinterpret_cast<jmeDeformableSpace *> (spaceId);
    NULL_CHK(pEnv, pSpace, "The physics space does not exist.", 0)
    btDeformableMultiBodyDynamicsWorld * const
            pWorld = pSpace->getDeformableWorld();
    NULL_CHK(pEnv, pWorld, "The deformable world does not exist.", 0);
    ASSERT_CHK(pEnv, pWorld->getWorldType() == BT_DEFORMABLE_MULTIBODY_DYNAMICS_WORLD, 0);

    btSoftBodyWorldInfo *pWorldInfo = &(pWorld->getWorldInfo());
    return reinterpret_cast<jlong> (pWorldInfo);
}

/*
 * Class:     com_jme3_bullet_DeformableSpace
 * Method:    removeSoftBody
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_com_jme3_bullet_DeformableSpace_removeSoftBody
(JNIEnv *pEnv, jclass, jlong spaceId, jlong softBodyId) {
    jmeDeformableSpace * const
            pSpace = reinterpret_cast<jmeDeformableSpace *> (spaceId);
    NULL_CHK(pEnv, pSpace, "The physics space does not exist.",)
    btDeformableMultiBodyDynamicsWorld * const
            pWorld = pSpace->getDeformableWorld();
    NULL_CHK(pEnv, pWorld, "The deformable world does not exist.",);
    ASSERT_CHK(pEnv, pWorld->getWorldType() == BT_DEFORMABLE_MULTIBODY_DYNAMICS_WORLD,);

    btSoftBody * const pSoftBody = reinterpret_cast<btSoftBody *> (softBodyId);
    NULL_CHK(pEnv, pSoftBody, "The collision object does not exist.",)
    ASSERT_CHK(pEnv, pSoftBody->getInternalType() & btCollisionObject::CO_SOFT_BODY,);

    jmeUserPointer const pUser = (jmeUserPointer) pSoftBody->getUserPointer();
    NULL_CHK(pEnv, pUser, "The user object does not exist.",)
    ASSERT_CHK(pEnv, pUser->m_jmeSpace == pSpace,);
    pUser->m_jmeSpace = NULL;

    pWorld->removeSoftBody(pSoftBody);
}

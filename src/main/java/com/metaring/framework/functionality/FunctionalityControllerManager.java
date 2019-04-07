/**
 *    Copyright 2019 MetaRing s.r.l.
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.metaring.framework.functionality;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

import com.metaring.framework.Core;
import com.metaring.framework.SysKB;
import com.metaring.framework.type.DataRepresentation;

@SuppressWarnings("unchecked")
final class FunctionalityControllerManager {

    private static final String CFG_FUNCTIONALITY = "functionality";
    private static final String CFG_CONTROLLER = "controller";

    static final Executor INSTANCE;

    static {
        SysKB sysKB = Core.SYSKB;
        if (sysKB == null) {
            INSTANCE = ForkJoinPool.commonPool();
        }
        else {
            if (!sysKB.hasProperty(CFG_FUNCTIONALITY)) {
                INSTANCE = ForkJoinPool.commonPool();
            }
            else {
                DataRepresentation functionalityDataRepresentation = sysKB.get(CFG_FUNCTIONALITY);
                if (!functionalityDataRepresentation.hasProperty(CFG_CONTROLLER)) {
                    INSTANCE = ForkJoinPool.commonPool();
                }
                else {
                    String className = functionalityDataRepresentation.getText(CFG_CONTROLLER);
                    Class<? extends FunctionalityController> clazz = null;
                    try {
                        clazz = (Class<? extends FunctionalityController>) Class.forName(className);
                    }
                    catch (ClassNotFoundException e) {
                        throw new IllegalArgumentException("An error occurred while creating Functionality Controller class " + className, e);
                    }
                    try {
                        INSTANCE = clazz.newInstance().init(sysKB);
                    }
                    catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
                        throw new IllegalArgumentException("An error occurred while trying to access to Functionality Controller init " + className, e);
                    }
                }
            }
        }
    }
}

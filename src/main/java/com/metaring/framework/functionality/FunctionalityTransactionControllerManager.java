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

import java.util.concurrent.CompletableFuture;

import com.metaring.framework.Core;
import com.metaring.framework.SysKB;
import com.metaring.framework.type.DataRepresentation;

@SuppressWarnings("unchecked")
public class FunctionalityTransactionControllerManager {
    private static final String CFG_PERSISTENCE = "persistence";
    private static final String CFG_CONTROLLER = "controller";

    private static final Class<? extends FunctionalityTransactionController> INSTANCE;

    static {
        SysKB sysKB = Core.SYSKB;
        if (sysKB == null) {
            INSTANCE = null;
        }
        else {
            if (!sysKB.hasProperty(CFG_PERSISTENCE)) {
                INSTANCE = null;
            }
            else {
                DataRepresentation functionalityDataRepresentation = sysKB.get(CFG_PERSISTENCE);
                if (!functionalityDataRepresentation.hasProperty(CFG_CONTROLLER)) {
                    INSTANCE = null;
                }
                else {
                    String className = functionalityDataRepresentation.getText(CFG_CONTROLLER);
                    Class<? extends FunctionalityTransactionController> clazz = null;
                    try {
                        clazz = (Class<? extends FunctionalityTransactionController>) Class.forName(className);
                    }
                    catch (ClassNotFoundException e) {
                        throw new IllegalArgumentException("An error occurred while creating Functionality Transaction Controller class " + className, e);
                    }
                    INSTANCE = clazz;

                }
            }
        }
    }

    static final CompletableFuture<FunctionalityTransactionController> createInstance() {
        try {
            if(INSTANCE == null) {
                return CompletableFuture.completedFuture(null);
            }
            return INSTANCE.newInstance().init(Core.SYSKB, FunctionalityControllerManager.INSTANCE);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            throw new IllegalArgumentException("An error occurred while trying to access to Functionality Transaction Controller init " + INSTANCE.getName(), e);
        }
    }
}

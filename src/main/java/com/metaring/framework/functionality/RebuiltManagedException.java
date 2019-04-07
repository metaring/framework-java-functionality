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

import java.lang.reflect.Field;
import com.metaring.framework.exception.ManagedException;

public class RebuiltManagedException extends ManagedException {
    private static Field NAME;
    private static Field PAYLOAD;
    private static final long serialVersionUID = -1273102889502939013L;

    public RebuiltManagedException(FunctionalityStepErrorData functionalityStepErrorData) {
        super(functionalityStepErrorData.getMessage());
        this.setData(functionalityStepErrorData);
    }

    private final void setData(FunctionalityStepErrorData functionalityStepErrorData) {
        try {
            NAME.set((Object) this, (Object) functionalityStepErrorData.getName());
            PAYLOAD.set((Object) this, (Object) functionalityStepErrorData.getPayload());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            NAME = ManagedException.class.getDeclaredField("name");
            NAME.setAccessible(true);
            PAYLOAD = ManagedException.class.getDeclaredField("payload");
            PAYLOAD.setAccessible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

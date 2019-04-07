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
import java.util.HashMap;
import java.util.Map;

import com.metaring.framework.util.StringUtil;

public class FunctionalitiesInfoProvider {
    private static final Map<String, FunctionalityInfo> functionalitiesInfo = new HashMap<String, FunctionalityInfo>();

    public static FunctionalityInfo get(String functionalityFullyQualifiedName) throws FunctionalityNotFoundException {
        FunctionalityInfo functionality;
        String functionalityName = functionalityFullyQualifiedName;
        if (!functionalitiesInfo.containsKey(functionalityName)) {
            FunctionalitiesInfoProvider.retrieveFunctionality(functionalityName);
        }
        if ((functionality = functionalitiesInfo.get(functionalityName)) == null) {
            throw new FunctionalityNotFoundException(functionalityFullyQualifiedName);
        }
        return functionality;
    }

    private static void retrieveFunctionality(String functionalityName) {
        try {
            Class<?> managerClassName = FunctionalitiesInfoProvider.getManagerClass(functionalityName);
            String functionalityFieldName = functionalityName.substring(functionalityName.lastIndexOf(".") + 1);
            functionalityFieldName = StringUtil.toStaticFieldName((String) functionalityFieldName);
            Field functionalityField = managerClassName.getDeclaredField(functionalityFieldName);
            FunctionalityInfo functionalityInfo = (FunctionalityInfo) functionalityField.get(null);
            functionalitiesInfo.put(functionalityName, functionalityInfo);
        }
        catch (Exception e) {
        }
    }

    private static Class<?> getManagerClass(String call) {
        String managerClassName = call.substring(0, call.lastIndexOf("."));
        String managerName = StringUtil.firstLetterToUpperCase((String) managerClassName.substring(managerClassName.lastIndexOf(".") + 1));
        managerClassName = managerClassName + "." + managerName + "FunctionalitiesManager";
        Class<?> managerClass = null;
        try {
            managerClass = Class.forName(managerClassName);
        }
        catch (Exception e) {
        }
        return managerClass;
    }
}

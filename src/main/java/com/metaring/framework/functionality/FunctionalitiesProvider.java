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

@SuppressWarnings("unchecked")
public final class FunctionalitiesProvider {

    private static final Map<String, Class<? extends Functionality>> CACHE = new HashMap<>();
    private static final Map<String, AbstractFunctionality> INJECTIONS = new HashMap<>();
    private static final Map<String, AbstractFunctionality> CLASSES_DICTIONARY = new HashMap<>();

    public static final AbstractFunctionality getFunctionality(FunctionalityInfo functionalityInfo, Class<? extends Functionality> functionalityClass) throws FunctionalityCreationException {
        if(!INJECTIONS.containsKey(functionalityInfo.getFunctionalityFullyQualifiedName())) {
            return createFunctionality(functionalityInfo, functionalityClass);
        }
        return INJECTIONS.get(functionalityInfo.getFunctionalityFullyQualifiedName());
    }

    public static final AbstractFunctionality getFunctionalityByClassName(String className) {
        return CLASSES_DICTIONARY.get(className);
    }

    private static final AbstractFunctionality createFunctionality(FunctionalityInfo functionalityInfo, Class<? extends Functionality> functionalityClass) throws FunctionalityCreationException {
        try {
            functionalityClass = FunctionalitiesProvider.load(functionalityInfo.getFunctionalityFullyQualifiedName(), functionalityClass);
            Field instanceField = functionalityClass.getDeclaredField("INSTANCE");
            instanceField.setAccessible(true);
            AbstractFunctionality functionality = (AbstractFunctionality) instanceField.get(null);
            INJECTIONS.put(functionalityInfo.getFunctionalityFullyQualifiedName(), functionality);
            CLASSES_DICTIONARY.put(functionality.getClass().getName(), functionality);
            return functionality;
        } catch (Exception e) {
            throw new FunctionalityCreationException(functionalityInfo.getFunctionalityFullyQualifiedName(), e);
        }
    }

    private static final Class<? extends Functionality> load(String functionalityName, Class<? extends Functionality> functionalityClass) throws FunctionalityNotFoundException {
        if (!CACHE.containsKey(functionalityName)) {
            if (functionalityClass == null) {
                String className = functionalityName.substring(0, functionalityName.lastIndexOf(".") + 1);
                className = className + StringUtil.firstLetterToUpperCase((String) functionalityName.substring(functionalityName.lastIndexOf(".") + 1));
                className = className + "Functionality";
                try {
                    functionalityClass = (Class<? extends Functionality>) Class.forName(className);
                } catch (ClassNotFoundException e) {
                    throw new FunctionalityNotFoundException(functionalityName, e);
                }
            }
            CACHE.put(functionalityName, functionalityClass);
        }
        return functionalityClass != null ? functionalityClass : CACHE.get(functionalityName);
    }
}

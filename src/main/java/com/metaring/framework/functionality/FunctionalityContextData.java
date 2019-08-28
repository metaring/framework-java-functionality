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

import java.util.HashMap;

public class FunctionalityContextData extends HashMap<String, Object> {

    private static final long serialVersionUID = 3777454499159654269L;

    public FunctionalityContextData() {
        super();
    }

    public void putSafely(String key, Object value) {
        String keyString = key;
        if (super.containsKey(keyString)) {
            throw new RuntimeException("Data " + keyString + " already exists in context and has value: " + super.get(keyString).toString());
        }
        super.put(keyString, value);
    }

    public Object removeSafely(String key) {
        String keyString = key;
        if (!super.containsKey(keyString)) {
            throw new RuntimeException("Context does not contains data with key " + keyString);
        }
        return super.remove(keyString);
    }

    public Boolean exists(String key) {
        return super.containsKey(key);
    }

    public void purge() {
        super.clear();
    }
}
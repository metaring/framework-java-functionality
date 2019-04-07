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
import java.util.Map;

public class FunctionalityContextData {
    private final Map<String, Object> data = new HashMap<String, Object>();

    public FunctionalityContextData() {
    }

    public Object put(String key, Object value) {
        return this.data.put(key, value);
    }

    public void putSafely(String key, Object value) {
        String keyString = key;
        if (this.data.containsKey(keyString)) {
            throw new RuntimeException("Data " + keyString + " already exists in context and has value: " + this.data.get(keyString).toString());
        }
        this.data.put(keyString, value);
    }

    public Object remove(String key) {
        return this.data.remove(key);
    }

    public void removeSafely(String key) {
        String keyString = key;
        if (!this.data.containsKey(keyString)) {
            throw new RuntimeException("Context does not contains data with key " + keyString);
        }
        this.data.remove(keyString);
    }

    public Object get(String key) {
        return this.data.get(key);
    }

    public Boolean exists(String key) {
        return this.data.containsKey(key);
    }

    public void purge() {
        this.data.clear();
    }
}
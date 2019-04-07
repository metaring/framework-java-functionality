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

public class CriticalMissingValuesException extends UnmanagedException {
    private static final long serialVersionUID = 6006438390078449639L;

    public CriticalMissingValuesException(FunctionalityStackElementSeries functionalityStackElementSeries, String... values) {
        super(functionalityStackElementSeries, CriticalMissingValuesException.printValues(values));
    }

    private static final String printValues(String... values) {
        if (values == null || values.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; ++i) {
            sb.append(values[i]);
            if (i >= values.length - 1)
                continue;
            sb.append(", ");
        }
        return sb.toString();
    }
}

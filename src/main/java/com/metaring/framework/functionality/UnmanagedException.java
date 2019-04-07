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

import com.metaring.framework.exception.CoreException;
import com.metaring.framework.util.StringUtil;

public abstract class UnmanagedException extends CoreException {
    private static final long serialVersionUID = 4932361294171682239L;
    public static final String EXCEPTION_START = "STACK:\n\n\t";
    public static final String EXCEPTION_END = "\n\n]EXCEPTION END[";

    public UnmanagedException(FunctionalityStackElementSeries functionalityStackElementSeries, String message) {
        super(UnmanagedException.getExceptionHeader(functionalityStackElementSeries, message, -1));
    }

    public UnmanagedException(FunctionalityStackElementSeries functionalityStackElementSeries, String message, int row) {
        super(UnmanagedException.getExceptionHeader(functionalityStackElementSeries, message, row));
    }

    private static final String getExceptionHeader(FunctionalityStackElementSeries functionalityStackElementSeries, String message, int row) {
        if (message != null && message.contains((CharSequence) EXCEPTION_START)) {
            return message.substring(message.indexOf(": ") + 1);
        }
        StringBuilder stringBuilder = new StringBuilder(EXCEPTION_START);
        for (int i = 0; i < functionalityStackElementSeries.size(); i++) {
            FunctionalityStackElement element = functionalityStackElementSeries.get(i);
            stringBuilder.append(element.getName()).append(" -> ").append(element.getStep().getName());
            if (i < functionalityStackElementSeries.size() - 1) {
                stringBuilder.append("\n\t\t");
                for (long l = 0; l < i; ++l) {
                    stringBuilder.append("\t");
                }
                stringBuilder.append("-> ");
            }
            if (i != functionalityStackElementSeries.size() - 1)
                continue;
            stringBuilder.append("\n\nTHROW POINT:\n\n    ");
            String name = element.getName();
            if (name.contains((CharSequence) ".")) {
                name = name.substring(0, name.lastIndexOf(".")) + "." + StringUtil.firstLetterToUpperCase((String) name.substring(name.lastIndexOf(".") + 1));
                name = name + "FunctionalityImpl";
            }
            stringBuilder.append(name).append(".").append(StringUtil.toCamelCase((String) element.getStep().getName()));
            stringBuilder.append("(").append(name.substring(name.lastIndexOf(".") + 1));
            stringBuilder.append(".java");
            if (row > -1) {
                stringBuilder.append(":").append(row);
            }
            stringBuilder.append(")");
        }
        stringBuilder.append("\n\n").append("MESSAGE:\n");
        if (message != null) {
            stringBuilder.append(message);
        }
        stringBuilder.append(EXCEPTION_END).append("\n\n");
        return stringBuilder.toString();
    }
}

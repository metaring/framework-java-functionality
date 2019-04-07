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

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.type.series.TextSeries;

public class FunctionalityExecutionError extends AbstractFunctionalityExecutionError {

    private static final String INTERNAL_PLATFORM_ERROR = "InternalPlatformError";

    protected FunctionalityExecutionError(FunctionalityStackElementSeries functionalityStackElementSeries, FunctionalityStepErrorData errorData) {
        super(functionalityStackElementSeries, errorData);
    }

    public static FunctionalityExecutionError fromJson(String jsonString) {

        if (jsonString == null) {
            return null;
        }

        jsonString = jsonString.trim();
        if (jsonString.isEmpty()) {
            return null;
        }
        if (jsonString.equalsIgnoreCase("null")) {
            return null;
        }

        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.fromJson(jsonString);

        FunctionalityStackElementSeries stack = null;
        if (dataRepresentation.hasProperty("stack")) {
            stack = dataRepresentation.get("stack", FunctionalityStackElementSeries.class);
        }

        FunctionalityStepErrorData errorData = null;
        if (dataRepresentation.hasProperty("errorData")) {
            errorData = dataRepresentation.get("errorData", FunctionalityStepErrorData.class);
        } else if (dataRepresentation.hasProperty("codeName")) {
            errorData = FunctionalityStepErrorData.create(FunctionalityErrorTypeEnumerator.MANAGED, dataRepresentation.getText("codeName"), dataRepresentation.getTextSeries("payload"), null, -1l);
        }

        FunctionalityExecutionError functionalityExecutionError = create(stack, errorData);
        return functionalityExecutionError;
    }

    @Override
    public DataRepresentation toDataRepresentation() {
        TextSeries payload;
        FunctionalityStepErrorData errorData = this.getErrorData();
        if (errorData == null) {
            return Tools.FACTORY_DATA_REPRESENTATION.fromJson("null");
        }
        String name = errorData.getName();
        FunctionalityErrorTypeEnumerator type = errorData.getType();
        if (name == null || type == FunctionalityErrorTypeEnumerator.UNMANAGED) {
            name = INTERNAL_PLATFORM_ERROR;
        }
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        dataRepresentation.add("codeName", name);
        if (type == FunctionalityErrorTypeEnumerator.MANAGED && (payload = errorData.getPayload()) != null) {
            dataRepresentation.add("payload", payload);
        }
        return dataRepresentation;
    }
}
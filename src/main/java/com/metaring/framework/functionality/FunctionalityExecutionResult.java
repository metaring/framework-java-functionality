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

public class FunctionalityExecutionResult extends AbstractFunctionalityExecutionResult {

    protected FunctionalityExecutionResult(FunctionalityExecutionVerdictEnumerator verdict, FunctionalityExecutionWarningDataSeries warningData, FunctionalityExecutionError errorData, String resultIdentificator, DataRepresentation result) {
        super(verdict, warningData, errorData, resultIdentificator, result);
    }

    public static FunctionalityExecutionResult fromJson(String jsonString) {

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

        FunctionalityExecutionVerdictEnumerator verdict = null;
        if (dataRepresentation.hasProperty("verdict")) {
            verdict = dataRepresentation.get("verdict", FunctionalityExecutionVerdictEnumerator.class);
        }

        FunctionalityExecutionWarningDataSeries warningData = null;
        if (dataRepresentation.hasProperty("warningData")) {
            warningData = dataRepresentation.get("warningData", FunctionalityExecutionWarningDataSeries.class);
        }

        FunctionalityExecutionError errorData = null;
        if (dataRepresentation.hasProperty("errorData")) {
            errorData = dataRepresentation.get("errorData", FunctionalityExecutionError.class);
        }

        String resultIdentificator = null;
        if (dataRepresentation.hasProperty("resultIdentificator")) {
            resultIdentificator = dataRepresentation.getText("resultIdentificator");
        }

        DataRepresentation result = null;
        if (dataRepresentation.hasProperty("result")) {
            result = dataRepresentation.get("result");
        }

        FunctionalityExecutionResult functionalityExecutionResult = create(verdict, warningData, errorData, resultIdentificator, result);
        return functionalityExecutionResult;
    }

    @Override
    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();

        if (getVerdict() != null) {
            dataRepresentation.add("verdict", getVerdict());
        }

        if (getWarningData() != null) {
            dataRepresentation.add("warningData", getWarningData().toArray());
        }

        if (getErrorData() != null) {
            dataRepresentation.add("errorData", getErrorData());
        }

        if (getResultIdentificator() != null) {
            dataRepresentation.add("resultIdentificator", getResultIdentificator());
        }

        if (getResult() != null) {
            dataRepresentation.add("result", getResult());
        }

        return dataRepresentation;
    }
}

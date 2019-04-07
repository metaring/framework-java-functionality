package com.metaring.framework.functionality;

import com.metaring.framework.functionality.FunctionalityExecutionVerdictEnumerator;
import com.metaring.framework.functionality.FunctionalityExecutionWarningDataSeries;
import com.metaring.framework.functionality.FunctionalityExecutionError;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public abstract class AbstractFunctionalityExecutionResult implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.framework.functionality.functionalityExecutionResult";

    private FunctionalityExecutionVerdictEnumerator verdict;
    private FunctionalityExecutionWarningDataSeries warningData;
    private FunctionalityExecutionError errorData;
    private String resultIdentificator;
    private DataRepresentation result;

    protected AbstractFunctionalityExecutionResult(FunctionalityExecutionVerdictEnumerator verdict, FunctionalityExecutionWarningDataSeries warningData, FunctionalityExecutionError errorData, String resultIdentificator, DataRepresentation result) {
        this.verdict = verdict;
        this.warningData = warningData;
        this.errorData = errorData;
        this.resultIdentificator = resultIdentificator;
        this.result = result;
    }

    public FunctionalityExecutionVerdictEnumerator getVerdict() {
        return this.verdict;
    }

    public FunctionalityExecutionWarningDataSeries getWarningData() {
        return this.warningData;
    }

    public FunctionalityExecutionError getErrorData() {
        return this.errorData;
    }

    public String getResultIdentificator() {
        return this.resultIdentificator;
    }

    public DataRepresentation getResult() {
        return this.result;
    }

    public static FunctionalityExecutionResult create(FunctionalityExecutionVerdictEnumerator verdict, FunctionalityExecutionWarningDataSeries warningData, FunctionalityExecutionError errorData, String resultIdentificator, DataRepresentation result) {
        return new FunctionalityExecutionResult(verdict, warningData, errorData, resultIdentificator, result);
    }

    public static FunctionalityExecutionResult fromJson(String jsonString) {

        if(jsonString == null) {
            return null;
        }

        jsonString = jsonString.trim();
        if(jsonString.isEmpty()) {
            return null;
        }

        if(jsonString.equalsIgnoreCase("null")) {
            return null;
        }

        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.fromJson(jsonString);

        FunctionalityExecutionVerdictEnumerator verdict = null;
        if(dataRepresentation.hasProperty("verdict")) {
            try {
                verdict = dataRepresentation.get("verdict", FunctionalityExecutionVerdictEnumerator.class);
            } catch (Exception e) {
            }
        }

        FunctionalityExecutionWarningDataSeries warningData = null;
        if(dataRepresentation.hasProperty("warningData")) {
            try {
                warningData = dataRepresentation.get("warningData", FunctionalityExecutionWarningDataSeries.class);
            } catch (Exception e) {
            }
        }

        FunctionalityExecutionError errorData = null;
        if(dataRepresentation.hasProperty("errorData")) {
            try {
                errorData = dataRepresentation.get("errorData", FunctionalityExecutionError.class);
            } catch (Exception e) {
            }
        }

        String resultIdentificator = null;
        if(dataRepresentation.hasProperty("resultIdentificator")) {
            try {
                resultIdentificator = dataRepresentation.getText("resultIdentificator");
            } catch (Exception e) {
            }
        }

        DataRepresentation result = null;
        if(dataRepresentation.hasProperty("result")) {
            try {
                result = dataRepresentation.get("result");
            } catch (Exception e) {
            }
        }

        FunctionalityExecutionResult functionalityExecutionResult = create(verdict, warningData, errorData, resultIdentificator, result);
        return functionalityExecutionResult;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (verdict != null) {
            dataRepresentation.add("verdict", verdict);
        }

        if (warningData != null) {
            dataRepresentation.add("warningData", warningData.toArray());
        }

        if (errorData != null) {
            dataRepresentation.add("errorData", errorData);
        }

        if (resultIdentificator != null) {
            dataRepresentation.add("resultIdentificator", resultIdentificator);
        }

        if (result != null) {
            dataRepresentation.add("result", result);
        }

        return dataRepresentation;
    }

    @Override
    public String toJson() {
        return toDataRepresentation().toJson();
    }

    @Override
    public String toString() {
        return this.toJson();
    }
}
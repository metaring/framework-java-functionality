package com.metaring.framework.functionality;

import com.metaring.framework.functionality.FunctionalityExecutionVerdictEnumerator;
import com.metaring.framework.functionality.FunctionalityStepErrorData;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class FunctionalityStepResult implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.framework.functionality.functionalityStepResult";

    private FunctionalityExecutionVerdictEnumerator verdict;
    private FunctionalityStepErrorData errorData;
    private String message;
    private DataRepresentation result;

    private FunctionalityStepResult(FunctionalityExecutionVerdictEnumerator verdict, FunctionalityStepErrorData errorData, String message, DataRepresentation result) {
        this.verdict = verdict;
        this.errorData = errorData;
        this.message = message;
        this.result = result;
    }

    public FunctionalityExecutionVerdictEnumerator getVerdict() {
        return this.verdict;
    }

    public FunctionalityStepErrorData getErrorData() {
        return this.errorData;
    }

    public String getMessage() {
        return this.message;
    }

    public DataRepresentation getResult() {
        return this.result;
    }

    public static FunctionalityStepResult create(FunctionalityExecutionVerdictEnumerator verdict, FunctionalityStepErrorData errorData, String message, DataRepresentation result) {
        return new FunctionalityStepResult(verdict, errorData, message, result);
    }

    public static FunctionalityStepResult fromJson(String jsonString) {

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

        FunctionalityStepErrorData errorData = null;
        if(dataRepresentation.hasProperty("errorData")) {
            try {
                errorData = dataRepresentation.get("errorData", FunctionalityStepErrorData.class);
            } catch (Exception e) {
            }
        }

        String message = null;
        if(dataRepresentation.hasProperty("message")) {
            try {
                message = dataRepresentation.getText("message");
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

        FunctionalityStepResult functionalityStepResult = create(verdict, errorData, message, result);
        return functionalityStepResult;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (verdict != null) {
            dataRepresentation.add("verdict", verdict);
        }

        if (errorData != null) {
            dataRepresentation.add("errorData", errorData);
        }

        if (message != null) {
            dataRepresentation.add("message", message);
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
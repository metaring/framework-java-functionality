package com.metaring.framework.functionality;

import com.metaring.framework.functionality.FunctionalityStackElementSeries;
import com.metaring.framework.functionality.FunctionalityStepErrorData;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public abstract class AbstractFunctionalityExecutionError implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.framework.functionality.functionalityExecutionError";

    private FunctionalityStackElementSeries stack;
    private FunctionalityStepErrorData errorData;

    protected AbstractFunctionalityExecutionError(FunctionalityStackElementSeries stack, FunctionalityStepErrorData errorData) {
        this.stack = stack;
        this.errorData = errorData;
    }

    public FunctionalityStackElementSeries getStack() {
        return this.stack;
    }

    public FunctionalityStepErrorData getErrorData() {
        return this.errorData;
    }

    public static FunctionalityExecutionError create(FunctionalityStackElementSeries stack, FunctionalityStepErrorData errorData) {
        return new FunctionalityExecutionError(stack, errorData);
    }

    public static FunctionalityExecutionError fromJson(String jsonString) {

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

        FunctionalityStackElementSeries stack = null;
        if(dataRepresentation.hasProperty("stack")) {
            try {
                stack = dataRepresentation.get("stack", FunctionalityStackElementSeries.class);
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

        FunctionalityExecutionError functionalityExecutionError = create(stack, errorData);
        return functionalityExecutionError;
    }

    public static FunctionalityExecutionError fromObject(Object object) {

        if(object == null) {
            return null;
        }

        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.fromObject(object);

        FunctionalityStackElementSeries stack = null;
        if(dataRepresentation.hasProperty("stack")) {
            try {
                stack = dataRepresentation.get("stack", FunctionalityStackElementSeries.class);
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

        FunctionalityExecutionError functionalityExecutionError = create(stack, errorData);
        return functionalityExecutionError;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (stack != null) {
            dataRepresentation.add("stack", stack.toArray());
        }

        if (errorData != null) {
            dataRepresentation.add("errorData", errorData);
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
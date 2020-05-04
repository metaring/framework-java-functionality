package com.metaring.framework.functionality;

import com.metaring.framework.functionality.FunctionalityExecutionStepEnumerator;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class FunctionalityExecutionWarningData implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.framework.functionality.functionalityExecutionWarningData";

    private FunctionalityExecutionStepEnumerator step;
    private String message;

    private FunctionalityExecutionWarningData(FunctionalityExecutionStepEnumerator step, String message) {
        this.step = step;
        this.message = message;
    }

    public FunctionalityExecutionStepEnumerator getStep() {
        return this.step;
    }

    public String getMessage() {
        return this.message;
    }

    public static FunctionalityExecutionWarningData create(FunctionalityExecutionStepEnumerator step, String message) {
        return new FunctionalityExecutionWarningData(step, message);
    }

    public static FunctionalityExecutionWarningData fromJson(String jsonString) {

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

        FunctionalityExecutionStepEnumerator step = null;
        if(dataRepresentation.hasProperty("step")) {
            try {
                step = dataRepresentation.get("step", FunctionalityExecutionStepEnumerator.class);
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

        FunctionalityExecutionWarningData functionalityExecutionWarningData = create(step, message);
        return functionalityExecutionWarningData;
    }

    public static FunctionalityExecutionWarningData fromObject(Object object) {

        if(object == null) {
            return null;
        }

        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.fromObject(object);

        FunctionalityExecutionStepEnumerator step = null;
        if(dataRepresentation.hasProperty("step")) {
            try {
                step = dataRepresentation.get("step", FunctionalityExecutionStepEnumerator.class);
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

        FunctionalityExecutionWarningData functionalityExecutionWarningData = create(step, message);
        return functionalityExecutionWarningData;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (step != null) {
            dataRepresentation.add("step", step);
        }

        if (message != null) {
            dataRepresentation.add("message", message);
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
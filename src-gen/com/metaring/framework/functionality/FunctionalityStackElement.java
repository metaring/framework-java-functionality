package com.metaring.framework.functionality;

import com.metaring.framework.functionality.FunctionalityExecutionStepEnumerator;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class FunctionalityStackElement implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.framework.functionality.functionalityStackElement";

    private String name;
    private FunctionalityExecutionStepEnumerator step;

    private FunctionalityStackElement(String name, FunctionalityExecutionStepEnumerator step) {
        this.name = name;
        this.step = step;
    }

    public String getName() {
        return this.name;
    }

    public FunctionalityExecutionStepEnumerator getStep() {
        return this.step;
    }

    public static FunctionalityStackElement create(String name, FunctionalityExecutionStepEnumerator step) {
        return new FunctionalityStackElement(name, step);
    }

    public static FunctionalityStackElement fromJson(String jsonString) {

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

        String name = null;
        if(dataRepresentation.hasProperty("name")) {
            try {
                name = dataRepresentation.getText("name");
            } catch (Exception e) {
            }
        }

        FunctionalityExecutionStepEnumerator step = null;
        if(dataRepresentation.hasProperty("step")) {
            try {
                step = dataRepresentation.get("step", FunctionalityExecutionStepEnumerator.class);
            } catch (Exception e) {
            }
        }

        FunctionalityStackElement functionalityStackElement = create(name, step);
        return functionalityStackElement;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (name != null) {
            dataRepresentation.add("name", name);
        }

        if (step != null) {
            dataRepresentation.add("step", step);
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
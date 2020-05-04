package com.metaring.framework.functionality;

import com.metaring.framework.functionality.FunctionalityErrorTypeEnumerator;
import com.metaring.framework.type.series.TextSeries;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class FunctionalityStepErrorData implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.framework.functionality.functionalityStepErrorData";

    private FunctionalityErrorTypeEnumerator type;
    private String name;
    private TextSeries payload;
    private String message;
    private Long row;

    private FunctionalityStepErrorData(FunctionalityErrorTypeEnumerator type, String name, TextSeries payload, String message, Long row) {
        this.type = type;
        this.name = name;
        this.payload = payload;
        this.message = message;
        this.row = row;
    }

    public FunctionalityErrorTypeEnumerator getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public TextSeries getPayload() {
        return this.payload;
    }

    public String getMessage() {
        return this.message;
    }

    public Long getRow() {
        return this.row;
    }

    public static FunctionalityStepErrorData create(FunctionalityErrorTypeEnumerator type, String name, TextSeries payload, String message, Long row) {
        return new FunctionalityStepErrorData(type, name, payload, message, row);
    }

    public static FunctionalityStepErrorData fromJson(String jsonString) {

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

        FunctionalityErrorTypeEnumerator type = null;
        if(dataRepresentation.hasProperty("type")) {
            try {
                type = dataRepresentation.get("type", FunctionalityErrorTypeEnumerator.class);
            } catch (Exception e) {
            }
        }

        String name = null;
        if(dataRepresentation.hasProperty("name")) {
            try {
                name = dataRepresentation.getText("name");
            } catch (Exception e) {
            }
        }

        TextSeries payload = null;
        if(dataRepresentation.hasProperty("payload")) {
            try {
                payload = dataRepresentation.getTextSeries("payload");
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

        Long row = null;
        if(dataRepresentation.hasProperty("row")) {
            try {
                row = dataRepresentation.getDigit("row");
            } catch (Exception e) {
            }
        }

        FunctionalityStepErrorData functionalityStepErrorData = create(type, name, payload, message, row);
        return functionalityStepErrorData;
    }

    public static FunctionalityStepErrorData fromObject(Object object) {

        if(object == null) {
            return null;
        }

        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.fromObject(object);

        FunctionalityErrorTypeEnumerator type = null;
        if(dataRepresentation.hasProperty("type")) {
            try {
                type = dataRepresentation.get("type", FunctionalityErrorTypeEnumerator.class);
            } catch (Exception e) {
            }
        }

        String name = null;
        if(dataRepresentation.hasProperty("name")) {
            try {
                name = dataRepresentation.getText("name");
            } catch (Exception e) {
            }
        }

        TextSeries payload = null;
        if(dataRepresentation.hasProperty("payload")) {
            try {
                payload = dataRepresentation.getTextSeries("payload");
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

        Long row = null;
        if(dataRepresentation.hasProperty("row")) {
            try {
                row = dataRepresentation.getDigit("row");
            } catch (Exception e) {
            }
        }

        FunctionalityStepErrorData functionalityStepErrorData = create(type, name, payload, message, row);
        return functionalityStepErrorData;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (type != null) {
            dataRepresentation.add("type", type);
        }

        if (name != null) {
            dataRepresentation.add("name", name);
        }

        if (payload != null) {
            dataRepresentation.add("payload", payload.toArray());
        }

        if (message != null) {
            dataRepresentation.add("message", message);
        }

        if (row != null) {
            dataRepresentation.add("row", row);
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
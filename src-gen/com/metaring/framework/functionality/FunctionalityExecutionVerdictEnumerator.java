package com.metaring.framework.functionality;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class FunctionalityExecutionVerdictEnumerator implements GeneratedCoreType {

    public static final FunctionalityExecutionVerdictEnumerator SUCCESS = new FunctionalityExecutionVerdictEnumerator("SUCCESS", 0l, "SUCCESS");
    public static final FunctionalityExecutionVerdictEnumerator FAULT = new FunctionalityExecutionVerdictEnumerator("FAULT", 1l, "FAULT");
    public static final FunctionalityExecutionVerdictEnumerator WARNING = new FunctionalityExecutionVerdictEnumerator("WARNING", 2l, "WARNING");

    private String name;
    private Long numericValue;
    private String textualValue;

    private FunctionalityExecutionVerdictEnumerator(String name, Long numericValue, String textualValue) {
        this.name = name;
        this.numericValue = numericValue;
        this.textualValue = textualValue;
    }

    public String getName() {
        return this.name;
    }

    public Long getNumericValue() {
        return this.numericValue;
    }

    public String getTextualValue() {
        return this.textualValue;
    }

    public static final FunctionalityExecutionVerdictEnumeratorSeries listAll() {
        return FunctionalityExecutionVerdictEnumeratorSeries.create(SUCCESS, FAULT, WARNING);
    }

    public static FunctionalityExecutionVerdictEnumerator getByNumericValue(Long numericValue) {
        if(numericValue == null) {
            return null;
        }
        switch(numericValue.intValue()) {

            case 0 : return SUCCESS;

            case 1 : return FAULT;

            case 2 : return WARNING;

            default: return null;
        }
    }

    public static FunctionalityExecutionVerdictEnumerator getByTextualValue(String textualValue) {
        if(textualValue == null) {
            return null;
        }
        switch(textualValue) {

            case "SUCCESS" : return SUCCESS;

            case "FAULT" : return FAULT;

            case "WARNING" : return WARNING;

            default: return null;
        }
    }

    public static FunctionalityExecutionVerdictEnumerator getByName(String functionalityExecutionVerdictEnumeratorName) {
        if(functionalityExecutionVerdictEnumeratorName == null) {
            return null;
        }
        switch(functionalityExecutionVerdictEnumeratorName) {

            case "SUCCESS" : return SUCCESS;

            case "FAULT" : return FAULT;

            case "WARNING" : return WARNING;

            default: return null;
        }
    }

    public static FunctionalityExecutionVerdictEnumerator fromJson(String json) {
        if(json == null) {
            return null;
        }
        if(json.startsWith("\"")) {
            json = json.substring(1);
        }
        if(json.endsWith("\"")) {
            json = json.substring(0, json.length() - 1);
        }
        try {
            return getByNumericValue(Long.parseLong(json));
        } catch(Exception e) {
        }
        FunctionalityExecutionVerdictEnumerator result = getByTextualValue(json);
        return result != null ? result : getByName(json);
    }

    @Override
    public String toJson() {
        return "\"" + this.name + "\"";
    }

    public DataRepresentation toDataRepresentation() {
        return Tools.FACTORY_DATA_REPRESENTATION.fromJson(toJson());
    }

    @Override
    public String toString() {
        return toJson();
    }
}
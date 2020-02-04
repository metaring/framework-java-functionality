package com.metaring.framework.functionality;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class CommonFunctionalityExecutionErrorsEnumerator implements GeneratedCoreType {

    public static final CommonFunctionalityExecutionErrorsEnumerator ALREADY_RUNNING = new CommonFunctionalityExecutionErrorsEnumerator("ALREADY_RUNNING", 0l, "The Functionality is already running");
    public static final CommonFunctionalityExecutionErrorsEnumerator ASYNC_CONTEXT = new CommonFunctionalityExecutionErrorsEnumerator("ASYNC_CONTEXT", 1l, "Synchronous calling of Functionality in an asynchronous context");
    public static final CommonFunctionalityExecutionErrorsEnumerator RESULT_NULL = new CommonFunctionalityExecutionErrorsEnumerator("RESULT_NULL", 2l, "Result is null");
    public static final CommonFunctionalityExecutionErrorsEnumerator VERDICT_NULL = new CommonFunctionalityExecutionErrorsEnumerator("VERDICT_NULL", 3l, "Result verdict is null");
    public static final CommonFunctionalityExecutionErrorsEnumerator UNKNOWN = new CommonFunctionalityExecutionErrorsEnumerator("UNKNOWN", 4l, "Unknown error");

    private String name;
    private Long numericValue;
    private String textualValue;

    private CommonFunctionalityExecutionErrorsEnumerator(String name, Long numericValue, String textualValue) {
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

    public static final CommonFunctionalityExecutionErrorsEnumeratorSeries listAll() {
        return CommonFunctionalityExecutionErrorsEnumeratorSeries.create(ALREADY_RUNNING, ASYNC_CONTEXT, RESULT_NULL, VERDICT_NULL, UNKNOWN);
    }

    public static CommonFunctionalityExecutionErrorsEnumerator getByNumericValue(Long numericValue) {
        if(numericValue == null) {
            return null;
        }
        switch(numericValue.intValue()) {

            case 0 : return ALREADY_RUNNING;

            case 1 : return ASYNC_CONTEXT;

            case 2 : return RESULT_NULL;

            case 3 : return VERDICT_NULL;

            case 4 : return UNKNOWN;

            default: return null;
        }
    }

    public static CommonFunctionalityExecutionErrorsEnumerator getByTextualValue(String textualValue) {
        if(textualValue == null) {
            return null;
        }
        switch(textualValue) {

            case "The Functionality is already running" : return ALREADY_RUNNING;

            case "Synchronous calling of Functionality in an asynchronous context" : return ASYNC_CONTEXT;

            case "Result is null" : return RESULT_NULL;

            case "Result verdict is null" : return VERDICT_NULL;

            case "Unknown error" : return UNKNOWN;

            default: return null;
        }
    }

    public static CommonFunctionalityExecutionErrorsEnumerator getByName(String commonFunctionalityExecutionErrorsEnumeratorName) {
        if(commonFunctionalityExecutionErrorsEnumeratorName == null) {
            return null;
        }
        switch(commonFunctionalityExecutionErrorsEnumeratorName) {

            case "ALREADY_RUNNING" : return ALREADY_RUNNING;

            case "ASYNC_CONTEXT" : return ASYNC_CONTEXT;

            case "RESULT_NULL" : return RESULT_NULL;

            case "VERDICT_NULL" : return VERDICT_NULL;

            case "UNKNOWN" : return UNKNOWN;

            default: return null;
        }
    }

    public static CommonFunctionalityExecutionErrorsEnumerator fromJson(String json) {
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
        CommonFunctionalityExecutionErrorsEnumerator result = getByTextualValue(json);
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
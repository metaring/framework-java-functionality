package com.metaring.framework.functionality;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class FunctionalityExecutionStepEnumerator implements GeneratedCoreType {

    public static final FunctionalityExecutionStepEnumerator CREATION = new FunctionalityExecutionStepEnumerator("CREATION", 0l, "CREATION");
    public static final FunctionalityExecutionStepEnumerator BEFORE_PRE_CONDITION_CHECK = new FunctionalityExecutionStepEnumerator("BEFORE_PRE_CONDITION_CHECK", 1l, "BEFORE_PRE_CONDITION_CHECK");
    public static final FunctionalityExecutionStepEnumerator PRE_CONDITION_CHECK = new FunctionalityExecutionStepEnumerator("PRE_CONDITION_CHECK", 2l, "PRE_CONDITION_CHECK");
    public static final FunctionalityExecutionStepEnumerator AFTER_PRE_CONDITION_CHECK = new FunctionalityExecutionStepEnumerator("AFTER_PRE_CONDITION_CHECK", 3l, "AFTER_PRE_CONDITION_CHECK");
    public static final FunctionalityExecutionStepEnumerator BEFORE_CALL = new FunctionalityExecutionStepEnumerator("BEFORE_CALL", 4l, "BEFORE_CALL");
    public static final FunctionalityExecutionStepEnumerator CALL = new FunctionalityExecutionStepEnumerator("CALL", 5l, "CALL");
    public static final FunctionalityExecutionStepEnumerator AFTER_CALL = new FunctionalityExecutionStepEnumerator("AFTER_CALL", 6l, "AFTER_CALL");
    public static final FunctionalityExecutionStepEnumerator BEFORE_POST_CONDITION_CHECK = new FunctionalityExecutionStepEnumerator("BEFORE_POST_CONDITION_CHECK", 7l, "BEFORE_POST_CONDITION_CHECK");
    public static final FunctionalityExecutionStepEnumerator POST_CONDITION_CHECK = new FunctionalityExecutionStepEnumerator("POST_CONDITION_CHECK", 8l, "POST_CONDITION_CHECK");
    public static final FunctionalityExecutionStepEnumerator AFTER_POST_CONDITION_CHECK = new FunctionalityExecutionStepEnumerator("AFTER_POST_CONDITION_CHECK", 9l, "AFTER_POST_CONDITION_CHECK");
    public static final FunctionalityExecutionStepEnumerator TERMINATE = new FunctionalityExecutionStepEnumerator("TERMINATE", 10l, "TERMINATE");
    public static final FunctionalityExecutionStepEnumerator VERIFY_RESULT = new FunctionalityExecutionStepEnumerator("VERIFY_RESULT", 11l, "VERIFY_RESULT");

    private String name;
    private Long numericValue;
    private String textualValue;

    private FunctionalityExecutionStepEnumerator(String name, Long numericValue, String textualValue) {
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

    public static final FunctionalityExecutionStepEnumeratorSeries listAll() {
        return FunctionalityExecutionStepEnumeratorSeries.create(CREATION, BEFORE_PRE_CONDITION_CHECK, PRE_CONDITION_CHECK, AFTER_PRE_CONDITION_CHECK, BEFORE_CALL, CALL, AFTER_CALL, BEFORE_POST_CONDITION_CHECK, POST_CONDITION_CHECK, AFTER_POST_CONDITION_CHECK, TERMINATE, VERIFY_RESULT);
    }

    public static FunctionalityExecutionStepEnumerator getByNumericValue(Long numericValue) {
        if(numericValue == null) {
            return null;
        }
        switch(numericValue.intValue()) {

            case 0 : return CREATION;

            case 1 : return BEFORE_PRE_CONDITION_CHECK;

            case 2 : return PRE_CONDITION_CHECK;

            case 3 : return AFTER_PRE_CONDITION_CHECK;

            case 4 : return BEFORE_CALL;

            case 5 : return CALL;

            case 6 : return AFTER_CALL;

            case 7 : return BEFORE_POST_CONDITION_CHECK;

            case 8 : return POST_CONDITION_CHECK;

            case 9 : return AFTER_POST_CONDITION_CHECK;

            case 10 : return TERMINATE;

            case 11 : return VERIFY_RESULT;

            default: return null;
        }
    }

    public static FunctionalityExecutionStepEnumerator getByTextualValue(String textualValue) {
        if(textualValue == null) {
            return null;
        }
        switch(textualValue) {

            case "CREATION" : return CREATION;

            case "BEFORE_PRE_CONDITION_CHECK" : return BEFORE_PRE_CONDITION_CHECK;

            case "PRE_CONDITION_CHECK" : return PRE_CONDITION_CHECK;

            case "AFTER_PRE_CONDITION_CHECK" : return AFTER_PRE_CONDITION_CHECK;

            case "BEFORE_CALL" : return BEFORE_CALL;

            case "CALL" : return CALL;

            case "AFTER_CALL" : return AFTER_CALL;

            case "BEFORE_POST_CONDITION_CHECK" : return BEFORE_POST_CONDITION_CHECK;

            case "POST_CONDITION_CHECK" : return POST_CONDITION_CHECK;

            case "AFTER_POST_CONDITION_CHECK" : return AFTER_POST_CONDITION_CHECK;

            case "TERMINATE" : return TERMINATE;

            case "VERIFY_RESULT" : return VERIFY_RESULT;

            default: return null;
        }
    }

    public static FunctionalityExecutionStepEnumerator getByName(String functionalityExecutionStepEnumeratorName) {
        if(functionalityExecutionStepEnumeratorName == null) {
            return null;
        }
        switch(functionalityExecutionStepEnumeratorName) {

            case "CREATION" : return CREATION;

            case "BEFORE_PRE_CONDITION_CHECK" : return BEFORE_PRE_CONDITION_CHECK;

            case "PRE_CONDITION_CHECK" : return PRE_CONDITION_CHECK;

            case "AFTER_PRE_CONDITION_CHECK" : return AFTER_PRE_CONDITION_CHECK;

            case "BEFORE_CALL" : return BEFORE_CALL;

            case "CALL" : return CALL;

            case "AFTER_CALL" : return AFTER_CALL;

            case "BEFORE_POST_CONDITION_CHECK" : return BEFORE_POST_CONDITION_CHECK;

            case "POST_CONDITION_CHECK" : return POST_CONDITION_CHECK;

            case "AFTER_POST_CONDITION_CHECK" : return AFTER_POST_CONDITION_CHECK;

            case "TERMINATE" : return TERMINATE;

            case "VERIFY_RESULT" : return VERIFY_RESULT;

            default: return null;
        }
    }

    public static FunctionalityExecutionStepEnumerator fromJson(String json) {
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
        FunctionalityExecutionStepEnumerator result = getByTextualValue(json);
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
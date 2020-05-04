package com.metaring.framework.functionality;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class FunctionalityErrorTypeEnumerator implements GeneratedCoreType {

    public static final FunctionalityErrorTypeEnumerator MANAGED = new FunctionalityErrorTypeEnumerator("MANAGED", 0l, "MANAGED");
    public static final FunctionalityErrorTypeEnumerator UNMANAGED = new FunctionalityErrorTypeEnumerator("UNMANAGED", 1l, "UNMANAGED");

    private String name;
    private Long numericValue;
    private String textualValue;

    private FunctionalityErrorTypeEnumerator(String name, Long numericValue, String textualValue) {
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

    public static final FunctionalityErrorTypeEnumeratorSeries listAll() {
        return FunctionalityErrorTypeEnumeratorSeries.create(MANAGED, UNMANAGED);
    }

    public static FunctionalityErrorTypeEnumerator getByNumericValue(Long numericValue) {
        if(numericValue == null) {
            return null;
        }
        switch(numericValue.intValue()) {

            case 0 : return MANAGED;

            case 1 : return UNMANAGED;

            default: return null;
        }
    }

    public static FunctionalityErrorTypeEnumerator getByTextualValue(String textualValue) {
        if(textualValue == null) {
            return null;
        }
        switch(textualValue) {

            case "MANAGED" : return MANAGED;

            case "UNMANAGED" : return UNMANAGED;

            default: return null;
        }
    }

    public static FunctionalityErrorTypeEnumerator getByName(String functionalityErrorTypeEnumeratorName) {
        if(functionalityErrorTypeEnumeratorName == null) {
            return null;
        }
        switch(functionalityErrorTypeEnumeratorName) {

            case "MANAGED" : return MANAGED;

            case "UNMANAGED" : return UNMANAGED;

            default: return null;
        }
    }

    public static FunctionalityErrorTypeEnumerator fromJson(String json) {
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
        FunctionalityErrorTypeEnumerator result = getByTextualValue(json);
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
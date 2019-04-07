/**
 *    Copyright 2019 MetaRing s.r.l.
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.metaring.framework.functionality;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;

public class FunctionalityInfo {
    private String functionalityFullyQualifiedName;
    private Boolean internal;
    private Boolean reserved;
    private Boolean restricted;
    private String inputFullyQualifiedName;
    private String outputFullyQualifiedName;

    private FunctionalityInfo(String functionalityFullyQualifiedName, Boolean internal, Boolean reserved, Boolean restricted, String inputFullyQualifiedName, String outputFullyQualifiedName) {
        this.functionalityFullyQualifiedName = functionalityFullyQualifiedName;
        this.internal = internal;
        this.reserved = reserved;
        this.restricted = restricted;
        this.inputFullyQualifiedName = inputFullyQualifiedName;
        this.outputFullyQualifiedName = outputFullyQualifiedName;
    }

    public String getFunctionalityFullyQualifiedName() {
        return this.functionalityFullyQualifiedName;
    }

    public Boolean isInternal() {
        return this.internal;
    }

    public Boolean isReserved() {
        return this.reserved;
    }

    public Boolean isRestricted() {
        return this.restricted;
    }

    public String getInputFullyQualifiedName() {
        return this.inputFullyQualifiedName;
    }

    public String getOutputFullyQualifiedName() {
        return this.outputFullyQualifiedName;
    }

    public static FunctionalityInfo create(String functionalityFullyQualifiedName, Boolean internal, Boolean reserved, Boolean restricted, String inputFullyQualifiedName, String outputFullyQualifiedName) {
        return new FunctionalityInfo(functionalityFullyQualifiedName, internal, reserved, restricted, inputFullyQualifiedName, outputFullyQualifiedName);
    }

    public static FunctionalityInfo fromJson(String jsonString) {

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

        String functionalityFullyQualifiedName = null;
        if(dataRepresentation.hasProperty("functionalityFullyQualifiedName")) {
            try {
                functionalityFullyQualifiedName = dataRepresentation.getText("functionalityFullyQualifiedName");
            } catch (Exception e) {
            }
        }

        Boolean internal = null;
        if(dataRepresentation.hasProperty("internal")) {
            try {
                internal = dataRepresentation.getTruth("internal");
            } catch (Exception e) {
            }
        }

        Boolean reserved = null;
        if(dataRepresentation.hasProperty("reserved")) {
            try {
                reserved = dataRepresentation.getTruth("reserved");
            } catch (Exception e) {
            }
        }

        Boolean restricted = null;
        if(dataRepresentation.hasProperty("restricted")) {
            try {
                restricted = dataRepresentation.getTruth("restricted");
            } catch (Exception e) {
            }
        }

        String inputFullyQualifiedName = null;
        if(dataRepresentation.hasProperty("inputFullyQualifiedName")) {
            try {
                inputFullyQualifiedName = dataRepresentation.getText("inputFullyQualifiedName");
            } catch (Exception e) {
            }
        }

        String outputFullyQualifiedName = null;
        if(dataRepresentation.hasProperty("outputFullyQualifiedName")) {
            try {
                outputFullyQualifiedName = dataRepresentation.getText("outputFullyQualifiedName");
            } catch (Exception e) {
            }
        }

        FunctionalityInfo functionalityInfo = create(functionalityFullyQualifiedName, internal, reserved, restricted, inputFullyQualifiedName, outputFullyQualifiedName);
        return functionalityInfo;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (functionalityFullyQualifiedName != null) {
            dataRepresentation.add("functionalityFullyQualifiedName", functionalityFullyQualifiedName);
        }

        if (internal != null) {
            dataRepresentation.add("internal", internal);
        }

        if (reserved != null) {
            dataRepresentation.add("reserved", reserved);
        }

        if (restricted != null) {
            dataRepresentation.add("restricted", restricted);
        }

        if (inputFullyQualifiedName != null) {
            dataRepresentation.add("inputFullyQualifiedName", inputFullyQualifiedName);
        }

        if (outputFullyQualifiedName != null) {
            dataRepresentation.add("outputFullyQualifiedName", outputFullyQualifiedName);
        }

        return dataRepresentation;
    }

    public String toJson() {
        return toDataRepresentation().toJson();
    }

    @Override
    public String toString() {
        return this.toJson();
    }
}

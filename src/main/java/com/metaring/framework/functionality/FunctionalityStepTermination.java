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

public class FunctionalityStepTermination {
    private String warningMessage;
    private Exception exception;
    private Object output;

    public FunctionalityStepTermination(FunctionalityExecutionResult functionalityExecutionResult) {
        this.output = FunctionalitiesManager.verifyAndSaveData(functionalityExecutionResult, this.warningMessage, this.exception);
    }

//    public FunctionalityStepTermination(String warningMessage) {
//        this.warningMessage = warningMessage;
//    }

    public FunctionalityStepTermination(Exception exception) {
        this.exception = exception;
    }

    public FunctionalityStepTermination(Object output, String warningMessage) {
        this.output = output;
        this.warningMessage = warningMessage;
    }

    public FunctionalityStepTermination(Object output) {
        this.output = output;
    }

    public FunctionalityStepTermination(String warningMessage, Exception exception) {
        this.warningMessage = warningMessage;
        this.exception = exception;
    }

    public String getWarningMessage() {
        return this.warningMessage;
    }

    public Exception getException() {
        return this.exception;
    }

    public Object getOutput() {
        return this.output;
    }
}

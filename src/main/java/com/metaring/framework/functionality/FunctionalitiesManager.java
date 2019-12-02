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

import static java.util.concurrent.CompletableFuture.completedFuture;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import com.metaring.framework.Core;
import com.metaring.framework.exception.ManagedException;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.util.StringUtil;

public class FunctionalitiesManager {

    public static final CompletableFuture<FunctionalityExecutionResult> executeFromJson(String functionalityFullyQualifiedName, Functionality callingFunctionality, String inputJson) throws FunctionalityNotFoundException {
        return executeWork(FunctionalitiesInfoProvider.get(functionalityFullyQualifiedName), null, callingFunctionality, inputJson, true);
    }

    public static final <T> CompletableFuture<T> call(FunctionalityInfo functionalityInfo, Class<? extends Functionality> functionalityClass, Functionality callingFunctionality, Object input, Function<DataRepresentation, T> transformer) {
        return executeWork(functionalityInfo, functionalityClass, callingFunctionality, input, false, transformer);
    }

    public static final <T> CompletableFuture<T> callFromJson(FunctionalityInfo functionalityInfo, Class<? extends Functionality> functionalityClass, Functionality callingFunctionality, String input, Function<DataRepresentation, T> transformer) {
        return executeWork(functionalityInfo, functionalityClass, callingFunctionality, input, true, transformer);
    }

    public static final <T> CompletableFuture<T> callFromJson(FunctionalityInfo functionalityInfo, Functionality callingFunctionality, String input, Function<DataRepresentation, T> transformer) {
        return executeWork(functionalityInfo, null, callingFunctionality, input, true, transformer);
    }

    private static final <T> CompletableFuture<T> executeWork(FunctionalityInfo functionalityInfo, Class<? extends Functionality> functionalityClass, Functionality callingFunctionality, Object input, boolean toJson, Function<DataRepresentation, T> transformer) {
        final CompletableFuture<T> result = new CompletableFuture<>();
        FunctionalitiesManager.executeWork(functionalityInfo, functionalityClass, callingFunctionality, input, toJson).whenCompleteAsync((res, error) -> {
            try {
                if(error != null) {
                    throw error;
                }
                DataRepresentation r = verifyAndReturnFunctionalityExecutionResult(res);
                result.complete(transformer != null && r != null ? transformer.apply(r) : null);
            } catch (Throwable e) {
                result.completeExceptionally(e);
            }
        }, FunctionalityControllerManager.INSTANCE);
        return result;
    }

    public static final DataRepresentation verifyAndReturnFunctionalityExecutionResult(FunctionalityExecutionResult functionalityExecutionResult) throws ManagedException, UnmanagedException {
        return verifyAndReturnFunctionalityExecutionResult(functionalityExecutionResult, null);
    }

    public static final DataRepresentation verifyAndReturnFunctionalityExecutionResult(FunctionalityExecutionResult functionalityExecutionResult, String warningData) throws ManagedException, UnmanagedException {
        if (functionalityExecutionResult == null) {
            throw new FunctionalityExecutionResultCheckException(FunctionalitiesManager.getStack(FunctionalityExecutionStepEnumerator.VERIFY_RESULT), "Functionality execution result is null");
        }
        if (functionalityExecutionResult.getVerdict() == FunctionalityExecutionVerdictEnumerator.WARNING) {
            FunctionalityExecutionWarningDataSeries functionalityExecutionWarningDataSeries = functionalityExecutionResult.getWarningData();
            if (functionalityExecutionWarningDataSeries == null) {
                throw new FunctionalityExecutionResultCheckException(FunctionalitiesManager.getStack(FunctionalityExecutionStepEnumerator.VERIFY_RESULT), "Warning data is null");
            }
            if (functionalityExecutionWarningDataSeries.size() == 0) {
                throw new FunctionalityExecutionResultCheckException(FunctionalitiesManager.getStack(FunctionalityExecutionStepEnumerator.VERIFY_RESULT), "No Warning data provided");
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int l = 0; l < functionalityExecutionResult.getWarningData().size(); ++l) {
                FunctionalityExecutionWarningData functionalityExecutionWarningData = functionalityExecutionResult.getWarningData().get(l);
                if (functionalityExecutionWarningData == null) {
                    throw new FunctionalityExecutionResultCheckException(FunctionalitiesManager.getStack(FunctionalityExecutionStepEnumerator.VERIFY_RESULT), "Warning data entry is null");
                }
                if (functionalityExecutionWarningData.getStep() == null) {
                    throw new FunctionalityExecutionResultCheckException(FunctionalitiesManager.getStack(FunctionalityExecutionStepEnumerator.VERIFY_RESULT), "Warning data entry step is null");
                }
                if (functionalityExecutionWarningData.getMessage() == null) {
                    throw new FunctionalityExecutionResultCheckException(FunctionalitiesManager.getStack(FunctionalityExecutionStepEnumerator.VERIFY_RESULT), "Warning data entry message is null");
                }
                stringBuilder.append("\n");
                stringBuilder.append("Warning data message retreived during ");
                stringBuilder.append(functionalityExecutionWarningData.getStep().getName()).append(" step:");
                stringBuilder.append("\n\t");
                stringBuilder.append(functionalityExecutionWarningData.getMessage());
                stringBuilder.append("\n");
            }
            warningData = stringBuilder.toString();
            String warningMessage = warningData;
            Core.SYSKB.getSystemLogger().warning(warningMessage);
        }
        if (functionalityExecutionResult.getVerdict() == FunctionalityExecutionVerdictEnumerator.FAULT) {
            FunctionalityExecutionError functionalityExecutionError = functionalityExecutionResult.getErrorData();
            if (functionalityExecutionError == null) {
                throw new FunctionalityExecutionResultCheckException(FunctionalitiesManager.getStack(FunctionalityExecutionStepEnumerator.VERIFY_RESULT), "Error data is null");
            }
            if (functionalityExecutionError.getStack() == null) {
                throw new FunctionalityExecutionResultCheckException(FunctionalitiesManager.getStack(FunctionalityExecutionStepEnumerator.VERIFY_RESULT), "Error data stack is null");
            }
            FunctionalityStepErrorData functionalityStepErrorData = functionalityExecutionError.getErrorData();
            if (functionalityStepErrorData == null) {
                throw new FunctionalityExecutionResultCheckException(FunctionalitiesManager.getStack(FunctionalityExecutionStepEnumerator.VERIFY_RESULT), "Step error data is null");
            }
            if (functionalityStepErrorData.getType() == null) {
                throw new FunctionalityExecutionResultCheckException(FunctionalitiesManager.getStack(FunctionalityExecutionStepEnumerator.VERIFY_RESULT), "Error data type is null");
            }
            if (functionalityStepErrorData.getMessage() == null) {
                throw new FunctionalityExecutionResultCheckException(FunctionalitiesManager.getStack(FunctionalityExecutionStepEnumerator.VERIFY_RESULT), "Error data message is null");
            }
            if (functionalityStepErrorData.getType() == FunctionalityErrorTypeEnumerator.UNMANAGED) {
                throw new RebuiltUnmanagedException(functionalityExecutionError);
            }
            throw new RebuiltManagedException(functionalityStepErrorData);
        }
        return functionalityExecutionResult.getResult();
    }

    public static final DataRepresentation verifyAndSaveData(FunctionalityExecutionResult functionalityExecutionResult, String warningData, Throwable e) {
        DataRepresentation result = null;
        try {
            result = FunctionalitiesManager.verifyAndReturnFunctionalityExecutionResult(functionalityExecutionResult, warningData);
        }
        catch (Exception ex) {
            e = ex;
        }
        return result;
    }

    private static final CompletableFuture<FunctionalityExecutionResult> executeWork(FunctionalityInfo functionalityInfo, Class<? extends Functionality> functionalityClass, Functionality callingFunctionality, Object input, boolean fromJson) {
        try {
            AbstractFunctionality functionality = FunctionalitiesProvider.getFunctionality(functionalityInfo, functionalityClass);
            FunctionalityContext functionalityContext = callingFunctionality == null ? new FunctionalityContext() : callingFunctionality.getContext();
            return functionality.execute(functionalityContext == null ? new FunctionalityContext() : functionalityContext, fromJson ? functionality.getInputFromJson(input == null ? null : input.toString()) : input);
        } catch (FunctionalityCreationException e) {
            return completedFuture(launchCreationUnmanagedError(callingFunctionality, e));
        }
    }

    private static final FunctionalityExecutionResult launchCreationUnmanagedError(Functionality callingFunctionality, Throwable t) {
        return FunctionalityExecutionResult.create(FunctionalityExecutionVerdictEnumerator.FAULT, null, FunctionalityExecutionError.create(FunctionalitiesManager.getStack(callingFunctionality), FunctionalityStepErrorData.create(FunctionalityErrorTypeEnumerator.UNMANAGED, null, null, StringUtil.fromThrowableToString(t), -1l)), null, null);
    }

    public static final FunctionalityStackElementSeries getStack(Functionality functionality) {
        if (functionality != null && functionality.getContext() != null) {
            return functionality.getContext().getStack().toStackElementSeries();
        }
        return getStack(FunctionalityExecutionStepEnumerator.CALL);
    }

    public static final FunctionalityStackElementSeries getStack(FunctionalityExecutionStepEnumerator functionalityExecutionStepEnumerator) {
        return FunctionalityStackElementSeries.create(FunctionalityStackElement.create("<" + functionalityExecutionStepEnumerator.getName().toLowerCase() + ">", functionalityExecutionStepEnumerator));
    }

    protected static final Functionality getCallingFunctionality() {
        try {
            return FunctionalitiesProvider.getFunctionalityByClassName(Arrays.asList(Thread.currentThread().getStackTrace()).stream().map(StackTraceElement::getClassName).filter(it -> it.endsWith("FunctionalityImpl")).findFirst().get());
        } catch (Exception e) {
            return null;
        }
    }
}

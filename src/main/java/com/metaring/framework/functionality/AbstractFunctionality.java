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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;

import com.ea.async.Async;
import com.metaring.framework.GeneratedCoreType;
import com.metaring.framework.Tools;
import com.metaring.framework.controller.AbstractBaseController;
import com.metaring.framework.exception.ManagedException;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.type.Email;
import com.metaring.framework.type.series.DigitSeries;
import com.metaring.framework.type.series.EmailSeries;
import com.metaring.framework.type.series.RealDigitSeries;
import com.metaring.framework.type.series.TextSeries;
import com.metaring.framework.type.series.TruthSeries;
import com.metaring.framework.util.StringUtil;

@SuppressWarnings("unchecked")
public abstract class AbstractFunctionality extends AbstractBaseController implements Functionality {

    protected static final CompletableFuture<Void> end = completedFuture(null);
    protected static final Executor EXECUTOR = FunctionalityControllerManager.INSTANCE;

    private final FunctionalityInfo functionalityInfo;
    private final String functionalityName;
    private final Class<?> outputClass;

    private final List<BiFunction<Object, Object, CompletableFuture<?>>> steps = new ArrayList<>();

    private final LinkedList<FunctionalityInstance> queue = new LinkedList<>();

    static {
        Async.init();
    }

    protected AbstractFunctionality(FunctionalityInfo functionalityInfo, Class<?> outputClass) {
        super();
        this.functionalityInfo = functionalityInfo;
        this.functionalityName = "[" + functionalityInfo.getFunctionalityFullyQualifiedName() + "] [";
        this.outputClass = outputClass;
        setLoggerStackPosition();
        fillSteps();
    }

    private final void fillSteps() {
        if (!steps.isEmpty()) {
            return;
        }
        steps.add(null);
        steps.add((input, output) -> {
            try {
                return beforePreConditionCheck(input);
            } catch (Exception e) {
                throw new InternalStepExectutionException(e);
            }
        });
        steps.add((input, output) -> {
            try {
                return preConditionCheck(input);
            } catch (Exception e) {
                throw new InternalStepExectutionException(e);
            }
        });
        steps.add((input, output) -> {
            try {
                return afterPreConditionCheck(input);
            } catch (Exception e) {
                throw new InternalStepExectutionException(e);
            }
        });
        steps.add((input, output) -> {
            try {
                return beforeCall(input);
            } catch (Exception e) {
                throw new InternalStepExectutionException(e);
            }
        });
        steps.add((input, output) -> {
            try {
                return call(input);
            } catch (Exception e) {
                throw new InternalStepExectutionException(e);
            }
        });
        steps.add((input, output) -> {
            try {
                return afterCall(input, output);
            } catch (Exception e) {
                throw new InternalStepExectutionException(e);
            }
        });
        steps.add((input, output) -> {
            try {
                return beforePostConditionCheck(input, output);
            } catch (Exception e) {
                throw new InternalStepExectutionException(e);
            }
        });
        steps.add((input, output) -> {
            try {
                return postConditionCheck(input, output);
            } catch (Exception e) {
                throw new InternalStepExectutionException(e);
            }
        });
        steps.add((input, output) -> {
            try {
                return afterPostConditionCheck(input, output);
            } catch (Exception e) {
                throw new InternalStepExectutionException(e);
            }
        });
    }

    private static final CompletableFuture<Object> wrap(final Object input, final Object output, final BiFunction<Object, Object, CompletableFuture<?>> function) {
        if (function == null) {
            return completedFuture(null);
        }
        final CompletableFuture<Object> wrap = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            try {
                function.apply(input, output).whenComplete((result, error) -> {
                    if (error != null) {
                        wrap.completeExceptionally(error);
                        return;
                    }
                    wrap.complete(result);
                });
            } catch (Exception e) {
                wrap.completeExceptionally(e);
            }
        }, EXECUTOR);
        return wrap;
    }

    @Override
    public final FunctionalityInfo getInfo() {
        return functionalityInfo;
    }

    @Override
    public FunctionalityExecutionStepEnumerator getCurrentStep() {
        return FunctionalityExecutionStepEnumerator.getByNumericValue(queue.getFirst().currentStep);
    }

    @Override
    public final FunctionalityContext getContext() {
        return queue.getFirst().functionalityContext;
    }

    protected final CompletableFuture<FunctionalityExecutionResult> execute(FunctionalityContext functionalityContext, Object input) {
        functionalityContext.getStack().add(this);
        FunctionalityInstance functionalityInstance = new FunctionalityInstance(functionalityContext, input);
        queue.addLast(functionalityInstance);
        if (queue.size() == 1) {
            CompletableFuture.runAsync(this::run, EXECUTOR);
        }
        return functionalityInstance.response;
    }

    private final void run() {
        if(queue.isEmpty()) {
            return;
        }
        final FunctionalityInstance functionalityInstance = queue.getFirst();
        wrap(functionalityInstance.input, functionalityInstance.output, steps.get(functionalityInstance.currentStep.intValue())).whenCompleteAsync((result, error) -> {
            if (error != null) {
                errorOccurred(functionalityInstance.functionalityContext, error);
            } else if(FunctionalityExecutionStepEnumerator.getByNumericValue(functionalityInstance.currentStep) == FunctionalityExecutionStepEnumerator.CALL){
                functionalityInstance.output = cleanOutput(result, outputClass);
            }
            functionalityInstance.currentStep++;
            queue.removeFirst();
            if(error == null && functionalityInstance.currentStep <= FunctionalityExecutionStepEnumerator.AFTER_POST_CONDITION_CHECK.getNumericValue()) {
                queue.addLast(functionalityInstance);
            } else {
                terminateStepExecution(functionalityInstance.functionalityContext).whenComplete((v, e) -> {
                    if(e != null) {
                        functionalityInstance.response.completeExceptionally(e);
                        return;
                    }
                    functionalityInstance.response.complete(buildResult(functionalityInstance, outputClass));
                });
            }
            if(!queue.isEmpty()) {
                CompletableFuture.runAsync(this::run, EXECUTOR);
            }
        }, EXECUTOR);
    }

    private static final FunctionalityExecutionResult buildResult(final FunctionalityInstance functionalityInstance, Class<?> outputClass) {
        FunctionalityExecutionError executionError = functionalityInstance.functionalityContext.executionError;
        FunctionalityExecutionWarningDataSeries warnings = functionalityInstance.functionalityContext.getWarnings();
        FunctionalityExecutionVerdictEnumerator verdict = executionError == null ? warnings == null || warnings.isEmpty() ? FunctionalityExecutionVerdictEnumerator.SUCCESS : FunctionalityExecutionVerdictEnumerator.WARNING : FunctionalityExecutionVerdictEnumerator.FAULT;
        functionalityInstance.functionalityContext.getStack().removeLast();
        if (functionalityInstance.functionalityContext.getStack().isEmpty()) {
            try {
                functionalityInstance.functionalityContext.purge();
            } catch (UnmanagedException e) {
            }
        }
        return FunctionalityExecutionResult.create(verdict, warnings, executionError, outputClass == null ? null : outputClass.getName(), Tools.FACTORY_DATA_REPRESENTATION.fromObject(functionalityInstance.output));
    }

    private static final void errorOccurred(FunctionalityContext functionalityContext, Throwable throwable) {
        if (functionalityContext.executionError != null) {
            return;
        }
        while (throwable != null && throwable.getCause() != null && (throwable instanceof InternalStepExectutionException || throwable instanceof CompletionException || throwable != throwable.getCause())) {
            throwable = throwable.getCause();
        }
        long row = -1;
        try {
            StackTraceElement[] elements = throwable.getStackTrace();
            for (int i = 0; i < elements.length; i++) {
                if (GeneratedFunctionality.class.isAssignableFrom(Class.forName(elements[i].getClassName()))) {
                    row = elements[i].getLineNumber();
                    break;
                }
            }

        } catch (Exception e) {
        }
        FunctionalityErrorTypeEnumerator type = FunctionalityErrorTypeEnumerator.UNMANAGED;
        String errorMessage = StringUtil.fromThrowableToString(throwable);
        String errorName = null;
        TextSeries errorPayload = null;
        if (throwable instanceof ManagedException) {
            type = FunctionalityErrorTypeEnumerator.MANAGED;
            ManagedException me = (ManagedException) throwable;
            errorName = me.getName();
            errorPayload = me.getPayload();
        }
        functionalityContext.executionError = FunctionalityExecutionError.create(functionalityContext.getStackElementSeries(), FunctionalityStepErrorData.create(type, errorName, errorPayload, errorMessage, row));
    }

    protected final <T> CompletableFuture<T> end(T t) {
        return completedFuture(t);
    }

    protected abstract CompletableFuture<Void> beforePreConditionCheck(Object input) throws Exception;

    protected abstract CompletableFuture<Void> preConditionCheck(Object input) throws Exception;

    protected abstract CompletableFuture<Void> afterPreConditionCheck(Object input) throws Exception;

    protected abstract CompletableFuture<Void> beforeCall(Object input) throws Exception;

    protected abstract CompletableFuture<Object> call(Object input) throws Exception;

    protected abstract CompletableFuture<Void> afterCall(Object input, Object output) throws Exception;

    protected abstract CompletableFuture<Void> beforePostConditionCheck(Object input, Object output) throws Exception;

    protected abstract CompletableFuture<Void> postConditionCheck(Object input, Object output) throws Exception;

    protected abstract CompletableFuture<Void> afterPostConditionCheck(Object input, Object output) throws Exception;

    private static final Object cleanOutput(Object output, Class<?> outputClass) {
        if (outputClass == null) {
            return null;
        }
        if (output == null) {
            return null;
        }
        boolean simpleInstance = outputClass.isInstance(output);
        if (simpleInstance) {
            return output;
        }
        if (output instanceof DataRepresentation) {
            DataRepresentation dataRepresentation = (DataRepresentation) output;
            if (outputClass.equals(String.class) && dataRepresentation.isText()) {
                return dataRepresentation.asText();
            }
            if (outputClass.equals(Long.class) && dataRepresentation.isDigit()) {
                return dataRepresentation.asDigit();
            }
            if (outputClass.equals(Double.class) && dataRepresentation.isRealDigit()) {
                return dataRepresentation.asRealDigit();
            }
            if (outputClass.equals(Boolean.class) && dataRepresentation.isTruth()) {
                return dataRepresentation.asTruth();
            }
            if (outputClass.equals(Email.class) && dataRepresentation.isEmail()) {
                return dataRepresentation.asEmail();
            }
            if (outputClass.equals(TextSeries.class) && dataRepresentation.isTextSeries()) {
                return dataRepresentation.asTextSeries();
            }
            if (outputClass.equals(DigitSeries.class) && dataRepresentation.isDigitSeries()) {
                return dataRepresentation.asDigitSeries();
            }
            if (outputClass.equals(RealDigitSeries.class) && dataRepresentation.isRealDigitSeries()) {
                return dataRepresentation.asRealDigitSeries();
            }
            if (outputClass.equals(TruthSeries.class) && dataRepresentation.isTruthSeries()) {
                return dataRepresentation.asTruthSeries();
            }
            if (outputClass.equals(EmailSeries.class) && dataRepresentation.isEmailSeries()) {
                return dataRepresentation.asEmailSeries();
            }
            if (outputClass.isAssignableFrom(GeneratedCoreType.class)) {
                Class<? extends GeneratedCoreType> generatedCoreTypeClass = (Class<? extends GeneratedCoreType>) outputClass;
                if (dataRepresentation.is(generatedCoreTypeClass)) {
                    return dataRepresentation.as(generatedCoreTypeClass);
                }
            }
        }
        return null;
    }

    private static final CompletableFuture<Void> terminateStepExecution(FunctionalityContext functionalityContext) {
        if (functionalityContext.executionError != null) {
            return functionalityContext.rollbackTransaction();
        }
        if (functionalityContext.getStack().getCardinality() == 1) {
            return functionalityContext.commitTransaction();
        }
        return end;
    }

    private final String getPrologue() {
        return this.functionalityName + Thread.currentThread().getName() + "] [" + getCurrentStep().getName() + "] [" + new Exception().getStackTrace()[2].getLineNumber() + "] ";
    }

    protected final void entering() {
        super.entering(getPrologue());
    }

    protected final void exiting() {
        super.exiting(getPrologue());
    }

    protected final void info(String messageString, String... messages) {
        List<String> list = messages != null && messages.length > 0 ? Arrays.asList(messages) : new ArrayList<String>();
        list.add(0, messageString);
        super.info(getPrologue(), list.toArray(new String[list.size()]));
    }

    protected final void debug(String messageString, String... messages) {
        List<String> list = messages != null && messages.length > 0 ? Arrays.asList(messages) : new ArrayList<String>();
        list.add(0, messageString);
        super.debug(getPrologue(), list.toArray(new String[list.size()]));
    }

    protected final void config(String parameterNameString, Object parameterValue) {
        super.config(getPrologue() + parameterNameString, parameterValue);
    }

    protected final void warning(String messageString, String... messages) {
        List<String> list = messages != null && messages.length > 0 ? Arrays.asList(messages) : new ArrayList<String>();
        list.add(0, messageString);
        super.warning(getPrologue(), list.toArray(new String[list.size()]));
    }

    protected final void severe(String messageString, String... messages) {
        List<String> list = messages != null && messages.length > 0 ? Arrays.asList(messages) : new ArrayList<String>();
        list.add(0, messageString);
        super.severe(getPrologue(), list.toArray(new String[list.size()]));
    }

    protected final void severe(Throwable t, String... messages) {
        List<String> list = messages != null && messages.length > 0 ? Arrays.asList(messages) : new ArrayList<String>();
        list.add(0, StringUtil.fromThrowableToString((Throwable) t));
        super.severe(getPrologue(), list.toArray(new String[list.size()]));
    }

    @Override
    public final Object getInputFromJson(String inputJson) {
        if(StringUtil.isNullOrEmpty(inputJson)) {
             return null;
        }
        if(inputJson.trim().equalsIgnoreCase("null")) {
            return null;
        }
        return getInputFromJsonWork(inputJson);
    }

    protected Object getInputFromJsonWork(String inputJson) {
        return null;
    }

    protected final void setContextData(String key, Object value) {
        queue.getFirst().functionalityContext.getData().put(key, value);
    }

    protected final <T> T getContextData(String key) {
        return (T) queue.getFirst().functionalityContext.getData().get(key);
    }

    protected final <T extends GeneratedCoreType> T getContextData(String key, Class<? extends T> clazz) {
        return ((DataRepresentation) getContextData(key)).as(clazz);
    }
}
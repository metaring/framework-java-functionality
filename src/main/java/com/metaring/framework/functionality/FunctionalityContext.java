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

import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;

import com.metaring.framework.util.StringUtil;

public class FunctionalityContext {

    private static final CompletableFuture<Void> END = CompletableFuture.completedFuture(null);

    protected LinkedList<FunctionalityExecutionWarningData> warnings = new LinkedList<>();
    private FunctionalityStack stack;
    private FunctionalityContextData data;
    private FunctionalityTransactionController functionalityTransactionController;
    protected FunctionalityExecutionError executionError;

    public FunctionalityContext() {
        this.data = new FunctionalityContextData();
        this.stack = new FunctionalityStack();
        FunctionalityTransactionControllerManager.createInstance().thenAcceptAsync(result -> {
            if(result == null) {
                return;
            }
            (functionalityTransactionController = result).initTransaction(FunctionalityControllerManager.INSTANCE);
        }, FunctionalityControllerManager.INSTANCE);
    }

    public final FunctionalityStack getStack() {
        return this.stack;
    }

    public final FunctionalityContextData getData() {
        return this.data;
    }

    public final void purge() throws UnmanagedException {
        this.stack.purge();
        this.stack = null;
        this.data.purge();
        this.data = null;
        this.warnings.clear();
        this.warnings = null;
        this.executionError = null;
        if(this.functionalityTransactionController != null) {
            this.functionalityTransactionController.close(FunctionalityControllerManager.INSTANCE);
        }
        this.functionalityTransactionController = null;
    }

    public final void addWarningMessage(String warning) {
        if (!StringUtil.isNullOrEmpty((String) warning)) {
            this.warnings.addLast(FunctionalityExecutionWarningData.create(((AbstractFunctionality) this.getStack().getLast()).getCurrentStep(), warning));
        }
    }

    public final FunctionalityExecutionWarningDataSeries getWarnings() {
        if (this.warnings.isEmpty()) {
            return null;
        }
        return FunctionalityExecutionWarningDataSeries.create(this.warnings.toArray(new FunctionalityExecutionWarningData[this.warnings.size()]));
    }

    public final FunctionalityStackElementSeries getStackElementSeries() {
        return this.stack.toStackElementSeries();
    }

    public final FunctionalityStackElementSeries getStackElementSeriesTillNow(Functionality functionality) {
        return this.stack.toStackElementSeriesTillNow(functionality);
    }

    public final FunctionalityTransactionController getTransactionController() {
        return this.functionalityTransactionController;
    }

    public final CompletableFuture<Void> commitTransaction() {
        if(this.functionalityTransactionController != null && this.functionalityTransactionController.isInTransaction()) {
            return this.functionalityTransactionController.commitTransaction(FunctionalityControllerManager.INSTANCE);
        }
        return END;
    }

    public final CompletableFuture<Void> rollbackTransaction() {
        if(this.functionalityTransactionController != null && this.functionalityTransactionController.isInTransaction()) {
            return this.functionalityTransactionController.rollbackTransaction(FunctionalityControllerManager.INSTANCE);
        }
        return END;
    }
}
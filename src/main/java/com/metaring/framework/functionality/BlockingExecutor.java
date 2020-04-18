package com.metaring.framework.functionality;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class BlockingExecutor {

    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    @SuppressWarnings("unchecked")
    public static final <T> CompletableFuture<T> retrieveAsync(SafeSupplier<T> supplier) {
        final CompletableFuture<T> completableFuture = new CompletableFuture<>();
        EXECUTOR_SERVICE.execute(() -> {
            Object[] t = new Object[1];
            Throwable[] throwable = new Throwable[1];
            try {
                t[0] = supplier.get();
            } catch(Exception e) {
                throwable[0] = e;
            }
            CompletableFuture.runAsync(() -> {
                if(throwable[0] != null) {
                    completableFuture.completeExceptionally(throwable[0]);
                    return;
                }
                completableFuture.complete((T) t[0]);
            }, FunctionalityControllerManager.INSTANCE);
        });
        return completableFuture;
    }

    public static final CompletableFuture<Void> executeAsync(SafeRunnable runnable) {
        final CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        EXECUTOR_SERVICE.execute(() -> {
            Throwable[] throwable = new Throwable[1];
            try {
                runnable.run();
            } catch(Exception e) {
                throwable[0] = e;
            }
            CompletableFuture.runAsync(() -> {
                if(throwable[0] != null) {
                    completableFuture.completeExceptionally(throwable[0]);
                    return;
                }
                completableFuture.complete(null);
            }, FunctionalityControllerManager.INSTANCE);
        });
        return completableFuture;
    }
}
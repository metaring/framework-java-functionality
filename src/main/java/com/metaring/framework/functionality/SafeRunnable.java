package com.metaring.framework.functionality;

@FunctionalInterface
public interface SafeRunnable {
    void run() throws Exception;
}
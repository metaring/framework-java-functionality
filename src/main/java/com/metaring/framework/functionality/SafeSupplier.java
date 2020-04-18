package com.metaring.framework.functionality;

@FunctionalInterface
public interface SafeSupplier<T> {
    T get() throws Exception;
}
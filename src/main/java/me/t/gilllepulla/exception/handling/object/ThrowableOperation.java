package me.t.gilllepulla.exception.handling.object;

@FunctionalInterface
public interface ThrowableOperation<T> {

    T execute() throws Throwable;
}

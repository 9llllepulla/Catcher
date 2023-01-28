package me.t.gilllepulla.handling.object;

@FunctionalInterface
public interface ThrowableOperation<T> {

    T execute() throws Throwable;
}

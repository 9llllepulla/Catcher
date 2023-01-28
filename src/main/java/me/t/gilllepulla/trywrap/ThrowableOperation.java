package me.t.gilllepulla.trywrap;

@FunctionalInterface
public interface ThrowableOperation<T> {

    T execute() throws Throwable;
}

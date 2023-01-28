package me.t.gilllepulla.handling.object;

@FunctionalInterface
public interface ThrowableFunction<T, R> {

    R apply(T t) throws Throwable;
}

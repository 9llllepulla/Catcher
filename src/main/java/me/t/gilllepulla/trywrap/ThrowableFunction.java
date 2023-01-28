package me.t.gilllepulla.trywrap;

@FunctionalInterface
public interface ThrowableFunction<T, R> {

    R apply(T t) throws Throwable;
}

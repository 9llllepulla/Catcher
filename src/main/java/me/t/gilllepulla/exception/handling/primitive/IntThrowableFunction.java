package me.t.gilllepulla.exception.handling.primitive;

@FunctionalInterface
public interface IntThrowableFunction<R> {

    R apply(int value) throws Throwable;

}

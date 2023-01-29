package me.t.gilllepulla.handling.primitive;

@FunctionalInterface
public interface IntThrowableFunction<R> {

    R apply(int value) throws Throwable;

}

package me.t.gilllepulla.exception.handling.primitive.integer;

@FunctionalInterface
public interface IntThrowableFunction<R> {

    R apply(int value) throws Throwable;

}

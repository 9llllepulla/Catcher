package me.t.gilllepulla.exception.handling.primitive.integer_;

@FunctionalInterface
public interface IntThrowableFunction<R> {

    R apply(int value) throws Throwable;

}

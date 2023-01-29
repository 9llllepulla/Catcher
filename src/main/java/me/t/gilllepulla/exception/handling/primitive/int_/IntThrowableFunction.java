package me.t.gilllepulla.exception.handling.primitive.int_;

@FunctionalInterface
public interface IntThrowableFunction<R> {

    R apply(int value) throws Throwable;

}

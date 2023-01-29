package me.t.gilllepulla.exception.handling.primitive;

@FunctionalInterface
public interface DoubleThrowableFunction<R> {

    R apply(double value) throws Throwable;

}

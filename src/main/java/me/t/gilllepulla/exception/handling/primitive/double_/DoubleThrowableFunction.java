package me.t.gilllepulla.exception.handling.primitive.double_;

@FunctionalInterface
public interface DoubleThrowableFunction<R> {

    R apply(double value) throws Throwable;

}

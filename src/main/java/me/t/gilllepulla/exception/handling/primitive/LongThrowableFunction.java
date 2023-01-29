package me.t.gilllepulla.exception.handling.primitive;

@FunctionalInterface
public interface LongThrowableFunction<R> {

    R apply(long value) throws Throwable;

}

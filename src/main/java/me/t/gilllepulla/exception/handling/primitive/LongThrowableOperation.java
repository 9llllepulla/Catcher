package me.t.gilllepulla.exception.handling.primitive;

@FunctionalInterface
public interface LongThrowableOperation {

    long execute() throws Throwable;

}

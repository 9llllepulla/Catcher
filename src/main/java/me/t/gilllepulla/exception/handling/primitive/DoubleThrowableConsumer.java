package me.t.gilllepulla.exception.handling.primitive;

@FunctionalInterface
public interface DoubleThrowableConsumer<E extends Throwable> {

    void accept(double value) throws E;

}

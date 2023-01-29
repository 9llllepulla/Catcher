package me.t.gilllepulla.exception.handling.primitive.double_;

@FunctionalInterface
public interface DoubleThrowableConsumer<E extends Throwable> {

    void accept(double value) throws E;

}

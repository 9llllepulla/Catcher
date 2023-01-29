package me.t.gilllepulla.exception.handling.primitive;

@FunctionalInterface
public interface LongThrowableConsumer<E extends Throwable> {

    void accept(long value) throws E;

}

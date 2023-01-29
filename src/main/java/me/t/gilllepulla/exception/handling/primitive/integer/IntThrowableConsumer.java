package me.t.gilllepulla.exception.handling.primitive.integer;

@FunctionalInterface
public interface IntThrowableConsumer <E extends Throwable>{

    void accept(int value) throws E;

}

package me.t.gilllepulla.handling.primitive;

@FunctionalInterface
public interface IntThrowableConsumer <E extends Throwable>{

    void accept(int value) throws E;

}

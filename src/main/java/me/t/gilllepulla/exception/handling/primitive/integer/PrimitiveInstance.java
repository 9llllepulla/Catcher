package me.t.gilllepulla.exception.handling.primitive.integer;

public final class PrimitiveInstance {

    private PrimitiveInstance() {
    }

    public static FailInt geFailIntInstance(Throwable throwable) {
        return new FailInt(throwable);
    }

    public static SuccessInt getSuccessIntInstance(int value) {
        return new SuccessInt(value);
    }

}

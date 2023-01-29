package me.t.gilllepulla.exception.handling.primitive.int_;

public final class PrimitiveInstance {

    private PrimitiveInstance() {
    }

    public static Fail geFailInstance(Throwable throwable) {
        return new Fail(throwable);
    }

    public static Success getSuccessInstance(int value) {
        return new Success(value);
    }

}

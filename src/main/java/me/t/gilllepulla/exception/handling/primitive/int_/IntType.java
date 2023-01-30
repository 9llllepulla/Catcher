package me.t.gilllepulla.exception.handling.primitive.int_;

public final class IntType {

    private IntType() {
    }

    public static Fail geFailInstance(Throwable throwable) {
        return new Fail(throwable);
    }

    public static Success getSuccessInstance(int value) {
        return new Success(value);
    }

}

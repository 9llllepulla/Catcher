package me.t.gilllepulla.exception.handling.object;

public final class Instance {

    private Instance() {
    }

    public static <T> Fail<T> getFailInstance(Throwable throwable) {
        return new Fail<>(throwable);
    }

    public static <T> Success<T> getSuccessInstance(T t) {
        return new Success<>(t);
    }
}

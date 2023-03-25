package me.t.gilllepulla.exception.handling.object;

/**
 * Factory class to provide Fail and Success object
 *
 * @author Sergey Lyashko
 */
public final class ObjectType {

    private ObjectType() {
    }

    public static <T> Fail<T> getFailInstance(Throwable throwable) {
        return new Fail<>(throwable);
    }

    public static <T> Success<T> getSuccessInstance(T t) {
        return new Success<>(t);
    }
}

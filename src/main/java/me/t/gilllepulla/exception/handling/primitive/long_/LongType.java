package me.t.gilllepulla.exception.handling.primitive.long_;

/**
 * Factory class to provide Fail and Success object
 *
 * @author Sergey Lyashko
 */
public final class LongType {

    private LongType() {
    }

    public static Fail geFailInstance(Throwable throwable) {
        return new Fail(throwable);
    }

    public static Success getSuccessInstance(long value) {
        return new Success(value);
    }
}

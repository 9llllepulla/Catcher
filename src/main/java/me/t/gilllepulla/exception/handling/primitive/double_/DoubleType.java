package me.t.gilllepulla.exception.handling.primitive.double_;

/**
 * Factory class to provide Fail and Success object
 *
 * @author Sergey Lyashko
 */
public final class DoubleType {

    private DoubleType() {
    }

    public static Fail geFailInstance(Throwable throwable) {
        return new Fail(throwable);
    }

    public static Success getSuccessInstance(double value) {
        return new Success(value);
    }
}

package me.t.gilllepulla.trywrap;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Result of execution that could have a value of the type T or failed with a Throwable.
 *
 * @author Sergey Lyashko
 */
public interface Try<T> {

    /**
     * Instance of wrapped value of the type T for a success or failure execution
     */
    static <T> Try<T> of(ThrowableOperation<T> operation) {
        try {
            return new Success<>(operation.execute());
        } catch (Throwable e) {
            return new Fail<>(e);
        }
    }

    /**
     * @return true if the original operation succeeded
     */
    boolean isSuccess();

    /**
     * @return the resulting value if this is a success, otherwise throws the original exception
     */
    T get() throws Throwable;

    /**
     * @return the resulting value if this is a success execution or
     * @throws RuntimeException the original exception wrapped in a RuntimeException
     */
    T getUnchecked();

    /**
     * @return wrapped resulting value if this is success execution or empty if this failure
     */
    Optional<T> toOptional();

    /**
     * @return the given resulting value or default value if this is a fail
     */
    T getOrElse(T defaultValue);

    /**
     * @return the resulting value if it is a succeeded execution or the result produced by the given supplier
     */
    T getOrElse(Supplier<? extends T> supplier);

    /**
     * @return the resulting value if this is a success execution or
     * @throws Throwable produced by the exception supplier
     */
    <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * @return the current resulting value wrapped Try if it is a success, otherwise does nothing
     * @throws E if the action throws an exception
     */
    <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> action) throws E;

    /**
     * @return the current resulting value wrapped Try if it is a fail, otherwise does nothing
     * @throws E if the action throws an exception
     */
    <E extends Throwable> Try<T> onFail(ThrowableConsumer<Throwable, E> action) throws E;

    /**
     * Converts this success into a fail (which holds NoSuchElementException)
     * if it is a Success and the predicate doesn't match for the value, otherwise returns this Try (success or fail)
     *
     * @return this Try (Success or Fail)
     * @throws NoSuchElementException if the predicate doesn't match for the value
     */
    Try<T> filter(Predicate<T> predicate);

    /**
     * Applies the given function to the resulting value if is a Success, otherwise returns this Fail.
     * If that function fails a Fail is returned
     *
     * @return
     */
    <U> Try<U> map(ThrowableFunction<? super T, ? extends U> function);

    /**
     * Applies the given function to the resulting value if is a Success, otherwise returns this Fail.
     * If that function fails a Fail is returned.
     * <p>
     * This method is similar to {@link Try#map}, but the mapping function already returns a Try.
     * Being invoked this method doesn't wrap the result of this function within a nested optional
     *
     * @return
     */
    <U> Try<U> flatMap(ThrowableFunction<? super T, Try<U>> function);

    /**
     * Applies the given function to recover from the exception throw if it is a failed execution, otherwise returns success.
     *
     * @return a new Try in the case of failure, or the current success
     */
    Try<T> recover(ThrowableFunction<? super Throwable, T> function);

    /**
     * Applies the given function to recover from the throwable if it is a Fail,
     * otherwise returns this Success.
     * <p>
     * This method is similar to {@link Try#recover}, but the recovering function already returns a Try.
     * Being invoked this method doesn't wrap the result of this function within a nested optional
     *
     * @return a new Try provided by the given function in the case of failure, or the current Success
     */
    Try<T> recoverWith(ThrowableFunction<? super Throwable, Try<T>> function);
}

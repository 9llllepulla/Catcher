package me.t.gilllepulla.trywrap;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Represents a failed execution operation
 *
 * @author Sergey Lyashko
 */
class Fail<T> implements Try<T> {
    private final Throwable throwable;

    Fail(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public T get() throws Throwable {
        throw throwable;
    }

    @Override
    public T getUnchecked() {
        throw new RuntimeException(throwable);
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.empty();
    }

    @Override
    public T getOrElse(T defaultValue) {
        return defaultValue;
    }

    @Override
    public T getOrElse(Supplier<? extends T> supplier) {
        return supplier.get();
    }

    @Override
    public <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        throw exceptionSupplier.get();
    }

    @Override
    public <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> action) throws E {
        return this;
    }

    @Override
    public <E extends Throwable> Try<T> onFail(ThrowableConsumer<Throwable, E> action) throws E {
        action.accept(throwable);
        return this;
    }

    @Override
    public Try<T> filter(Predicate<T> predicate) {
        return this;
    }

    @Override
    public <U> Try<U> map(ThrowableFunction<? super T, ? extends U> function) {
        return new Fail<>(throwable);
    }

    @Override
    public <U> Try<U> flatMap(ThrowableFunction<? super T, Try<U>> function) {
        return new Fail<>(throwable);
    }

    @Override
    public Try<T> recover(ThrowableFunction<? super Throwable, T> function) {
        try {
            T apply = function.apply(throwable);
            return new Success<>(apply);
        } catch (Throwable ex) {
            return new Fail<>(ex);
        }
    }

    @Override
    public Try<T> recoverWith(ThrowableFunction<? super Throwable, Try<T>> function) {
        try {
            return function.apply(throwable);
        } catch (Throwable ex) {
            return new Fail<>(ex);
        }
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public String toString() {
        return "Fail[" + throwable + "]";
    }
}

package me.t.gilllepulla.handling.object;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Представляет успешную операцию выполнения
 *
 * @author Sergey Lyashko
 */
public final class Success<T> implements Try<T> {
    private final T value;

    public Success(T value) {
        this.value = value;
    }

    @Override
    public T get() throws Throwable {
        return value;
    }

    @Override
    public T getUnchecked() {
        return value;
    }

    @Override
    public Optional<T> optional() {
        return Optional.of(value);
    }

    @Override
    public Stream<T> stream() {
        return Stream.of(value);
    }

    @Override
    public T getOrElse(T defaultValue) {
        return value;
    }

    @Override
    public T getOrElse(Supplier<? extends T> supplier) {
        return value;
    }

    @Override
    public <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return value;
    }

    @Override
    public <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> consumer) throws E {
        consumer.accept(value);
        return this;
    }

    @Override
    public <E extends Throwable> Try<T> onFail(ThrowableConsumer<Throwable, E> consumer) throws E {
        return this;
    }

    @Override
    public Try<T> filter(Predicate<T> predicate) {
        if (predicate.test(value)) {
            return this;
        }
        return new Fail<>(new NoSuchElementException());
    }

    @Override
    public <U> Try<U> map(ThrowableFunction<? super T, ? extends U> mapper) {
        try {
            U apply = mapper.apply(value);
            return new Success<>(apply);
        } catch (Throwable e) {
            return new Fail<>(e);
        }
    }

    @Override
    public <U> Try<U> flatMap(ThrowableFunction<? super T, Try<U>> mapper) {
        try {
            return mapper.apply(value);
        } catch (Throwable e) {
            return new Fail<>(e);
        }
    }

    @Override
    public Try<T> recover(ThrowableFunction<? super Throwable, T> recoverFunction) {
        return this;
    }

    @Override
    public Try<T> recoverWith(ThrowableFunction<? super Throwable, Try<T>> recoverFunction) {
        return this;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public String toString() {
        return "Success[" + value + "]";
    }
}

package me.t.gilllepulla.exception.handling.primitive.integer;

import me.t.gilllepulla.exception.handling.TryInt;
import me.t.gilllepulla.exception.handling.object.Fail;
import me.t.gilllepulla.exception.handling.Try;

import java.util.OptionalInt;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Представляет неудачную операцию выполнения для примитивного типа int
 *
 * @author Sergey Lyashko
 */
final class FailInt implements TryInt {
    private final Throwable throwable;

    FailInt(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public OptionalInt optional() {
        return OptionalInt.empty();
    }

    @Override
    public int get() throws Throwable {
        throw throwable;
    }

    @Override
    public int getUnchecked() {
        throw new RuntimeException(throwable);
    }

    @Override
    public int getOrElse(int defaultValue) {
        return defaultValue;
    }

    @Override
    public int getOrElse(IntSupplier supplier) {
        return supplier.getAsInt();
    }

    @Override
    public <X extends Throwable> int getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        throw exceptionSupplier.get();
    }

    @Override
    public <E extends Throwable> TryInt onSuccess(IntThrowableConsumer<E> consumer) throws E {
        return this;
    }

    @Override
    public TryInt filter(IntPredicate predicate) {
        return this;
    }

    @Override
    public <U> Try<U> mapToObj(IntThrowableFunction<? extends U> mapper) {
        return new Fail<>(throwable);
    }

    @Override
    public IntStream stream() {
        return IntStream.empty();
    }

    @Override
    public String toString() {
        return "FailInt[" + throwable + ']';
    }
}
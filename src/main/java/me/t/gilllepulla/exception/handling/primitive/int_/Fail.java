package me.t.gilllepulla.exception.handling.primitive.int_;

import me.t.gilllepulla.exception.handling.TryInt;
import me.t.gilllepulla.exception.handling.Try;
import me.t.gilllepulla.exception.handling.object.ObjectType;
import me.t.gilllepulla.exception.handling.primitive.IntThrowableConsumer;
import me.t.gilllepulla.exception.handling.primitive.IntThrowableFunction;

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
class Fail implements TryInt {
    private final Throwable throwable;

    Fail(Throwable throwable) {
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
        return ObjectType.getFailInstance(throwable);
    }

    @Override
    public IntStream stream() {
        return IntStream.empty();
    }

    @Override
    public String toString() {
        return "Fail[" + throwable + ']';
    }
}

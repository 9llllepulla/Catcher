package me.t.gilllepulla.exception.handling.primitive.long_;

import me.t.gilllepulla.exception.handling.Try;
import me.t.gilllepulla.exception.handling.TryLong;
import me.t.gilllepulla.exception.handling.object.ObjectType;
import me.t.gilllepulla.exception.handling.primitive.LongThrowableConsumer;
import me.t.gilllepulla.exception.handling.primitive.LongThrowableFunction;

import java.util.OptionalLong;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.LongStream;

/**
 * Представляет неудачную операцию выполнения для примитивного типа long
 *
 * @author Sergey Lyashko
 */
class Fail implements TryLong {
    private final Throwable throwable;

    Fail(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public OptionalLong optional() {
        return OptionalLong.empty();
    }

    @Override
    public long get() throws Throwable {
        throw throwable;
    }

    @Override
    public long getUnchecked() {
        throw new RuntimeException(throwable);
    }

    @Override
    public long getOrElse(long defaultValue) {
        return defaultValue;
    }

    @Override
    public long getOrElse(LongSupplier supplier) {
        return supplier.getAsLong();
    }

    @Override
    public <X extends Throwable> long getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        throw exceptionSupplier.get();
    }

    @Override
    public <E extends Throwable> TryLong onSuccess(LongThrowableConsumer<E> consumer) throws E {
        return this;
    }

    @Override
    public TryLong filter(LongPredicate predicate) {
        return this;
    }

    @Override
    public <U> Try<U> mapToObj(LongThrowableFunction<? extends U> mapper) {
        return ObjectType.getFailInstance(throwable);
    }

    @Override
    public LongStream stream() {
        return LongStream.empty();
    }

    @Override
    public String toString() {
        return "Fail{" + throwable +'}';
    }
}

package me.t.gilllepulla.exception.handling.primitive.long_;

import me.t.gilllepulla.exception.handling.Try;
import me.t.gilllepulla.exception.handling.TryLong;
import me.t.gilllepulla.exception.handling.object.ObjectType;
import me.t.gilllepulla.exception.handling.primitive.LongThrowableConsumer;
import me.t.gilllepulla.exception.handling.primitive.LongThrowableFunction;

import java.util.NoSuchElementException;
import java.util.OptionalLong;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.LongStream;

/**
 * Представляет успешную операцию выполнения для примитивного long
 *
 * @author Sergey Lyashko
 */
class Success implements TryLong {
    private final long value;

    Success(long value) {
        this.value = value;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public OptionalLong optional() {
        return OptionalLong.of(value);
    }

    @Override
    public long get() throws Throwable {
        return value;
    }

    @Override
    public long getUnchecked() {
        return value;
    }

    @Override
    public long getOrElse(long defaultValue) {
        return value;
    }

    @Override
    public long getOrElse(LongSupplier supplier) {
        return value;
    }

    @Override
    public <X extends Throwable> long getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return value;
    }

    @Override
    public <E extends Throwable> TryLong onSuccess(LongThrowableConsumer<E> consumer) throws E {
        consumer.accept(value);
        return this;
    }

    @Override
    public TryLong filter(LongPredicate predicate) {
        if (predicate.test(value)) {
            return this;
        }
        return new Fail(new NoSuchElementException());
    }

    @Override
    public <U> Try<U> mapToObj(LongThrowableFunction<? extends U> mapper) {
        try {
            U apply = mapper.apply(value);
            return ObjectType.getSuccessInstance(apply);
        } catch (Throwable e) {
            return ObjectType.getFailInstance(e);
        }
    }

    @Override
    public LongStream stream() {
        return LongStream.of(value);
    }

    @Override
    public String toString() {
        return "Success{" + value + '}';
    }
}

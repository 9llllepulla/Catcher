package me.t.gilllepulla.exception.handling.primitive.int_;

import me.t.gilllepulla.exception.handling.Try;
import me.t.gilllepulla.exception.handling.TryInt;
import me.t.gilllepulla.exception.handling.object.ObjectType;
import me.t.gilllepulla.exception.handling.primitive.IntThrowableConsumer;
import me.t.gilllepulla.exception.handling.primitive.IntThrowableFunction;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Представляет успешную операцию выполнения для примитивного int
 *
 * @author Sergey Lyashko
 */
class Success implements TryInt {
    private final int value;

    Success(int value) {
        this.value = value;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public OptionalInt optional() {
        return OptionalInt.of(value);
    }

    @Override
    public int get() throws Throwable {
        return value;
    }

    @Override
    public int getUnchecked() {
        return value;
    }

    @Override
    public int getOrElse(int defaultValue) {
        return value;
    }

    @Override
    public int getOrElse(IntSupplier supplier) {
        return value;
    }

    @Override
    public <X extends Throwable> int getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return value;
    }

    @Override
    public <E extends Throwable> TryInt onSuccess(IntThrowableConsumer<E> consumer) throws E {
        consumer.accept(value);
        return this;
    }

    @Override
    public TryInt filter(IntPredicate predicate) {
        if (predicate.test(value)) {
            return this;
        }
        return new Fail(new NoSuchElementException());
    }

    @Override
    public <U> Try<U> mapToObj(IntThrowableFunction<? extends U> mapper) {
        try {
            U apply = mapper.apply(value);
            return ObjectType.getSuccessInstance(apply);
        } catch (Throwable e) {
            return ObjectType.getFailInstance(e);
        }
    }

    @Override
    public IntStream stream() {
        return IntStream.of(value);
    }

    @Override
    public String toString() {
        return "Success[" + value + ']';
    }
}

package me.t.gilllepulla.exception.handling.primitive.double_;

import me.t.gilllepulla.exception.handling.Try;
import me.t.gilllepulla.exception.handling.TryDouble;
import me.t.gilllepulla.exception.handling.object.ObjectType;
import me.t.gilllepulla.exception.handling.primitive.DoubleThrowableConsumer;
import me.t.gilllepulla.exception.handling.primitive.DoubleThrowableFunction;

import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

/**
 * Представляет успешную операцию выполнения для примитивного double
 *
 * @author Sergey Lyashko
 */
class Success implements TryDouble {
    private final double value;

    Success(double value) {
        this.value = value;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public OptionalDouble optional() {
        return OptionalDouble.of(value);
    }

    @Override
    public double get() throws Throwable {
        return value;
    }

    @Override
    public double getUnchecked() {
        return value;
    }

    @Override
    public double getOrElse(double defaultValue) {
        return value;
    }

    @Override
    public double getOrElse(DoubleSupplier supplier) {
        return value;
    }

    @Override
    public <X extends Throwable> double getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return value;
    }

    @Override
    public <E extends Throwable> TryDouble onSuccess(DoubleThrowableConsumer<E> consumer) throws E {
        consumer.accept(value);
        return this;
    }

    @Override
    public TryDouble filter(DoublePredicate predicate) {
        if (predicate.test(value)) {
            return this;
        }
        return new Fail(new NoSuchElementException());
    }

    @Override
    public <U> Try<U> mapToObj(DoubleThrowableFunction<? extends U> mapper) {
        try {
            U apply = mapper.apply(value);
            return ObjectType.getSuccessInstance(apply);
        } catch (Throwable e) {
            return ObjectType.getFailInstance(e);
        }
    }

    @Override
    public DoubleStream stream() {
        return DoubleStream.of(value);
    }

    @Override
    public String toString() {
        return "Success{" + value + '}';
    }
}

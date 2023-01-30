package me.t.gilllepulla.exception.handling.primitive.double_;

import me.t.gilllepulla.exception.handling.Try;
import me.t.gilllepulla.exception.handling.TryDouble;
import me.t.gilllepulla.exception.handling.object.ObjectType;
import me.t.gilllepulla.exception.handling.primitive.DoubleThrowableConsumer;
import me.t.gilllepulla.exception.handling.primitive.DoubleThrowableFunction;

import java.util.OptionalDouble;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

/**
 * Представляет неудачную операцию выполнения для примитивного типа double
 *
 * @author Sergey Lyashko
 */
class Fail implements TryDouble {
    private final Throwable throwable;

    Fail(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public OptionalDouble optional() {
        return OptionalDouble.empty();
    }

    @Override
    public double get() throws Throwable {
        throw throwable;
    }

    @Override
    public double getUnchecked() {
        throw new RuntimeException(throwable);
    }

    @Override
    public double getOrElse(double defaultValue) {
        return defaultValue;
    }

    @Override
    public double getOrElse(DoubleSupplier supplier) {
        return supplier.getAsDouble();
    }

    @Override
    public <X extends Throwable> double getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        throw exceptionSupplier.get();
    }

    @Override
    public <E extends Throwable> TryDouble onSuccess(DoubleThrowableConsumer<E> consumer) throws E {
        return this;
    }

    @Override
    public TryDouble filter(DoublePredicate predicate) {
        return this;
    }

    @Override
    public <U> Try<U> mapToObj(DoubleThrowableFunction<? extends U> mapper) {
        return ObjectType.getFailInstance(throwable);
    }

    @Override
    public DoubleStream stream() {
        return DoubleStream.empty();
    }
}

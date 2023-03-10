package me.t.gilllepulla.exception.handling;

import me.t.gilllepulla.exception.handling.primitive.*;
import me.t.gilllepulla.exception.handling.primitive.double_.DoubleType;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.*;
import java.util.stream.DoubleStream;

/**
 * Оборачивающий интерфейс для выполнения операций с примитивным типом double, которые могут завершиться ошибкой (выбросить исключение).
 * Может содержать значение примитивного типа int как результат выполнения операции или завершиться с ошибкой Throwable.
 * <p>
 * Предоставляет дополнительные методы, результат выполнения которых зависит от наличия содержащегося результирующего значения или ошибки выполнения.
 * <p>
 * Предназначен для работы с примитивными типами и использования в качестве типа возвращаемого значения метода,
 * когда существует явная возможность возникновения ошибки выполнения.
 *
 * @author Sergey Lyashko
 */
public interface TryDouble {

    static TryDouble of(DoubleThrowableOperation operation) {
        try {
            return DoubleType.getSuccessInstance(operation.execute());
        } catch (Throwable e) {
            return DoubleType.geFailInstance(e);
        }
    }

    /**
     * @return true если исходная операция может быть выполнена успешно
     */
    boolean isSuccess();

    /**
     * @return обернутое в OptionalInt результирующее значение, если это успешное выполнение или пустое, в случае ошибки
     */
    OptionalDouble optional();

    /**
     * @return результирующее значение, если произошло успешное выполнение
     * @throws Throwable исходное исключение
     */
    double get() throws Throwable;

    /**
     * @return результирующее значение, если произошло успешное выполнение
     * @throws RuntimeException обернутое в RuntimeException исходное исключение в случае ошибки
     */
    double getUnchecked();

    /**
     * @return результирующее значение, если это успешное выполнение, или значение по умолчанию, в случае ошибки
     */
    double getOrElse(double defaultValue);

    /**
     * @return результирующее значение, если это успешное выполнение, иначе результат, произведенный данным поставщиком
     */
    double getOrElse(DoubleSupplier supplier);

    /**
     * @return результирующее значение, если выполнение было успешное или исключение, передаваемое supplier-ом
     * @throws Throwable производится поставщиком исключения, передаваемого в сигнатуре метода
     */
    <X extends Throwable> double getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * @return текущее результирующее значение обернуто Try, если оно выполнено успешно, иначе ничего не делает
     * @throws E если действие в consumer вызывает исключение
     */
    <E extends Throwable> TryDouble onSuccess(DoubleThrowableConsumer<E> consumer) throws E;

    /**
     * Если значение присутствует и соответствует заданному предикату, возвращает Try, описывающий значение,
     * в противном случае возвращает Try, содержащий NoSuchElementException.
     *
     * @return результирующее значение или ошибку, обернутую Try
     * @throws NoSuchElementException если предикат не соответствует значению
     */
    TryDouble filter(DoublePredicate predicate);

    /**
     * Применяет данную функцию к результирующему значению в случае успеха, в противном случае возвращает это значение с ошибкой.
     * Если передаваемая параметром функция выбрасывает исключение в случае ошибки, то возвращается обернутая в Try ошибка.
     * <p>
     * Этот метод по своему принципу действия аналогичен {@link Optional#map(Function)}
     *
     * @param mapper функция преобразования
     */
    <U> Try<U> mapToObj(DoubleThrowableFunction<? extends U> mapper);

    /**
     * @return получение stream-a результирующего значения, если это успешное выполнение или пустой stream, в случае ошибки
     */
    DoubleStream stream();

}

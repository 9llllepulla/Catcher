package me.t.gilllepulla.exception.handling;

import me.t.gilllepulla.exception.handling.primitive.*;
import me.t.gilllepulla.exception.handling.primitive.long_.LongType;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.function.*;
import java.util.stream.LongStream;

/**
 * Оборачивающий интерфейс для выполнения операций с примитивным типом long, которые могут завершиться ошибкой (выбросить исключение).
 * Может содержать значение примитивного типа int как результат выполнения операции или завершиться с ошибкой Throwable.
 * <p>
 * Предоставляет дополнительные методы, результат выполнения которых зависит от наличия содержащегося результирующего значения или ошибки выполнения.
 * <p>
 * Предназначен для работы с примитивными типами и использования в качестве типа возвращаемого значения метода,
 * когда существует явная возможность возникновения ошибки выполнения.
 *
 * @author Sergey Lyashko
 */
public interface TryLong {

    static TryLong of(LongThrowableOperation operation) {
        try {
            return LongType.getSuccessInstance(operation.execute());
        } catch (Throwable e) {
            return LongType.geFailInstance(e);
        }
    }

    /**
     * @return true если исходная операция может быть выполнена успешно
     */
    boolean isSuccess();

    /**
     * @return обернутое в OptionalInt результирующее значение, если это успешное выполнение или пустое, в случае ошибки
     */
    OptionalLong optional();

    /**
     * @return результирующее значение, если произошло успешное выполнение
     * @throws Throwable исходное исключение
     */
    long get() throws Throwable;

    /**
     * @return результирующее значение, если произошло успешное выполнение
     * @throws RuntimeException обернутое в RuntimeException исходное исключение в случае ошибки
     */
    long getUnchecked();

    /**
     * @return результирующее значение, если это успешное выполнение, или значение по умолчанию, в случае ошибки
     */
    long getOrElse(long defaultValue);

    /**
     * @return результирующее значение, если это успешное выполнение, иначе результат, произведенный данным поставщиком
     */
    long getOrElse(LongSupplier supplier);

    /**
     * @return результирующее значение, если выполнение было успешное или исключение, передаваемое supplier-ом
     * @throws Throwable производится поставщиком исключения, передаваемого в сигнатуре метода
     */
    <X extends Throwable> long getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * @return текущее результирующее значение обернуто Try, если оно выполнено успешно, иначе ничего не делает
     * @throws E если действие в consumer вызывает исключение
     */
    <E extends Throwable> TryLong onSuccess(LongThrowableConsumer<E> consumer) throws E;

    /**
     * Если значение присутствует и соответствует заданному предикату, возвращает Try, описывающий значение,
     * в противном случае возвращает Try, содержащий NoSuchElementException.
     *
     * @return результирующее значение или ошибку, обернутую Try
     * @throws NoSuchElementException если предикат не соответствует значению
     */
    TryLong filter(LongPredicate predicate);

    /**
     * Применяет данную функцию к результирующему значению в случае успеха, в противном случае возвращает это значение с ошибкой.
     * Если передаваемая параметром функция выбрасывает исключение в случае ошибки, то возвращается обернутая в Try ошибка.
     * <p>
     * Этот метод по своему принципу действия аналогичен {@link Optional#map(Function)}
     *
     * @param mapper функция преобразования
     */
    <U> Try<U> mapToObj(LongThrowableFunction<? extends U> mapper);

    /**
     * @return получение stream-a результирующего значения, если это успешное выполнение или пустой stream, в случае ошибки
     */
    LongStream stream();
}

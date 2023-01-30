package me.t.gilllepulla.exception.handling;

import me.t.gilllepulla.exception.handling.object.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Оборачивающий интерфейс для выполнения операций, которые могут завершиться ошибкой (выбросить исключение).
 * Может содержать значение типа T как результат выполнения операции или завершиться с ошибкой Throwable.
 * <p>
 * Предоставляет дополнительные методы, результат выполнения которых зависит от наличия содержащегося результирующего значения или ошибки выполнения.
 * <p>
 * Предназначен для использования в качестве типа возвращаемого значения метода, когда существует явная возможность возникновения ошибки выполнения.
 *
 * @param <T> тип значения результата успешного выполнения
 * @author Sergey Lyashko
 */
public interface Try<T> {

    /**
     * Экземпляр обернутого значения типа T для успешного или неудачного последующего выполнения
     */
    static <T> Try<T> of(ThrowableOperation<T> operation) {
        try {
            return ObjectType.getSuccessInstance(operation.execute());
        } catch (Throwable e) {
            return ObjectType.getFailInstance(e);
        }
    }

    /**
     * @return true если исходная операция может быть выполнена успешно
     */
    boolean isSuccess();

    /**
     * @return обернутое в Optional результирующее значение, если это успешное выполнение или пустое, в случае ошибки
     */
    Optional<T> optional();

    /**
     * @return получение stream-a результирующего значения, если это успешное выполнение или пустой stream, в случае ошибки
     */
    Stream<T> stream();

    /**
     * @return результирующее значение, если произошло успешное выполнение
     * @throws Throwable исходное исключение
     */
    T get() throws Throwable;

    /**
     * @return результирующее значение, если произошло успешное выполнение
     * @throws RuntimeException обернутое в RuntimeException исходное исключение в случае ошибки
     */
    T getUnchecked();

    /**
     * @return результирующее значение, если это успешное выполнение, или значение по умолчанию, в случае ошибки
     */
    T getOrElse(T defaultValue);

    /**
     * @return результирующее значение, если это успешное выполнение, иначе результат, произведенный данным поставщиком
     */
    T getOrElse(Supplier<? extends T> supplier);

    /**
     * @return результирующее значение, если выполнение было успешное или исключение, передаваемое supplier-ом
     * @throws Throwable производится поставщиком исключения, передаваемого в сигнатуре метода
     */
    <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * @return текущее результирующее значение обернуто Try, если оно выполнено успешно, иначе ничего не делает
     * @throws E если действие в consumer вызывает исключение
     */
    <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> consumer) throws E;

    /**
     * @return текущее результирующее значение обернуто Try, если это ошибка, иначе ничего не делает
     * @throws E если действие в consumer вызывает исключение
     */
    <E extends Throwable> Try<T> onFail(ThrowableConsumer<Throwable, E> consumer) throws E;

    /**
     * Если значение присутствует и соответствует заданному предикату, возвращает Try, описывающий значение,
     * в противном случае возвращает Try, содержащий NoSuchElementException.
     *
     * @return результирующее значение или ошибку, обернутую Try
     * @throws NoSuchElementException если предикат не соответствует значению
     */
    Try<T> filter(Predicate<T> predicate);

    /**
     * Применяет данную функцию к результирующему значению в случае успеха, в противном случае возвращает это значение с ошибкой.
     * Если передаваемая параметром функция выбрасывает исключение в случае ошибки, то возвращается обернутая в Try ошибка.
     * <p>
     * Этот метод по своему принципу действия аналогичен {@link Optional#map(Function)}
     *
     * @param mapper функция преобразования
     */
    <U> Try<U> map(ThrowableFunction<? super T, ? extends U> mapper);

    /**
     * Применяет данную функцию к результирующему значению в случае успеха, в противном случае возвращает это значение с ошибкой.
     * Если передаваемая параметром функция не работает, возвращается обернутая в Try ошибка.
     * <p>
     * Этот метод по своему принципу действия аналогичен {@link Optional#flatMap(Function)}
     *
     * @param mapper функция преобразования
     */
    <U> Try<U> flatMap(ThrowableFunction<? super T, Try<U>> mapper);

    /**
     * Применяет recoverFunction для восстановления после выброса исключения, если это неудачное выполнение,
     * в противном случае возвращает результирующее значение выполнения, обернутое в Try.
     * <p>
     * Этот метод по своему принципу действия аналогичен {@link Optional#map(Function)}
     *
     * @return новый Try, содержащий ошибку, в случае, если выполнение завершилось ошибкой
     * или текущее результирующее значение выполнения, в случае успешного выполнения
     */
    Try<T> recover(ThrowableFunction<? super Throwable, T> recoverFunction);

    /**
     * Применяет recoverFunction для восстановления после выброса исключения, если это неудачное выполнение,
     * в противном случае возвращает результирующее значение выполнения, обернутое в Try.
     * <p>
     * Этот метод по своему принципу действия аналогичен {@link Optional#flatMap(Function)} с {@link Try#recover(ThrowableFunction)} одновременно
     *
     * @return новый Try, содержащий ошибку, в случае, если выполнение завершилось ошибкой
     * или текущее результирующее значение выполнения, в случае успешного выполнения
     */
    Try<T> recoverWith(ThrowableFunction<? super Throwable, Try<T>> recoverFunction);


}

package me.t.gilllepulla.handling.primitive;

import me.t.gilllepulla.handling.object.Try;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.*;

/**
 *
 * @author Sergey Lyashko
 */
public interface TryInt {

    static TryInt of(IntThrowableOperation operation) {
        try {
            return new SuccessInt(operation.execute());
        } catch (Throwable e) {
            return new FailInt(e);
        }
    }

    /**
     * @return true если исходная операция может быть выполнена успешно
     */
    boolean isSuccess();

    /**
     * @return обернутое в Optional результирующее значение, если это успешное выполнение или пустое, в случае ошибки
     */
    OptionalInt optional();

    /**
     * @return результирующее значение, если произошло успешное выполнение
     * @throws Throwable исходное исключение
     */
    int get() throws Throwable;

    /**
     * @return результирующее значение, если произошло успешное выполнение
     * @throws RuntimeException обернутое в RuntimeException исходное исключение в случае ошибки
     */
    int getUnchecked();

    /**
     * @return результирующее значение, если это успешное выполнение, или значение по умолчанию, в случае ошибки
     */
    int getOrElse(int defaultValue);

    /**
     * @return результирующее значение, если это успешное выполнение, иначе результат, произведенный данным поставщиком
     */
    int getOrElse(IntSupplier supplier);

    /**
     * @return результирующее значение, если выполнение было успешное или исключение, передаваемое supplier-ом
     * @throws Throwable производится поставщиком исключения, передаваемого в сигнатуре метода
     */
    <X extends Throwable> int getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * @return текущее результирующее значение обернуто Try, если оно выполнено успешно, иначе ничего не делает
     * @throws E если действие в consumer вызывает исключение
     */
    <E extends Throwable> TryInt onSuccess(IntThrowableConsumer<E> consumer) throws E;

    /**
     * Если значение присутствует и соответствует заданному предикату, возвращает Try, описывающий значение,
     * в противном случае возвращает Try, содержащий NoSuchElementException.
     *
     * @return результирующее значение или ошибку, обернутую Try
     * @throws NoSuchElementException если предикат не соответствует значению
     */
    TryInt filter(IntPredicate predicate);

    /**
     * Применяет данную функцию к результирующему значению в случае успеха, в противном случае возвращает это значение с ошибкой.
     * Если передаваемая параметром функция выбрасывает исключение в случае ошибки, то возвращается обернутая в Try ошибка.
     * <p>
     * Этот метод по своему принципу действия аналогичен {@link Optional#map(Function)}
     *
     * @param mapper функция преобразования
     */
    <U> Try<U> mapToObj(IntThrowableFunction<? extends U> mapper);

}
package org.fail;

/**
 * Represents an operation that may potentially fail with an exception
 * <p>
 * ThrowableOperation — функциональный интерфейс, представляющий операцию,
 * возвращающую результат типа T или вызывающую исключение;
 */
@FunctionalInterface
interface ThrowableOperation<T> {

    T execute() throws Throwable;
}

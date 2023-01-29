package me.t.gilllepulla.exception.handling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sergey Lyashko
 */
class TryTest {

    @Test
    void success() {
        Try<Integer> tryParse = Try.of(() -> Integer.parseInt("42"));
        assertTrue(tryParse.isSuccess());
        assertFalse(tryParse.optional().isEmpty());
        assertEquals(42, tryParse.getUnchecked());
        assertNotEquals(0, tryParse.getUnchecked());
    }

    @Test
    void failure() {
        Try<Integer> tryParse = Try.of(() -> Integer.parseInt("test42"));
        assertFalse(tryParse.isSuccess());
        assertTrue(tryParse.optional().isEmpty());
        assertThrows(RuntimeException.class, tryParse::getUnchecked);
    }

    @Test
    void get() {
        Try<Integer> tryParse = Try.of(() -> Integer.parseInt("4242"));
        assertEquals(4242, tryParse.getOrElse(2));
        assertEquals(4242, tryParse.getOrElse(() -> 10 * 20 * 30));
        assertEquals(4242, tryParse.getOrElseThrow(IllegalArgumentException::new));
        assertDoesNotThrow(() -> tryParse.getOrElseThrow(IllegalArgumentException::new));
    }

    @Test
    void orElse() {
        Try<Integer> tryParse = Try.of(() -> Integer.parseInt("test42"));
        assertEquals(0, tryParse.getOrElse(0));
        assertNotEquals(42, tryParse.getOrElse(0));
        assertEquals(6000, tryParse.getOrElse(() -> 10 * 20 * 30));
        assertThrows(IllegalArgumentException.class, () -> tryParse.getOrElseThrow(IllegalArgumentException::new));
    }

    @Test
    void filter() {
        Assertions.assertEquals(100, Try.of(() -> Integer.parseInt("100"))
                .filter(value -> value > 50)
                .getUnchecked());

        assertThrows(RuntimeException.class, () -> Try.of(() -> Integer.parseInt("49"))
                .filter(value -> value > 50)
                .getUnchecked());

        assertThrows(RuntimeException.class, () -> Try.of(() -> Integer.parseInt("test100"))
                .filter(value -> value > 50)
                .getUnchecked());
    }

    @Test
    void map() {
        assertEquals(5000, Try.of(() -> Integer.parseInt("500"))
                .map(value -> value * 10)
                .getOrElse(0));

        assertEquals(0, Try.of(() -> Integer.parseInt("500K"))
                .map(value -> value * 10)
                .getOrElse(0));

        assertEquals(0, Try.of(() -> Integer.parseInt("500"))
                .map(TryTest::throwableNPE)
                .getOrElse(0));
    }

    @Test
    void onFail() {
        assertThrowsExactly(IllegalArgumentException.class, () -> Try.of(() -> Integer.parseInt("test42"))
                .onSuccess(value -> Assertions.assertEquals(42, value))
                .onFail(e -> assertInstanceOf(Throwable.class, e))
                .getOrElseThrow(IllegalArgumentException::new));

        var result = Try.of(() -> Integer.parseInt("test42"))
                .onFail(e -> assertInstanceOf(Throwable.class, e))
                .filter(x -> x >= 1)
                .getOrElse(1);
        Assertions.assertEquals(1, result);
    }

    @Test
    void recover() {
        Integer result = Try.of(() -> 1000)
                .flatMap(value -> Try.of(() -> throwableException(value))
                        .onFail(e -> assertInstanceOf(Throwable.class, e))
                        .recover(e -> value - 100))
                .getOrElse(0);
        assertEquals(900, result);
    }

    @Test
    void recoverWith() {
        Integer result = Try.of(() -> Integer.parseInt("test42"))
                .recoverWith(e -> Try.of(() -> Integer.parseInt("42")))
                .getOrElse(0);
        assertEquals(42, result);
    }

    private static int throwableException(Object o) {
        throw new IllegalArgumentException();
    }

    private static Integer throwableNPE(Object o) {
        throw new NullPointerException();
    }

    @Test
    void stream() {
        assertEquals(420, Try.of(() -> Integer.parseInt("42"))
                .stream()
                .map(value -> value * 10)
                .findAny()
                .orElse(0));

        assertEquals(0, Try.of(() -> Integer.parseInt("test42"))
                .stream()
                .map(value -> value * 10)
                .findAny()
                .orElse(0));
    }
}

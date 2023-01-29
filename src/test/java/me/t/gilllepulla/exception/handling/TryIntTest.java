package me.t.gilllepulla.exception.handling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TryIntTest {

    @Test
    void success() {
        TryInt tryParse = TryInt.of(() -> Integer.parseInt("42"));
        assertTrue(tryParse.isSuccess());
        assertFalse(tryParse.optional().isEmpty());
        assertEquals(42, tryParse.getUnchecked());
        assertNotEquals(0, tryParse.getUnchecked());
    }

    @Test
    void map() {
        assertEquals(5000, TryInt.of(() -> Integer.parseInt("500"))
                .mapToObj(value -> value * 10)
                .getOrElse(0));

        assertEquals(0, TryInt.of(() -> Integer.parseInt("500K"))
                .mapToObj(value -> value * 10)
                .getOrElse(0));

        assertEquals(0, TryInt.of(() -> Integer.parseInt("500"))
                .mapToObj(TryIntTest::throwableNPE)
                .getOrElse(0));
    }

    private static Integer throwableNPE(Object o) {
        throw new NullPointerException();
    }

}

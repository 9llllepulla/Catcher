package me.t.gilllepulla.functional.patterns.bridge;

import java.util.function.Consumer;

@FunctionalInterface
public interface Bridge<T> {

    void action(Consumer<T> consumer);
}

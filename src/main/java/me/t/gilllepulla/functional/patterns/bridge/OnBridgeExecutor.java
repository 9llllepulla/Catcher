package me.t.gilllepulla.functional.patterns.bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class OnBridgeExecutor<T, R> {

    private final Bridge<T> bridge;

    protected OnBridgeExecutor(Bridge<T> bridge) {
        this.bridge = bridge;
    }

    protected R map(Function<T, R> mapping) {
        List<R> rs = new ArrayList<>();
        bridge.action(t -> {
            R apply = mapping.apply(t);
            rs.add(apply);
        });
        return rs.get(0);
    }

    protected void action(Consumer<T> consumer) {
        bridge.action(consumer);
    }

}

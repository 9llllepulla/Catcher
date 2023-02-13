package me.t.gilllepulla.functional.patterns.bridge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BridgeTest {

    @Test
    void mapping() {

        Circle whiteCircle = new Circle(consumer -> consumer.accept("white"));
        String mappingWhiteCircle = whiteCircle.map(colorName -> "color " + colorName);
        assertEquals("color white", mappingWhiteCircle);

        Circle blackCircle = new Circle(consumer -> consumer.accept("black"));
        String mappingBlackCircle = blackCircle.map(colorName -> "too color " + colorName);
        assertEquals("too color black", mappingBlackCircle);

        Racoon wolfgang = new Racoon("Вольфганг", 10);
        WildAnimalMapper animalMapper = new WildAnimalMapper(consumer -> consumer.accept(wolfgang));
        Squirrel squirrel = animalMapper.map(racoon -> new Squirrel(racoon.name(), racoon.age()));
        assertEquals(squirrel.age(), wolfgang.age());
        assertEquals(squirrel.name(), wolfgang.name());
    }

}

class Circle extends OnBridgeExecutor<String, String> {

    public Circle(Bridge<String> bridge) {
        super(bridge);
    }
}

class WildAnimalMapper extends OnBridgeExecutor<Racoon, Squirrel> {
    protected WildAnimalMapper(Bridge<Racoon> bridge) {
        super(bridge);
    }
}

record Racoon(String name, Integer age) {
}

record Squirrel(String name, Integer age) {
}

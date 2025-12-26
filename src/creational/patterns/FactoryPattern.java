package creational.patterns;

import java.util.HashMap;
import java.util.Map;

public class FactoryPattern {
    private enum ENGINE_TYPE{CAR, BIKE};

    public static void main(String[] args) {
        Engine engineType = EngineFactory.getEngineType(ENGINE_TYPE.CAR);
        engineType.engine();
    }

    private static class EngineFactory {
        private static Map<ENGINE_TYPE, Engine> engineMap = new HashMap<>();
        static {
            engineMap.putIfAbsent(ENGINE_TYPE.CAR, new Car());
            engineMap.putIfAbsent(ENGINE_TYPE.BIKE, new Bike());
        }

        public static Engine getEngineType(ENGINE_TYPE engineType) {
            return engineMap.getOrDefault(engineType, null);
        }
    }

    public static class Car implements Engine {
        public void engine() {
            System.out.println("Car engine");
        }
    }
    public static class Bike implements Engine {
        public void engine() {
            System.out.println("Bike Engine");
        }
    }
    public interface Engine {
        void engine();
    }
}

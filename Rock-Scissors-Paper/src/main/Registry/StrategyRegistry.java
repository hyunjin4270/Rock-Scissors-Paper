package main.Registry;

import main.domain.strategy.Strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StrategyRegistry {
    private final Map<String, Strategy> strategyMap;

    public StrategyRegistry(Map<String, Strategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public Strategy get(String name) {
        if (!strategyMap.containsKey(name)) throw new IllegalArgumentException("not a valid strategy name");
        return strategyMap.get(name);
    }

    public Set<String> getAvailableRules() {
        return strategyMap.keySet();
    }
}

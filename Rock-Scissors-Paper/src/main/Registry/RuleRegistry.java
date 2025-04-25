package main.Registry;

import main.domain.rule.RpsRule;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RuleRegistry {
    private final Map<String, RpsRule> ruleMap = new HashMap<>();

    public void register(RpsRule rule) {
        ruleMap.put(rule.getName(), rule);
    }
    public RpsRule get(String name) {
        if (!ruleMap.containsKey(name)) throw new IllegalArgumentException("not a valid rule name");
        return ruleMap.get(name);
    }

    public Set<String> getAvailableRules() {
        return ruleMap.keySet();
    }
}

package org.exps.deadlock;

import java.util.HashMap;
import java.util.Map;

public class FruitResources {
    private static final Orange ORANGE = new Orange();
    private static final Banana BANANA = new Banana();

    private static final Map<String, Object> FRUITS = new HashMap();
    {
        FRUITS.put("ORANGE", ORANGE);
        FRUITS.put("BANANA", BANANA);
    }

    public Object getFruit(String name) {
        return FRUITS.get(name);
    }

    /**
     * Workaround to have a pretty output on thread dump
     * 	-  blocked on org.exps.deadlock.FruitResources$Banana@xxx
     * 	-  locked org.exps.deadlock.FruitResources$Orange@xxxx
     */
    private static class Banana {
        @Override
        public String toString() {
            return "BANANA";
        }
    }
    /**
     * Workaround to have a pretty output on thread dump
     * 	-  blocked on org.exps.deadlock.FruitResources$Banana@xxx
     * 	-  locked org.exps.deadlock.FruitResources$Orange@xxxx
     */
    private static class Orange {
        @Override
        public String toString() {
            return "ORANGE";
        }
    }
}

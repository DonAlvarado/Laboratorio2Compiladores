package com.compis;

import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTabla {

    private Map<String, Symbol> table = new LinkedHashMap<>();

    public void add(Symbol symbol) {
        table.put(symbol.name, symbol);
    }

    public Symbol get(String name) {
        return table.get(name);
    }

    public boolean exists(String name) {
        return table.containsKey(name);
    }

    public void print() {
        System.out.println("VARIABLE | TIPO | VALOR");
        for (Symbol s : table.values()) {
            System.out.println(s.name + " | " + s.type + " | " + s.value);
        }
    }
}
package org.courses.leaf;

import org.courses.Expression;

public class Constant implements Expression {
    private int value;

    public Constant(int value) {
        this.value = value;
    }

    public int calculate() {
        return value;
    }
}

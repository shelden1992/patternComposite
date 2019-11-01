package org.courses.composite;

import org.courses.Expression;

public class UnaryExpression implements Expression {
    protected Expression right;

    public int calculate() {
        return right.calculate();
    }

    public UnaryExpression(Expression right) {
        this.right = right;
    }
}

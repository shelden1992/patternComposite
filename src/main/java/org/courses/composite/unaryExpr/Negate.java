package org.courses.composite.unaryExpr;

import org.courses.Expression;
import org.courses.composite.UnaryExpression;

public class Negate extends UnaryExpression {

    public Negate(Expression right) {
        super(right);
    }

    @Override
    public int calculate() {
        return -right.calculate();
    }
}

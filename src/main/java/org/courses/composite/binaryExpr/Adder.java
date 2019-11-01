package org.courses.composite.binaryExpr;

import org.courses.Expression;

public class Adder extends BinaryExpression {

    public Adder(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int calculate() {
        return left.calculate() + right.calculate();
    }
}

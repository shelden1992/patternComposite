package org.courses.composite.binaryExpr;

import org.courses.Expression;
import org.courses.composite.binaryExpr.BinaryExpression;

public class Subtracter extends BinaryExpression {

    public Subtracter(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int calculate() {
        return left.calculate() - right.calculate();
    }

}

package org.courses.composite.binaryExpr;

import org.courses.Expression;
import org.courses.composite.UnaryExpression;

public class BinaryExpression extends UnaryExpression {
    protected Expression left;

    public BinaryExpression(Expression left, Expression right) {
        super(right);
        this.left = left;
    }
}

package org.courses.parsing;

import org.apache.log4j.Logger;
import org.courses.composite.binaryExpr.Adder;
import org.courses.composite.binaryExpr.Divider;
import org.courses.composite.binaryExpr.Multiplier;
import org.courses.composite.binaryExpr.Subtracter;
import org.courses.leaf.Constant;

import java.util.Arrays;
import java.util.Stack;

public class EvaluateString {
    public static final Logger LOG = Logger.getLogger(EvaluateString.class);

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    //new Adder(new Multiplier(new Adder(new Constant(1), new Constant(2)), new Constant(4)), new Multiplier(new Adder(new Constant(1), new Constant(2)), new Constant(4))).calculate();
    public static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return new Adder(new Constant(b), new Constant(a)).calculate();
            case '-':
                return new Subtracter(new Constant(b), new Constant(a)).calculate();
            case '*':
                return new Multiplier(new Constant(b), new Constant(a)).calculate();
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return new Divider(new Constant(b), new Constant(a)).calculate();
        }
        return 0;
    }

    public int evaluate(String expression) {
        char[] tokens = expression.toCharArray();
        LOG.debug("All tokens " + Arrays.toString(tokens));
        Stack<Integer> values = new Stack<>();

        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') {
                LOG.debug("String contain space");
                continue;
            }

            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer sbuf = new StringBuffer();
                while (i < tokens.length) {
                    if (tokens[i] >= '0' && tokens[i] <= '9')
                        sbuf.append(tokens[i++]);
                    else {
                        LOG.debug("Operation to int " + sbuf.toString());
                        i--;
                        break;
                    }

                }
                values.push(Integer.parseInt(sbuf.toString()));
            } else if (tokens[i] == '(') {
                LOG.debug("Open hooks in position " + tokens[i]);
                ops.push(tokens[i]);
            } else if (tokens[i] == ')') {
                LOG.debug("Close hooks in position " + tokens[i]);
                while (ops.peek() != '(') {
                    Integer valuesIntoHooks = values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                    LOG.debug("Values into hooks = " + valuesIntoHooks);
                }
                ops.pop();
            } else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                ops.push(tokens[i]);
            }
        }

        while (!ops.empty()) {
            Character pop = ops.pop();
            Integer pop1 = values.pop();
            Integer pop2 = values.pop();
            values.push(applyOp(pop, pop1, pop2));
            LOG.debug(String.format("Final values left %s operation \"%s\" values right %s", pop1, pop, pop2));
        }

        return values.pop();
    }
}

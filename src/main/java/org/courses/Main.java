package org.courses;

import org.courses.calculate.Calculation;

public class Main {

    public static void main(String[] args) {
        System.out.println(Calculation.getInstance().calculateAndGet("(1+2)*4+5*(3+6)"));
    }
}

package com.example.ejemplos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculadora {
    public double suma(double a, double b) {
        BigDecimal rslt = BigDecimal.valueOf(a + b);
        return rslt.setScale(15, RoundingMode.HALF_DOWN).doubleValue();
    }

    public double divide(double a, double b) {
        Utils.isZero(b);
        return a / b;
    }

    public double divide(int a, int b) {
        Utils.isZero(b);
        return a / b;
    }
}

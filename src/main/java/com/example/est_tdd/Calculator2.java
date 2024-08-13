package com.example.est_tdd;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Calculator2 {
    public int add(int a, int b) {
        if (willAdditionOverflow(a, b)) {
            if (a > 0) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        return a + b;
    }

    private boolean willAdditionOverflow(int a, int b) {
        if (b > 0 && a > Integer.MAX_VALUE - b) {
            return true;
        }
        if (b < 0 && a < Integer.MIN_VALUE - b) {
            return true;
        }
        return false;
    }


}

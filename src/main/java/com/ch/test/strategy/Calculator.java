package com.ch.test.strategy;

/**
 * @Description 策略枚举
 * @Author caihao
 * @Date 2019/9/6 15:44
 * @Param
 * @Return
 */
public enum Calculator {

    ADD("+"){
        @Override
        public int math(int a, int b) {
            return a + b;
        }
    },
    SUB("-"){
        @Override
        public int math(int a, int b) {
            return a - b;
        }
    },
    MULTI("×"){
        @Override
        public int math(int a, int b) {
            return a * b;
        }
    },
    DEVISED("÷"){
        @Override
        public int math(int a, int b) {
            return a / b;
        }
    };

    private String symbol;

    Calculator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract int math(int a, int b);
}

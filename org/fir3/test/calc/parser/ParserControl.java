package org.fir3.test.calc.parser;

import org.fir3.test.calc.expression.Expression;

import java.util.Queue;

public interface ParserControl {
    void fail(String reason);
    void push(Expression expression);
    void push(State state);
    Queue<Expression> reduce(int states, int expressions);
    void shift();
    void succeed();
}

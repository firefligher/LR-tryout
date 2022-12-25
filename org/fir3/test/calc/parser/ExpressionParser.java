package org.fir3.test.calc.parser;

import org.fir3.test.calc.expression.Expression;
import org.fir3.test.calc.token.Token;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public final class ExpressionParser {
    private class ControlImpl implements ParserControl {
        private boolean complete;

        @Override
        public void fail(String reason) {
            throw new IllegalStateException(reason);
        }

        @Override
        public void push(Expression expression) {
            ExpressionParser.this.results.push(expression);
        }

        @Override
        public void push(State state) {
            ExpressionParser.this.states.push(state);
        }

        @Override
        public Queue<Expression> reduce(int states, int expressions) {
            var result = new ArrayDeque<Expression>();

            for (; states > 0; states--) {
                ExpressionParser.this.states.pop();
            }

            for (; expressions > 0; expressions--) {
                result.addFirst(ExpressionParser.this.results.pop());
            }

            return result;
        }

        @Override
        public void shift() {
            this.complete = true;
        }

        @Override
        public void succeed() {
            ExpressionParser.this.accepting = true;
        }
    }

    private final Stack<Expression> results;
    private final Stack<State> states;
    private boolean accepting;

    public ExpressionParser() {
        this.results = new Stack<>();
        this.states = new Stack<>();
        this.states.push(State.Initial);
    }

    public Expression getResult() {
        if (!this.accepting) {
            throw new IllegalStateException("Not complete yet");
        }

        return this.results.peek();
    }

    public void update(Token token) {
        var ctrl = new ControlImpl();
        var processToken = true;

        while (!ctrl.complete) {
            var stateCount = this.states.size();
            var state = this.states.peek();

            if (processToken) {
                token.accept(state.getTokenStrategy(), ctrl);
            } else {
                this.results.peek().accept(
                        state.getExpressionStrategy().get(),
                        ctrl
                );
            }

            processToken = this.states.size() > stateCount;
        }
    }

    public void finish() {
        var ctrl = new ControlImpl();
        var processToken = true;

        while (this.results.size() > 1 || !this.accepting) {
            var stateCount = this.states.size();
            var state = this.states.peek();

            if (processToken) {
                state.getTokenStrategy().visitEnd(ctrl);
            } else {
                this.results.peek().accept(
                        state.getExpressionStrategy().get(),
                        ctrl
                );
            }

            processToken = this.states.size() > stateCount;
        }
    }
}

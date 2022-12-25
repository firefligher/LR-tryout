package org.fir3.test.calc;

import org.fir3.test.calc.parser.ExpressionParser;
import org.fir3.test.calc.token.*;

import java.util.ArrayDeque;
import java.util.Arrays;

public final class Main {
    public static void main(String[] args) {
        var tokens = new ArrayDeque<>(
                Arrays.asList(
                        new Constant(17),
                        new Symbol(SymbolType.Plus),
                        new Keyword(KeywordType.Sqrt),
                        new Symbol(SymbolType.LeftParenthesis),
                        new Constant(8.5),
                        new Symbol(SymbolType.RightParenthesis),
                        new Symbol(SymbolType.Asterisk),
                        new Constant(8.5),
                        new Symbol(SymbolType.Asterisk),
                        new Symbol(SymbolType.LeftParenthesis),
                        new Constant(47.3),
                        new Symbol(SymbolType.Plus),
                        new Constant(50),
                        new Symbol(SymbolType.RightParenthesis),
                        new Symbol(SymbolType.Plus),
                        new Constant(7)
                )
        );

        var parser = new ExpressionParser();

        while (!tokens.isEmpty()) {
            parser.update(tokens.pop());
        }

        parser.finish();
        System.out.println(parser.getResult());
    }
}

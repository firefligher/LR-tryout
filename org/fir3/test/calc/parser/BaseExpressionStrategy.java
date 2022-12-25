package org.fir3.test.calc.parser;

import org.fir3.test.calc.expression.AbstractExpressionVisitor;
import org.fir3.test.calc.expression.Expression;

abstract class BaseExpressionStrategy
        extends AbstractExpressionVisitor<ParserControl> {
    @Override
    protected final void visitOther(
            Expression expression,
            ParserControl parserControl
    ) {
        parserControl.fail("Unexpected token type");
    }
}

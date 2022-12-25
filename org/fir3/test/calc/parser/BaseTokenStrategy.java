package org.fir3.test.calc.parser;

import org.fir3.test.calc.token.AbstractTokenVisitor;
import org.fir3.test.calc.token.Token;

abstract class BaseTokenStrategy
        extends AbstractTokenVisitor<ParserControl>
        implements TokenStrategy {

    @Override
    public void visitEnd(ParserControl parserControl) {
        this.visitOther(null, parserControl);
    }

    @Override
    protected final void visitOther(Token token, ParserControl parserControl) {
        parserControl.fail("Unexpected token type");
    }
}

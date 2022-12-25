package org.fir3.test.calc.parser;

import org.fir3.test.calc.token.TokenVisitor;

public interface TokenStrategy extends TokenVisitor<ParserControl> {
    void visitEnd(ParserControl parserControl);
}

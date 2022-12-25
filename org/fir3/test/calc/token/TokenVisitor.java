package org.fir3.test.calc.token;

public interface TokenVisitor<TUserData> {
    void visit(Constant constant, TUserData userData);
    void visit(Keyword keyword, TUserData userData);
    void visit(Symbol symbol, TUserData userData);
}

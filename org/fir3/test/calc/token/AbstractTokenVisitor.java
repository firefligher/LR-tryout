package org.fir3.test.calc.token;

public abstract class AbstractTokenVisitor<TUserData>
        implements TokenVisitor<TUserData> {

    @Override
    public void visit(Constant constant, TUserData userData) {
        this.visitOther(constant, userData);
    }

    @Override
    public void visit(Keyword keyword, TUserData userData) {
        this.visitOther(keyword, userData);
    }

    @Override
    public void visit(Symbol symbol, TUserData userData) {
        this.visitOther(symbol, userData);
    }

    protected abstract void visitOther(Token token, TUserData userData);
}

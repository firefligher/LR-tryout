package org.fir3.test.calc.expression;

public abstract class AbstractExpressionVisitor<TUserData>
        implements ExpressionVisitor<TUserData> {
    @Override
    public void visit(Addition addition, TUserData userData) {
        this.visitOther(addition, userData);
    }

    @Override
    public void visit(Constant constant, TUserData userData) {
        this.visitOther(constant, userData);
    }

    @Override
    public void visit(Multiplication multiplication, TUserData userData) {
        this.visitOther(multiplication, userData);
    }

    @Override
    public void visit(SquareRoot squareRoot, TUserData userData) {
        this.visitOther(squareRoot, userData);
    }

    protected abstract void visitOther(
            Expression expression,
            TUserData userData
    );
}

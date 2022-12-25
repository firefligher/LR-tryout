package org.fir3.test.calc.expression;

public interface ExpressionVisitor<TUserData> {
    void visit(Addition addition, TUserData userData);
    void visit(Constant constant, TUserData userData);
    void visit(Multiplication multiplication, TUserData userData);
    void visit(SquareRoot squareRoot, TUserData userData);
}

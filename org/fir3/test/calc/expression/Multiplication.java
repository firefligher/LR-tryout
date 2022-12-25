package org.fir3.test.calc.expression;

public final class Multiplication implements Expression {
    private final Expression leftFactor;
    private final Expression rightFactor;

    public Multiplication(Expression leftFactor, Expression rightFactor) {
        this.leftFactor = leftFactor;
        this.rightFactor = rightFactor;
    }

    @Override
    public <TUserData> void accept(
            ExpressionVisitor<TUserData> visitor,
            TUserData userData
    ) {
        visitor.visit(this, userData);
    }

    public Expression getLeftFactor() {
        return this.leftFactor;
    }

    public Expression getRightFactor() {
        return this.rightFactor;
    }

    @Override
    public String toString() {
        return "(" + this.leftFactor + " * " + this.rightFactor + ")";
    }
}

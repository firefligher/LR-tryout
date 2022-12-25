package org.fir3.test.calc.expression;

public final class SquareRoot implements Expression {
    private final Expression operand;

    public SquareRoot(Expression operand) {
        this.operand = operand;
    }

    @Override
    public <TUserData> void accept(
            ExpressionVisitor<TUserData> visitor,
            TUserData userData
    ) {
        visitor.visit(this, userData);
    }

    public Expression getOperand() {
        return this.operand;
    }

    @Override
    public String toString() {
        return "sqrt(" + this.operand + ")";
    }
}

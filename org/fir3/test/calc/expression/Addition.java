package org.fir3.test.calc.expression;

public final class Addition implements Expression {
    private final Expression leftSummand;
    private final Expression rightSummand;

    public Addition(Expression leftSummand, Expression rightSummand) {
        this.leftSummand = leftSummand;
        this.rightSummand = rightSummand;
    }

    @Override
    public <TUserData> void accept(
            ExpressionVisitor<TUserData> visitor,
            TUserData userData
    ) {
        visitor.visit(this, userData);
    }

    public Expression getLeftSummand() {
        return this.leftSummand;
    }

    public Expression getRightSummand() {
        return this.rightSummand;
    }

    @Override
    public String toString() {
        return "(" + this.leftSummand + " + " + this.rightSummand + ")";
    }
}

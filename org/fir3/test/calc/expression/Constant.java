package org.fir3.test.calc.expression;

public final class Constant implements Expression {
    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public <TUserData> void accept(
            ExpressionVisitor<TUserData> visitor,
            TUserData userData
    ) {
        visitor.visit(this, userData);
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}

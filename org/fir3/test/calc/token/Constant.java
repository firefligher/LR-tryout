package org.fir3.test.calc.token;

public final class Constant implements Token {
    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public <TUserData> void accept(
            TokenVisitor<TUserData> visitor,
            TUserData userData
    ) {
        visitor.visit(this, userData);
    }

    public double getValue() {
        return this.value;
    }
}

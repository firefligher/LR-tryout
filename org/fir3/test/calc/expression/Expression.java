package org.fir3.test.calc.expression;

public interface Expression {
    <TUserData> void accept(
            ExpressionVisitor<TUserData> visitor,
            TUserData userData
    );
}

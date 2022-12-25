package org.fir3.test.calc.token;

public interface Token {
    <TUserData> void accept(
            TokenVisitor<TUserData> visitor,
            TUserData userData
    );
}

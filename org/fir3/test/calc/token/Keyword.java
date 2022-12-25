package org.fir3.test.calc.token;

public final class Keyword implements Token {
    private final KeywordType type;

    public Keyword(KeywordType type) {
        this.type = type;
    }

    @Override
    public <TUserData> void accept(
            TokenVisitor<TUserData> visitor,
            TUserData userData
    ) {
        visitor.visit(this, userData);
    }

    public KeywordType getType() {
        return this.type;
    }
}

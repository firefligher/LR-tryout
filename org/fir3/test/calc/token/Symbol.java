package org.fir3.test.calc.token;

public final class Symbol implements Token {
    private final SymbolType type;

    public Symbol(SymbolType type) {
        this.type = type;
    }

    @Override
    public <TUserData> void accept(
            TokenVisitor<TUserData> visitor,
            TUserData userData
    ) {
        visitor.visit(this, userData);
    }

    public SymbolType getType() {
        return this.type;
    }
}

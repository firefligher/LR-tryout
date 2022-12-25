package org.fir3.test.calc.parser;

import org.fir3.test.calc.expression.*;
import org.fir3.test.calc.token.*;
import org.fir3.test.calc.token.Constant;

import java.util.Optional;

enum State {
    Addition(
            new AbstractExpressionVisitor<>() {
                @Override
                protected void visitOther(
                        Expression expression,
                        ParserControl parserControl
                ) {
                    parserControl.push(AdditionReduction);
                }
            },
            new BaseTokenStrategy() {
                @Override
                public void visit(
                        org.fir3.test.calc.token.Constant constant,
                        ParserControl parserControl
                ) {
                    parserControl.push(
                            new org.fir3.test.calc.expression.Constant(
                                    constant.getValue()
                            )
                    );

                    parserControl.push(AdditionReduction);
                    parserControl.shift();
                }

                @Override
                public void visit(
                        Keyword keyword,
                        ParserControl parserControl
                ) {
                    if (keyword.getType() != KeywordType.Sqrt) {
                        parserControl.fail("Unexpected keyword");
                        return;
                    }

                    parserControl.shift();
                    parserControl.push(Sqrt);
                }

                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    if (symbol.getType() != SymbolType.LeftParenthesis) {
                        parserControl.fail("Unexpected symbol");
                        return;
                    }

                    parserControl.shift();
                    parserControl.push(LeftParenthesis);
                }
            }
    ),
    AdditionReduction(
            new AbstractExpressionVisitor<>() {
                @Override
                protected void visitOther(
                        Expression expression,
                        ParserControl parserControl) {
                    var summands = parserControl.reduce(3, 2);
                    var leftSummand = summands.remove();
                    var rightSummand = summands.remove();

                    parserControl.push(
                            new Addition(leftSummand, rightSummand)
                    );
                }
            },
            new BaseTokenStrategy() {
                private void reduce(ParserControl ctrl) {
                    var summands = ctrl.reduce(3, 2);
                    var leftSummand = summands.remove();
                    var rightSummand = summands.remove();

                    ctrl.push(
                            new Addition(leftSummand, rightSummand)
                    );

                    ctrl.succeed();
                }

                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    SymbolType symbolType = symbol.getType();

                    if (symbolType == SymbolType.Asterisk) {
                        parserControl.push(OperatorOrEnd);
                        return;
                    }

                    if (
                            symbolType == SymbolType.Plus ||
                                    symbolType == SymbolType.RightParenthesis
                    ) {
                        this.reduce(parserControl);
                        return;
                    }

                    parserControl.fail("Unexpected symbol");
                }

                @Override
                public void visitEnd(ParserControl parserControl) {
                    this.reduce(parserControl);
                }
            }
    ),
    Initial(
            new AbstractExpressionVisitor<>() {
                @Override
                protected void visitOther(
                        Expression expression,
                        ParserControl parserControl
                ) {
                    parserControl.push(OperatorOrEnd);
                }
            },
            new BaseTokenStrategy() {
                @Override
                public void visit(
                        org.fir3.test.calc.token.Constant constant,
                        ParserControl parserControl
                ) {
                    parserControl.push(
                            new org.fir3.test.calc.expression.Constant(
                                    constant.getValue()
                            )
                    );

                    parserControl.push(OperatorOrEnd);
                    parserControl.shift();
                }

                @Override
                public void visit(
                        Keyword keyword,
                        ParserControl parserControl
                ) {
                    if (keyword.getType() == KeywordType.Sqrt) {
                        parserControl.push(Sqrt);
                        parserControl.shift();
                        return;
                    }

                    parserControl.fail("Unexpected keyword");
                }

                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    SymbolType type = symbol.getType();

                    if (type == SymbolType.LeftParenthesis) {
                        parserControl.shift();
                        parserControl.push(LeftParenthesis);
                        return;
                    }

                    parserControl.fail("Unexpected symbol");
                }
            }
    ),
    LeftParenthesis(
            new AbstractExpressionVisitor<>() {
                @Override
                protected void visitOther(
                        Expression expression,
                        ParserControl parserControl
                ) {
                    parserControl.push(RightParenthesis);
                }
            },
            new BaseTokenStrategy() {
                @Override
                public void visit(
                        org.fir3.test.calc.token.Constant constant,
                        ParserControl parserControl
                ) {
                    parserControl.push(
                            new org.fir3.test.calc.expression.Constant(
                                    constant.getValue()
                            )
                    );

                    parserControl.push(OperatorOrEnd);
                    parserControl.shift();
                }

                @Override
                public void visit(
                        Keyword keyword,
                        ParserControl parserControl
                ) {
                    if (keyword.getType() != KeywordType.Sqrt) {
                        parserControl.fail("Unexpected keyword");
                        return;
                    }

                    parserControl.shift();
                    parserControl.push(Sqrt);
                }

                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    SymbolType type = symbol.getType();

                    if (type == SymbolType.LeftParenthesis) {
                        parserControl.shift();
                        parserControl.push(LeftParenthesis);
                        return;
                    }

                    parserControl.fail("Unexpected symbol");
                }
            }
    ),
    Multiplication(
            new AbstractExpressionVisitor<>() {
                @Override
                protected void visitOther(
                        Expression expression,
                        ParserControl parserControl
                ) {
                    parserControl.push(MultiplicationReduction);
                }
            },
            new BaseTokenStrategy() {
                @Override
                public void visit(
                        org.fir3.test.calc.token.Constant constant,
                        ParserControl parserControl
                ) {
                    parserControl.push(
                            new org.fir3.test.calc.expression.Constant(
                                    constant.getValue()
                            )
                    );

                    parserControl.push(MultiplicationReduction);
                    parserControl.shift();
                }

                @Override
                public void visit(
                        Keyword keyword,
                        ParserControl parserControl
                ) {
                    if (keyword.getType() != KeywordType.Sqrt) {
                        parserControl.fail("Unexpected keyword");
                        return;
                    }

                    parserControl.shift();
                    parserControl.push(Sqrt);
                }

                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    if (symbol.getType() != SymbolType.LeftParenthesis) {
                        parserControl.fail("Unexpected symbol");
                        return;
                    }

                    parserControl.shift();
                    parserControl.push(LeftParenthesis);
                }
            }
    ),
    MultiplicationReduction(
            new BaseExpressionStrategy() {
                @Override
                public void visit(
                        Multiplication multiplication,
                        ParserControl parserControl
                ) {
                    var factors = parserControl.reduce(3, 2);
                    var leftFactor = factors.remove();
                    var rightFactor = factors.remove();

                    parserControl.push(
                            new Multiplication(leftFactor, rightFactor)
                    );
                }
            },
            new BaseTokenStrategy() {
                private void reduce(ParserControl ctrl) {
                    var factors = ctrl.reduce(3, 2);
                    var leftFactor = factors.remove();
                    var rightFactor = factors.remove();

                    ctrl.push(
                            new Multiplication(leftFactor, rightFactor)
                    );
                }

                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    SymbolType symbolType = symbol.getType();

                    if (
                            symbolType == SymbolType.Asterisk ||
                                    symbolType == SymbolType.Plus ||
                                    symbolType == SymbolType.RightParenthesis
                    ) {
                        this.reduce(parserControl);
                        return;
                    }

                    parserControl.fail("Unexpected symbol");
                }

                @Override
                public void visitEnd(ParserControl parserControl) {
                    this.reduce(parserControl);
                    parserControl.succeed();
                }
            }
    ),
    OperatorOrEnd(
            new BaseTokenStrategy() {
                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    switch (symbol.getType()) {
                        case Asterisk -> {
                            parserControl.push(Multiplication);
                            parserControl.shift();
                        }
                        case Plus -> {
                            parserControl.push(Addition);
                            parserControl.shift();
                        }
                        case RightParenthesis -> parserControl.push(
                                parserControl.reduce(1, 1).remove()
                        );
                        default -> parserControl.fail("Unexpected symbol");
                    }
                }

                @Override
                public void visitEnd(ParserControl parserControl) {
                    parserControl.succeed();
                }
            }
    ),
    ParenthesisReduction(
            new BaseTokenStrategy() {
                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    if (
                            symbol.getType() != SymbolType.Plus &&
                                    symbol.getType() != SymbolType.Asterisk
                    ) {
                        parserControl.fail("Unexpected symbol");
                        return;
                    }

                    parserControl.push(parserControl.reduce(3, 1).remove());
                }
            }
    ),
    RightParenthesis(
            new BaseTokenStrategy() {
                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    if (symbol.getType() != SymbolType.RightParenthesis) {
                        parserControl.fail("Unexpected symbol");
                        return;
                    }

                    parserControl.shift();
                    parserControl.push(ParenthesisReduction);
                }
            }
    ),
    Sqrt(
            new BaseTokenStrategy() {
                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    if (symbol.getType() != SymbolType.LeftParenthesis) {
                        parserControl.fail("Unexpected symbol");
                        return;
                    }

                    parserControl.shift();
                    parserControl.push(SqrtLeft);
                }
            }
    ),
    SqrtLeft(
            new AbstractExpressionVisitor<>() {
                @Override
                protected void visitOther(
                        Expression expression,
                        ParserControl parserControl
                ) {
                    parserControl.push(SqrtRight);
                }
            },
            new BaseTokenStrategy() {
                @Override
                public void visit(
                        Constant constant,
                        ParserControl parserControl
                ) {
                    parserControl.push(
                            new org.fir3.test.calc.expression.Constant(
                                    constant.getValue()
                            )
                    );

                    parserControl.push(OperatorOrEnd);
                    parserControl.shift();
                }

                @Override
                public void visit(
                        Keyword keyword,
                        ParserControl parserControl
                ) {
                    if (keyword.getType() != KeywordType.Sqrt) {
                        parserControl.fail("Unexpected keyword");
                        return;
                    }

                    parserControl.shift();
                    parserControl.push(Sqrt);
                }

                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    SymbolType type = symbol.getType();

                    if (type == SymbolType.LeftParenthesis) {
                        parserControl.shift();
                        parserControl.push(LeftParenthesis);
                        return;
                    }

                    parserControl.fail("Unexpected symbol");
                }
            }
    ),
    SqrtRight(
            new BaseTokenStrategy() {
                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    if (symbol.getType() != SymbolType.RightParenthesis) {
                        parserControl.fail("Unexpected symbol");
                        return;
                    }

                    parserControl.shift();
                    parserControl.push(SqrtReduction);
                }
            }
    ),
    SqrtReduction(
            new BaseTokenStrategy() {
                @Override
                public void visit(Symbol symbol, ParserControl parserControl) {
                    if (
                            symbol.getType() != SymbolType.Plus &&
                                    symbol.getType() != SymbolType.Asterisk
                    ) {
                        parserControl.fail("Unexpected symbol");
                        return;
                    }

                    parserControl.push(
                            new SquareRoot(
                                    parserControl.reduce(4, 1).remove()
                            )
                    );
                }
            }
    );

    private final ExpressionVisitor<ParserControl> expressionStrategy;
    private final TokenStrategy tokenStrategy;

    State(TokenStrategy tokenStrategy) {
        this(null, tokenStrategy);
    }

    State(
            ExpressionVisitor<ParserControl> expressionStrategy,
            TokenStrategy tokenStrategy
    ) {
        this.tokenStrategy = tokenStrategy;
        this.expressionStrategy = expressionStrategy;
    }

    public Optional<ExpressionVisitor<ParserControl>> getExpressionStrategy() {
        return Optional.ofNullable(this.expressionStrategy);
    }

    public TokenStrategy getTokenStrategy() {
        return this.tokenStrategy;
    }
}

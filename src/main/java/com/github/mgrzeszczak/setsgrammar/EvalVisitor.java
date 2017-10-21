package com.github.mgrzeszczak.setsgrammar;

import java.util.HashSet;
import java.util.Set;

public class EvalVisitor extends com.github.mgrzeszczak.setsgrammar.SetsBaseVisitor<MathSet<Integer>> {

    @Override
    public MathSet<Integer> visitExpr(SetsParser.ExprContext ctx) {
        if (ctx.OP_ADD() != null) {
            MathSet<Integer> left = visit(ctx.expr());
            MathSet<Integer> right = visit(ctx.term());
            switch (ctx.OP_ADD().getText()) {
                case "u":
                    return left.union(right);
                case "/":
                    return left.difference(right);
            }
            return null;
        } else {
            return visit(ctx.term());
        }
    }

    @Override
    public MathSet<Integer> visitTerm(SetsParser.TermContext ctx) {
        if (ctx.OP_MUL() != null) {
            MathSet<Integer> left = visit(ctx.term());
            MathSet<Integer> right = visit(ctx.factor());
            switch (ctx.OP_MUL().getText()) {
                case "n":
                    return left.intersection(right);
            }
            return null;
        } else {
            return visitFactor(ctx.factor());
        }
    }

    @Override
    public MathSet<Integer> visitFactor(SetsParser.FactorContext ctx) {
        if (ctx.expr() != null) {
            return visit(ctx.expr());
        } else {
            return visit(ctx.set());
        }
    }

    @Override
    public MathSet<Integer> visitSet(SetsParser.SetContext ctx) {
        if (ctx.ints() == null) {
            return MathSet.of(new HashSet<>());
        }
        return visitInts(ctx.ints());
    }

    @Override
    public MathSet<Integer> visitInts(SetsParser.IntsContext ctx) {
        Set<Integer> set = new HashSet<>();
        set.add(Integer.parseInt(ctx.INT().getSymbol().getText()));
        while (ctx.ints() != null) {
            ctx = ctx.ints();
            set.add(Integer.parseInt(ctx.INT().getSymbol().getText()));
        }
        return MathSet.of(set);
    }
}

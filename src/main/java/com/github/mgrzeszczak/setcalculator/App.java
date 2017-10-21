package com.github.mgrzeszczak.setcalculator;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(">> ");
                String input = scanner.nextLine();
                SetsLexer lexer = new SetsLexer(new ANTLRInputStream(input));
                CommonTokenStream tokenStream = new CommonTokenStream(lexer);
                SetsParser parser = new SetsParser(tokenStream);

                SetsParser.ExprContext expr = parser.expr();
                EvalVisitor visitor = new EvalVisitor();
                MathSet<Integer> result = visitor.visit(expr);
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

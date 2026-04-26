package com.compis;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {

        try {

            CharStream input = CharStreams.fromStream(System.in);

            Lab2Lexer lexer = new Lab2Lexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            Lab2Parser parser = new Lab2Parser(tokens);

            parser.removeErrorListeners();
            parser.addErrorListener(new Errores());

            ParseTree tree = parser.program();

            Semantica visitor = new Semantica();
            Object result = visitor.visit(tree);

            visitor.getTable().print();

            System.out.println("Resultado de la expresión: " + result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

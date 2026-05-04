package com.compis;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {

        try {

            CharStream input = CharStreams.fromStream(System.in);

            /* LEXER */
            Lab2Lexer lexer = new Lab2Lexer(input);

            lexer.removeErrorListeners();
            lexer.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer,
                                        Object offendingSymbol,
                                        int line,
                                        int charPositionInLine,
                                        String msg,
                                        RecognitionException e) {

                    throw new RuntimeException(
                        "Error léxico en línea " + line +
                        ", columna " + charPositionInLine +
                        ": símbolo no válido"
                    );
                }
            });

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            /* PARSER */
            Lab2Parser parser = new Lab2Parser(tokens);

            parser.removeErrorListeners();
            parser.addErrorListener(new Errores());

            ParseTree tree = parser.program();

            /* SEMÁNTICA */
            Semantica visitor = new Semantica();
            Object result = visitor.visit(tree);

            /* OUTPUT */
            visitor.getTable().print();
            System.out.println("Resultado de la expresión: " + result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
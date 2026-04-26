package com.compis;

import org.antlr.v4.runtime.*;

public class Errores extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) {

        throw new RuntimeException("Error sintáctico en línea " + line + ": " + msg);
    }
}

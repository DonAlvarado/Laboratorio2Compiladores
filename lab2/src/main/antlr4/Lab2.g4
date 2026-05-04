grammar Lab2;

@header {
package com.compis;
}

program
    : statement* expr EOF
    ;

statement
    : ID '=' value
    ;

expr
    : expr 'and' expr     # AndExpr
    | 'not' '(' expr ')'  # NotExpr
    | ID                  # IdExpr
    ;

value
    : BOOL
    | INT
    | STRING
    ;

BOOL   : 'true' | 'false';
INT    : [0-9]+;
STRING : '"' .*? '"';
ID     : [a-zA-Z]+;

WS : [ \t\r\n]+ -> skip;
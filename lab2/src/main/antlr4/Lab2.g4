grammar Lab2;

@header {
package com.compis;
}

/* estructura principal */
program
    : statement* expr EOF
    ;

/* declaraciones */
statement
    : ID '=' value
    ;

/* expresiones booleanas */
expr
    : expr 'and' expr
    | 'not' '(' expr ')'
    | ID
    ;

/* valores */
value
    : BOOL
    | INT
    | STRING
    ;

/* tokens */
BOOL   : 'true' | 'false';
INT    : [0-9]+;
STRING : '"' .*? '"';
ID     : [a-zA-Z]+;

/* espacios */
WS : [ \t\r\n]+ -> skip;